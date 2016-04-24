package com.example.rahul.guessthecolour;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameOver extends AppCompatActivity
{
    TextView currentScore;
    TextView highest_score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        setup();
    }

    private void setup()
    {
        currentScore =(TextView) findViewById(R.id.CurrentScore);
        highest_score =(TextView) findViewById(R.id.HighestScore);
        setCurrentScore();
        setHighestScore();
     }

    private void setCurrentScore()
    {
        Intent b=getIntent();
        String score_thistime=b.getExtras().getString("Currscore");
        currentScore.setText(score_thistime);
    }

    private void setHighestScore()
    {
        SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        highest_score.setText(prefs.getString("getScore",null));
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
    }
    public void backToMain(View v)
    {
        finish();
    }
    public void playAgain(View v)
    {
        finish();
        Intent i =new Intent(getApplicationContext() , Game.class);
        startActivity(i);
    }

}
