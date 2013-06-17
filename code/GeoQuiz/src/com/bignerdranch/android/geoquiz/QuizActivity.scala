package com.bignerdranch.android.geoquiz

import android.os.Bundle
import android.app.Activity
import android.view.Menu
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.view.View
import android.view.View.OnClickListener
import utils.Helpers._

class QuizActivity extends Activity {

  val questionBank = Map(
    0 -> new TrueFalse(R.string.question_oceans, true),
    1 -> new TrueFalse(R.string.question_mideast, false),
    2 -> new TrueFalse(R.string.question_africa, false),
    3 -> new TrueFalse(R.string.question_americas, true),
    4 -> new TrueFalse(R.string.question_asia, true))

  def nextIndex(i: Int) = (i + 1) % questionBank.size
  
  def prevIndex(i: Int) = (i + questionBank.size - 1) % questionBank.size
  
  //TODO remove mutable state
  var currentIndex : Int = 0
  
  def checkAnswer(userPressedTrue : Boolean) = {
    val answerIsTrue = questionBank(currentIndex).trueQuestion
    
    val messageResId = if(userPressedTrue == answerIsTrue) R.string.correct_toast else R.string.incorrect_toast
    
    Toast.makeText(QuizActivity.this, messageResId, Toast.LENGTH_SHORT).show()
  }
  
  override def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_quiz);

    val questionTextView = this.findViewById(R.id.question_text_view).asInstanceOf[TextView]
    questionTextView.setText(questionBank(currentIndex).question)
    
    val nextClickHandler = (v: View) => {
      currentIndex = nextIndex(currentIndex)
      questionTextView.setText(questionBank(currentIndex).question)
    }
    
    questionTextView.setOnClickListener(nextClickHandler)
    
    val trueButton = this.findViewById(R.id.true_button).asInstanceOf[Button]
    trueButton.setOnClickListener((v: View) => checkAnswer(true))

    val falseButton = this.findViewById(R.id.false_button).asInstanceOf[Button]
    falseButton.setOnClickListener((v: View) => checkAnswer(false))
    
    val nextButton = this.findViewById(R.id.next_button).asInstanceOf[Button]
    nextButton.setOnClickListener(nextClickHandler)
    
    val prevButton = this.findViewById(R.id.prev_button).asInstanceOf[Button]
    prevButton.setOnClickListener((v: View) => {
      currentIndex = prevIndex(currentIndex)
      questionTextView.setText(questionBank(currentIndex).question)
    })
  }

  override def onCreateOptionsMenu(menu: Menu): Boolean = {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.quiz, menu);
    return true;
  }

}
