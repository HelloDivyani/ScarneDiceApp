package com.example.android.scarnedice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private TextView tvTurnScore,tvComputerOverallScore,tvStatus,tvPlayerOverallScore,tvPlayerTurnScore;
    private ImageView ivDiceFace;
    private Button btHold,btRoll,btReset;
    private int ComputerTurnScore,ComputerOverallScore,PlayerTurnScore,PlayerOverallScore;
    private int[] diceFaces={R.drawable.dice1,R.drawable.dice2,R.drawable.dice3,R.drawable.dice4,R.drawable.dice5,R.drawable.dice6};

  // private Handler handler;
//    private Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvPlayerTurnScore=(TextView)findViewById(R.id.tvTurnScore);
        tvPlayerOverallScore=(TextView)findViewById(R.id.tvPlayerOverallScore);
        tvComputerOverallScore=(TextView)findViewById(R.id.tvComputerScore);
        tvStatus=(TextView)findViewById(R.id.tvStatus);
        ivDiceFace=(ImageView)findViewById(R.id.ivDiceFace);
        btHold=(Button)findViewById(R.id.btHold);
        btRoll=(Button)findViewById(R.id.btRoll);
        btReset=(Button)findViewById(R.id.btReset);


        btHold.setOnClickListener(this);
        btReset.setOnClickListener(this);
        btRoll.setOnClickListener(this);

        btHold.setEnabled(false);
       //Handler =new Handler();



    }



    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.btHold:
                hold();
                break;
            case R.id.btReset:
                reset();
                break;
            case R.id.btRoll:
                roll();
                break;
        }
    }
    private void roll()
    {
        btHold.setEnabled(true);
        tvPlayerTurnScore.setVisibility(View.VISIBLE);
        int rollNumber=getDiceFaceNumber();
        Toast.makeText(this,"Rolled:"+rollNumber,Toast.LENGTH_SHORT).show();
                if(rollNumber!=1)
                {
                    PlayerTurnScore+=rollNumber;
                    tvPlayerTurnScore.setText("Player turn score is:"+PlayerTurnScore);
                }
        else
                {
                    PlayerTurnScore=0;
                    tvPlayerTurnScore.setText("Player turn score is:"+PlayerTurnScore);
                    computerTurn();
                }
    }
    private int getDiceFaceNumber()
    {
        Random random=new Random();
        int i=random.nextInt(6);
        ivDiceFace.setImageResource(diceFaces[i]);
        return i+1;
    }
    private void computerTurn()
    {
        Toast.makeText(this,"Computer's Turn",Toast.LENGTH_SHORT).show();
        displayButtons();
        resetPlayerTurnScore();
        final Random random = new Random();
        while(true)
        {
            int rollNumber=getDiceFaceNumber();
            Toast.makeText(this,"Rolled:"+rollNumber,Toast.LENGTH_SHORT).show();
            if(rollNumber!=1)
            {
                ComputerTurnScore+=rollNumber;
                boolean hold=random.nextBoolean();
                if(hold)
                {
                    ComputerOverallScore+=ComputerTurnScore;
                    break;

                }
                else
                {
                    ComputerTurnScore=0;
                    break;
                }
            }
        }
        tvComputerOverallScore.setText("Computer overall Score:" + ComputerOverallScore);
        PlayerTurn();

    }
    private void PlayerTurn()
    {
       resetPlayerTurnScore();
        Toast.makeText(this,"Your turn",Toast.LENGTH_SHORT).show();
        btHold.setEnabled(false);
        btRoll.setEnabled(true);
    }
    private void resetPlayerTurnScore()
    {
        PlayerTurnScore=0;
        tvPlayerTurnScore.setText("");
    }
    private void displayButtons()
    {
        btRoll.setEnabled(false);
        btHold.setEnabled(false);
    }
    private void reset()
    {
        PlayerTurnScore=0;
        PlayerOverallScore=0;
        ComputerOverallScore=0;
        ComputerTurnScore=0;
        tvComputerOverallScore.setText("Computer overall score:"+ComputerOverallScore);
       // tvPlayerTurnScore.setText("Player turn score:{PlayerTurnScore}");
        tvPlayerTurnScore.setVisibility(View.INVISIBLE);
        ivDiceFace.setImageResource(diceFaces[0]);
        tvPlayerOverallScore.setText("Player overall score:"+PlayerOverallScore);

    }

    private void hold()
    {
        PlayerOverallScore+=PlayerTurnScore;
        PlayerTurnScore=0;
        tvPlayerOverallScore.setText("Player Overall Score"+PlayerOverallScore);
        computerTurn();
    }
}
