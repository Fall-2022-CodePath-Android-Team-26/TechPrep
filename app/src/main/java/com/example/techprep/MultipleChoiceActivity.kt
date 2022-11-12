package com.example.techprep

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.techprep.database.QuestionJson
import com.example.techprep.database.QuestionsApplication
import com.example.techprep.databinding.ActivityMultipleChoiceBinding
import com.example.techprep.questionList.QUESTION_EXTRA
import com.example.techprep.questionList.Question
import com.example.techprep.questionList.QuestionListActivity
import com.example.techprep.topics.QUESTION_TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class MultipleChoiceActivity : AppCompatActivity(), View.OnClickListener {

    private var binding: ActivityMultipleChoiceBinding? = null
    private var mSelectedOptionPosition: Int = 0
    private lateinit var mQuestion: Question


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMultipleChoiceBinding.inflate(layoutInflater)
        setContentView(binding?.root)

//        Log.i("GetSerializable", "test")

        val questionId = (intent.getStringExtra(QUESTION_EXTRA))!!

//        Log.i("GetSerializable", questionId)

        val asyncJob = lifecycleScope.launch(Dispatchers.IO) {
//            Log.i("DB response", "This is being repeated")

            val question = (application as QuestionsApplication).db.questionDao()
                .getIdQuestions(questionId)
            mQuestion = Question(question.id,question.question,question.description, question.answers, question.multiple_correct_answers, question.correct_answers, question.explanation,
            question.tip, question.tags, question.category, question.difficulty)
//            Log.i("GetQuestion", question.question.toString())
        }

        while (asyncJob.isActive) {

        }

//        Log.i("GetSerializableQuestion", mQuestion.question.toString())

        setQuestion(mQuestion)

//        val resultIntent = Intent()
//        resultIntent.putExtra("new food", newFood)
//        setResult(RESULT_OK, resultIntent)
//        finish()
    }

    private fun setQuestion(question: Question) {
        defaultOptionsView()
        binding?.tvOptionOne?.isClickable = true
        binding?.tvOptionTwo?.isClickable = true
        binding?.tvOptionThree?.isClickable = true
        binding?.tvOptionFour?.isClickable = true
        binding?.btnSubmit?.isClickable = false

        binding?.tvQuestion?.text = question.question
        binding?.tvOptionOne?.text = question.answers?.get(0)?.value.toString()
        binding?.tvOptionTwo?.text = question.answers?.get(1)?.value.toString()
        binding?.tvOptionThree?.text = question.answers?.get(2)?.value.toString()
        binding?.tvOptionFour?.text = question.answers?.get(3)?.value.toString()

        binding?.btnSubmit?.text = "SUBMIT"
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, binding?.tvOptionOne!!)
        options.add(1, binding?.tvOptionTwo!!)
        options.add(2, binding?.tvOptionThree!!)
        options.add(3, binding?.tvOptionFour!!)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        binding?.btnSubmit?.isClickable = true
        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.typeface = Typeface.DEFAULT_BOLD
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tvOptionOne -> {
                selectedOptionView(binding?.tvOptionOne!!,0)
            }

            R.id.tvOptionTwo -> {
                selectedOptionView(binding?.tvOptionTwo!!,1)
            }

            R.id.tvOptionThree -> {
                selectedOptionView(binding?.tvOptionThree!!,2)
            }

            R.id.tvOptionFour -> {
                selectedOptionView(binding?.tvOptionFour!!,3)
            }

            R.id.btnSubmit -> {

                val correctAnswers = mQuestion.correct_answers
                var correctAnswer: Int = 0

                for(i in 0 until 3){
                    if(correctAnswers?.get(i)?.value.toBoolean()){
                        break
                    }else{
                        correctAnswer++
                    }
                }
                if (correctAnswer != mSelectedOptionPosition) {
                    answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                }

                answerView(correctAnswer, R.drawable.correct_option_border_bg)

                binding?.btnSubmit?.text = "SUBMIT"

                binding?.tvOptionOne?.isClickable = false
                binding?.tvOptionTwo?.isClickable = false
                binding?.tvOptionThree?.isClickable = false
                binding?.tvOptionFour?.isClickable = false


//                val intent = Intent(this, QuestionListActivity::class.java)
//                startActivity(intent)
//                finish()
            }
        }
    }

    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                binding?.tvOptionOne?.background = ContextCompat.getDrawable(this, drawableView)
            }
            2 -> {
                binding?.tvOptionTwo?.background = ContextCompat.getDrawable(this, drawableView)
            }
            3 -> {
                binding?.tvOptionThree?.background = ContextCompat.getDrawable(this, drawableView)
            }
            4 -> {
                binding?.tvOptionFour?.background = ContextCompat.getDrawable(this, drawableView)
            }
        }
    }
}