package com.example.techprep

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.techprep.database.QuestionJson
import com.example.techprep.databinding.ActivityMultipleChoiceBinding
import com.example.techprep.questionList.QuestionListActivity
import java.util.*

class MultipleChoiceActivity : AppCompatActivity(), View.OnClickListener {

    private var binding: ActivityMultipleChoiceBinding? = null
    private var mCurrentPosition: Int = 1
    private var mQuestion: Question? = null
    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswer: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMultipleChoiceBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setQuestion()
    }

    private fun setQuestion() {
        defaultOptionsView()
        binding?.tvOptionOne?.isClickable = true
        binding?.tvOptionTwo?.isClickable = true
        binding?.tvOptionThree?.isClickable = true
        binding?.tvOptionFour?.isClickable = true
        binding?.btnSubmit?.isClickable = false

        val question: QuestionJson = mQuestionsList!![mCurrentPosition - 1]
        binding?.tvQuestion?.text = question.question
        binding?.tvOptionOne?.text = question.optionOne
        binding?.tvOptionTwo?.text = question.optionTwo
        binding?.tvOptionThree?.text = question.optionThree
        binding?.tvOptionFour?.text = question.optionFour

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
                selectedOptionView(binding?.tvOptionOne!!,1)
            }

            R.id.tvOptionTwo -> {
                selectedOptionView(binding?.tvOptionTwo!!,2)
            }

            R.id.tvOptionThree -> {
                selectedOptionView(binding?.tvOptionThree!!,3)
            }

            R.id.tvOptionFour -> {
                selectedOptionView(binding?.tvOptionFour!!,4)
            }

            R.id.btnSubmit -> {
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++

                    if (mCurrentPosition <= mQuestionsList!!.size) {
                        setQuestion()
                    } else {
                        val intent = Intent(this, QuestionListActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
                else {
                    val question = mQuestionsList?.get(mCurrentPosition - 1)

                    if (question!!.correctAnswer != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }else{
                        mCorrectAnswer++
                    }

                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if (mCurrentPosition == mQuestionsList!!.size) {
                        binding?.btnSubmit?.text = "FINISH"
                    } else {
                        binding?.btnSubmit?.text = "NEXT QUESTION"
                    }

                    mSelectedOptionPosition = 0
                    binding?.tvOptionOne?.isClickable = false
                    binding?.tvOptionTwo?.isClickable = false
                    binding?.tvOptionThree?.isClickable = false
                    binding?.tvOptionFour?.isClickable = false
                }
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