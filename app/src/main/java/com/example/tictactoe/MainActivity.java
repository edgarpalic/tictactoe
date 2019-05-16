package com.example.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //0 is O, 1 is X

    //An array with the int of 2 that represents a third state, empty.
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    int activePlayer = 0;

    boolean gameActive = true;

    /* dropIn is the onClick added on all the image views.
    When clicked an animated image of an X or an O will appear
    A simple if statement swaps between player 0 and player 1 */
    public void dropIn(View view){
        ImageView counter = (ImageView) view;

        // This will change the values in the gameState array from 2 to 0 or 1.
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        //If the click area is empty and game is active, then it can be played.
        if(gameState[tappedCounter] == 2 && gameActive) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.o);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.x);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).setDuration(500);

            //if someone meets these conditions then they win.
            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    //game stops when someone wins.
                    gameActive = false;

                    String winner;
                    if (activePlayer == 1) {
                        winner = "The O player";
                    } else {
                        winner = "The X player";
                    }

                    Button playAgainButton = findViewById(R.id.playAgainButton);
                    TextView winnerTextView = findViewById(R.id.winnerTextView);

                    winnerTextView.setText(winner + " has won!");

                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                }
            }
        }else{
            gameActive = false;
            Button playAgainButton = findViewById(R.id.playAgainButton);
            TextView winnerTextView = findViewById(R.id.winnerTextView);

            winnerTextView.setText("It is a draw!");

            playAgainButton.setVisibility(View.VISIBLE);
            winnerTextView.setVisibility(View.VISIBLE);
        }
    }

    public void playAgain(View view){
        Button playAgainButton = findViewById(R.id.playAgainButton);
        TextView winnerTextView = findViewById(R.id.winnerTextView);

        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = findViewById(R.id.gridLayout);

        //A little loop to reset all the imageViews.
        for(int i=0; i<gridLayout.getChildCount(); i++){
            ImageView counter1 = (ImageView) gridLayout.getChildAt(i);
            counter1.setImageDrawable(null);
        }
        //A little loop to set all the counters back to 2. And restart game.
        for(int i=0; i<gameState.length; i++) {
            gameState[i] = 2;
        }
        activePlayer = 0;
        gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
