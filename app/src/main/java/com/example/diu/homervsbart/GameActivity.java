package com.example.diu.homervsbart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {



    // 0 for homer; 1 for bart ;2 for empty
    int activePlayer=0;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winnigPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive=true;
    //play again button
    Button playAgainButton;

    //message to show the winner
    String msg="";

    boolean isDraw=false;

    public void tapAction(View view){

        ImageView counter=(ImageView) view;
//        Log.i("tag",counter.getTag().toString());

        int tappedTag=Integer.parseInt(counter.getTag().toString());

       //ending progeamme here
//        if(isDraw == true){
//
//
//        }

        if (gameState[tappedTag]==2 && gameActive){

            gameState[tappedTag]=activePlayer;
            counter.setTranslationY(-1500);

            if(activePlayer==0){
                counter.setImageResource(R.drawable.homer5);
                activePlayer=1;
            }else if(activePlayer==1){
                counter.setImageResource(R.drawable.bart);
                activePlayer=0;
            }
            counter.animate().translationYBy(1500).rotation(36000).setDuration(300);

            //checking if any field is empty or not, if not then the game is Draw;
            for(int i=0;i<gameState.length;i++){
                if(gameState[i]==2){
                    isDraw=false;
                    break;
                }else {
                    isDraw=true;
                }
            }


            if(!isDraw){

                for (int[] winningPosition: winnigPositions){
                    if (gameState[winningPosition[0]]==gameState[winningPosition[1]] &&
                            gameState[winningPosition[1]]==gameState[winningPosition[2]] && gameState[winningPosition[0]]!=2 ){


                        if (activePlayer==1){
                            msg="Homer";
                        }else if (activePlayer==0) {
                            msg="Bart";
                        }


                        Toast.makeText(this, "Winner is "+msg, Toast.LENGTH_SHORT).show();
                        gameActive=false;
                        playAgainButton = findViewById(R.id.playAgainButton);
                        playAgainButton.setVisibility(view.VISIBLE);
                    }
                }

            }else {
                //ending code here
                msg="No one";
                Toast.makeText(this, "Winner is "+msg, Toast.LENGTH_SHORT).show();
                gameActive=false;
                playAgainButton = findViewById(R.id.playAgainButton);
                playAgainButton.setVisibility(view.VISIBLE);

            }


        }

    }

    public void playAgainFunction(View view){

        playAgainButton.setVisibility(view.INVISIBLE);
        activePlayer=0;
        gameActive=true;
        //setting game state to empty
        for (int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }

        //removing all imageview
        GridLayout gridLayout= (GridLayout) findViewById(R.id.gridLayout);
        for (int i=0;i<gridLayout.getChildCount();i++){
            ImageView Counter= (ImageView) gridLayout.getChildAt(i);
            Counter.setImageDrawable(null);
        }

        Log.i("info","play again button pressed");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

    }
}
