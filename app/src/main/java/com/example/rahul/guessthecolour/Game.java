package com.example.rahul.guessthecolour;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Math.random;
import static java.lang.Math.round;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;

public class Game extends AppCompatActivity
{
    Button options[]=new Button[4];
    TextView question;
    TextView scoreDisp;
    int score;
    int time=2500;
    TextView timerTextField;
    String colours[]={"BLUE","RED","ORANGE","GREEN","PURPLE","BLACK","YELLOW","BROWN","GRAY","PINK"};
    String answer;
    String option[]=new String[4];
    CountDownTimer timer=new CountDownTimer(time,1000)
    {
        @Override
        public void onTick(long millisUntilFinished)
        {
            timerTextField.setText(Long.toString(millisUntilFinished/1000));
        }
        @Override
        public void onFinish()
        {
            gameOver();
        }
    };
    Drawable d;
    int colors[]={Color.BLUE,Color.RED,Color.rgb(255,102,0),Color.GREEN,Color.rgb(102,51,153),Color.BLACK,Color.YELLOW,Color.rgb(102,51,00),Color.GRAY,Color.rgb(255,00,128)};
    //final MediaPlayer rs = MediaPlayer.create(this,R.raw.success);
    //final MediaPlayer ws = MediaPlayer.create(getApplicationContext(), R.raw.gameover);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setup();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        shuffle();
        time=2500;
        timer.start();
    }

    private void setup()
    {
        options[0]=(Button)findViewById(R.id.optionA);
        options[1]=(Button)findViewById(R.id.optionB);
        options[2]=(Button)findViewById(R.id.optionC);
        options[3]=(Button)findViewById(R.id.optionD);
        question=(TextView)findViewById(R.id.question);
        scoreDisp=(TextView)findViewById(R.id.scoreDisplay);
        score=0;
        timerTextField=(TextView)findViewById(R.id.timer);
        d=options[0].getBackground();
        /*AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("YOUR_DEVICE_HASH")
                .build();
        mAdView.loadAd(adRequest);*/

    }

    private void shuffle()
    {
        //question
        double a1=random();
        int b1=((int)round(a1*10))%10;
        question.setText(colours[b1]);

        //question font colour
        double a2=random();
        int b2=((int)round(a2*10))%10;
        question.setTextColor(colors[b2]);
        answer=colours[b2];
        /*
        //question background colour
        int b3=b2;
        while(b3==b1 || b3==b2)
        {
            double a3=random();
            b3=((int)round(a3*10)%10);
        }
        question.setBackgroundColor(colors[b3]);*/

        //options
        int i=0;
        int vals[]=new int[4];
        while(i<4)
        {
            boolean flag=true;
            double x=random();
            vals[i]=((int)round(x*10))%10;
            for(int j=i-1;j>=0;j--)
            {
                if(vals[i]==vals[j])
                {
                    flag=false;
                    break;
                }
            }
            if(flag)
            {
                option[i] = colours[vals[i]];
                options[i].setText(option[i]);
                i++;
            }
        }
        //checking if answer exists
        boolean flag2=true;
        for(int k=0;k<4;k++)
        {
            if(option[k].equals(answer))
                flag2=false;        //answer exists
        }

        //adding correct answer
        if(flag2)
        {
            double a = random();
            int b = ((int) round(a * 10)) % 4;
            option[b] = answer;
            options[b].setText(answer);
        }
    }

    public void clickedA(View v)
    {
        timer.cancel();
        if(options[0].getText().equals(answer))
            RightAnswer(0);
        else
            WrongAnswer(0);
    }
    public void clickedB(View v)
    {
        timer.cancel();
        if(options[1].getText().equals(answer))
            RightAnswer(1);
        else
            WrongAnswer(1);
    }
    public void clickedC(View v)
    {
        timer.cancel();
        if(options[2].getText().equals(answer))
            RightAnswer(2);
        else
            WrongAnswer(2);
    }
    public void clickedD(View v)
    {
        timer.cancel();
        if(options[3].getText().equals(answer))
            RightAnswer(3);
        else
            WrongAnswer(3);
    }
    public void RightAnswer(final int i)
    {
        score++;
        time-=10;
        //rs.start();
        options[i].setBackgroundColor(Color.GREEN);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                options[i].setBackground(d);
            }
        }, 100);
        scoreDisp.setText(Integer.toString(score));
        shuffle();
        timer.start();
    }
    public void WrongAnswer(final int i)
    {
        options[i].setBackgroundColor(Color.RED);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                options[i].setBackground(d);
            }
        }, 500);
        //ws.start();
        gameOver();
    }
    public void gameOver()
    {
        finish();
        Intent intent=new Intent(getApplicationContext(),GameOver.class);
        intent.putExtra("Currscore",Integer.toString(score));
        setHighScore();
        startActivity(intent);
    }
    public void setHighScore()
    {
        SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        int previousScore=0;
        if(prefs.getString("getScore","0") != null)
            previousScore=Integer.parseInt(prefs.getString("getScore", "0"));
        if(previousScore < score)
            editor.putString("getScore", Integer.toString(score));
        else
            editor.putString("getScore", Integer.toString(previousScore));
        editor.commit();
    }
    @Override
    protected void onStop() {
        super.onStop();
        setHighScore();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        setHighScore();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        timer.cancel();
        setHighScore();
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        setHighScore();
        finish();
    }
}
