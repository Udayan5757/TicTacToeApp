package com.example.tictactoelight;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    int activePlayer = 1;
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int[] track = {2,2,2,2,2,2,2,2,2};
    boolean status = false;
    @SuppressLint("SetTextI18n")
    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        int tappedPosition = Integer.parseInt(counter.getTag().toString());
        if(track[tappedPosition]==2 && !status){
        counter.setTranslationY(-1500);
        if(activePlayer==0) {
            counter.setImageResource(R.drawable.cross1);
            track[tappedPosition] = activePlayer;
            activePlayer = 1;
        }
        else{
            counter.setImageResource(R.drawable.right);
            track[tappedPosition] = activePlayer;
            activePlayer = 0;
        }
        counter.animate().translationYBy(1500).setDuration(300);
        for(int[] each:winningPositions){
            if(track[each[0]]==track[each[1]] && track[each[2]]==track[each[0]] && track[each[0]]!=2){
                String player;
                if(activePlayer==1) {
                    player = "Red";
                }
                else {
                    player = "Green";
                }
                status = true;
                Toast.makeText(this, player +" is the winner", Toast.LENGTH_SHORT).show();
                Button playAgainButton = findViewById(R.id.playAgainButton);
                TextView textView = findViewById(R.id.textView);
                textView.setText(player + " is the winner");
                textView.setVisibility(View.VISIBLE);
                playAgainButton.setVisibility(View.VISIBLE);
            }
          }
        }
    }
    public void runTheGame(View view){
        Button playAgainButton = findViewById(R.id.playAgainButton);
        TextView textView = findViewById(R.id.textView);
        playAgainButton.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);
        androidx.gridlayout.widget.GridLayout gridlayout = findViewById(R.id.gridlayout);
        for(int i=0;i<gridlayout.getChildCount();i++){
            ImageView counter = (ImageView) gridlayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        Arrays.fill(track, 2);
        activePlayer = 1;
        status = false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}