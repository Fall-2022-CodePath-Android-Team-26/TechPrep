# Milestone 1 - TechPrep
<!-- CodePrep (This is taken already) -->

## Table of Contents

1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)

## Overview

### Description

Mobile App for preparing for techinical interviews with multiple choice style programming questions

### App Evaluation

[Evaluation of your app across the following attributes]
- **Category:** Quiz/Test category
- **Mobile:** Can thrive as website as well
- **Story:** Very compelling, it's like leetcode except on the go
- **Market:** Pretty unique
- **Habit:** Used to enforce good habits
- **Scope:** Caters to programmers/developers

## Product Spec

### 1. User Features (Required and Optional)

**Required Features**

* Display programming questions (multiple choice)
* Display correct and wrong solutions at the end
* Choose certain topics to work on

**Stretch Features**

* Login - keep track of progress (graphs, scores, success/fail percentage by topics)
* User created questions
* Timer
* Pick 10,20,50 questions
* Answer as many questions before x minutes (type of test)
* Keep track of streak/highest score
* Daily reminder
* Personal study notes
* Enable offline mode with database that caches the question list

### 2. Screen Archetypes

- Question topics
  - Choose certain topics to work on
- RecyclerView of the list of questions
  - Display programming questions
- Clicking a question brings up Artifact?
  - Lists the question and multiple choice options

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Question topics - Grid/linear layout of topics
* Dashboard (or History?) - Grid/Linear Layout on right/wrong answers
* *Stretch: Create Questions Tab

**Flow Navigation** (Screen to Screen)

- Question Topics screen
  - => RecyclerView of the list of questions
- RecyclerView of the list of questions
  - => An artifact of a question + multiple choice options
- An artifact of a question + multiple choice options
  - => A way to go back to the RecyclerView of questions (Back or Submit Button)

## Wireframes

[Add picture of your hand sketched wireframes in this section]
<img src="YOUR_WIREFRAME_IMAGE_URL" width=600>

### [BONUS] Digital Wireframes & Mockups

### [BONUS] Interactive Prototype
