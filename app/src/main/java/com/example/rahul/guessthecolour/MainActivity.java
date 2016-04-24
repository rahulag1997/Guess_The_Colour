package com.example.rahul.guessthecolour;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void game(View v)
    {
        Intent intent=new Intent(v.getContext(),Game.class);
        startActivityForResult(intent, 0);
    }
    public void options(View v)
    {
        Intent intent= new Intent(v.getContext(),options.class);
        startActivityForResult(intent,0);
    }
    public void help(View v)
    {
        Intent intent= new Intent(v.getContext(),Help.class);
        startActivityForResult(intent,0);
    }

    public void HighScore(View v)
    {
        SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        String HighScore = prefs.getString("getScore", "0");
        TextView Hscore =(TextView) findViewById(R.id.highscoreText);
        Hscore.setText(HighScore);
    }

    public void exit(View v)
    {
        finish();
    }
}
