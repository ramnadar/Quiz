package com.example.android.quiz;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Ans, Score = 0; // This variable will store the Score of the Quiz
                //At the end, the score will displayed in the status bar as a Toast or Snack.
                RadioGroup radGroup;
                AppCompatRadioButton ansButton, radButton;
                StringBuilder MyStringList = new StringBuilder();


                LinearLayout quizBody =  (LinearLayout)  findViewById(R.id.quizbody);

                
                for (int i=0; i< quizBody.getChildCount();i++)
                {
                    View v  =  quizBody.getChildAt(i);
                    if ( v instanceof RadioGroup )
                    {
                      radGroup = (RadioGroup) v;
                      //  Each Radio group has a Answer Text with Tag ans
                        ansButton = (AppCompatRadioButton) radGroup.findViewWithTag("ans");
                        String ans ;
                        ans =  ansButton.getText().toString();


                      if  (radGroup.getCheckedRadioButtonId() == -1)
                      {
                          
                         Snackbar.make(view,"Please Select Answers for each group", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                          break;
                      }

                      else {
                          radButton = (AppCompatRadioButton) findViewById(radGroup.getCheckedRadioButtonId());

                          //store the answere in a string list along with the end result
                          MyStringList.append(radButton.getText().toString() + " : " + radButton.getText().toString().equalsIgnoreCase(ans) + " "  );
                          

                          //Retrieve the Correct Ans by Tag and Compare this with the Selected Radio Button
                          //If it matches, award 2 points
                          if (radButton.getText().toString().equalsIgnoreCase(ans)) {
                              Score = Score + 2;
                          }

                          //Snackbar.make(view, radButton.getText().toString(), Snackbar.LENGTH_LONG)
                          //       .setAction("Action", null).show();
                      }
                    }

                }
                Snackbar.make(view, MyStringList.toString() +" Score = " + Score, Snackbar.LENGTH_LONG).setAction("Action", null).show();




            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
