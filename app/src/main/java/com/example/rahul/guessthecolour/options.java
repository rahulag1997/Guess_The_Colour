package com.example.rahul.guessthecolour;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class options extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
    }
    public void reset(View v)
    {
        SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("getScore", Integer.toString(0));
        editor.commit();
        Toast.makeText(this,"HighScore set to 0",Toast.LENGTH_SHORT).show();
    }
    public void back(View v)
    {
        finish();
    }
}
