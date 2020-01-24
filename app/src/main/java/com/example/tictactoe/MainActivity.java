package com.example.tictactoe;
//Tyler Hennig
//K00400684

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String PLAYER1 = "X";
    String PLAYER2 = "O";
    int player1wins = 0;
    int player2wins = 0;
    int tiesnum = 0;
    String currentPlayer = "X";
    String first = PLAYER1;
    int roundNumber = 0;
    Button[] position = new Button[9];

    TextView xwins;
    TextView owins;
    TextView ties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        for (int i = 0; i < 9; i++){
            String buttonID = "pos" + (i+1);
            int viewID = getResources().getIdentifier(buttonID, "id", getPackageName());
            position[i] = findViewById(viewID);
            position[i].setOnClickListener(this);
        }


        xwins = findViewById(R.id.xwin);
        owins = findViewById(R.id.owin);
        ties = findViewById(R.id.ties);

        Button resetscore = (Button) findViewById(R.id.reset);
        resetscore.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                player1wins = 0;
                player2wins = 0;
                tiesnum = 0;

                xwins.setText("X's Wins: " + String.valueOf(player1wins));
                owins.setText("O's Wins: " + String.valueOf(player1wins));
                ties.setText("Ties: " + String.valueOf(tiesnum));
            }
        });
        Button newgame = (Button) findViewById(R.id.newgame);
        newgame.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                reset();
            }
        });


    }
    @Override
    public void onClick(View v) {
        TextView status = (TextView) findViewById(R.id.status);
        TextView xwins = (TextView) findViewById(R.id.xwin);
        TextView owins = (TextView) findViewById(R.id.owin);

        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        if (currentPlayer.equals(PLAYER1)) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }

        roundNumber++;

        if (checkForWin(currentPlayer)) {
            if (currentPlayer.equals(PLAYER1)) {
                player1win();
                status.setText("Player1 Wins");
                xwins.setText("X's Wins: " + String.valueOf(player1wins));
            } else {
                player2win();
                status.setText("Player2 Wins");
                owins.setText("O's Wins: " + String.valueOf(player2wins));
            }
        } else if (roundNumber == 9) {
            draw();
            ties.setText("Ties: " + String.valueOf(tiesnum));
            status.setText("Draw!");
        } else {
            if (currentPlayer.equals(PLAYER1)) {
                currentPlayer = PLAYER2;
                status.setText("Player2's Turn.");
            } else {
                currentPlayer = PLAYER1;
                status.setText("Player1's Turn.");
            }
        }
    }
    public void player1win(){
        player1wins++;
        reset();
    }
    public void player2win(){
        player2wins++;
        reset();
    }
    public void draw(){
        tiesnum++;
        reset();
    }

    public void reset() {
        for (int i = 0; i < 9; i++) {
            position[i].setText("");
        }
        roundNumber = 0;
        if (first.equals(PLAYER1)) {
            currentPlayer = PLAYER2;
            first = PLAYER2;
        } else {
            currentPlayer = PLAYER1;
            first = PLAYER1;
        }
    }


    public boolean checkForWin(String player){
        boolean win = false;
        if (position[0].getText().toString().equals(player) && position[1].getText().toString().equals(player) && position[2].getText().toString().equals(player) ){
            win = true;
        }
        if (position[3].getText().toString().equals(player) && position[4].getText().toString().equals(player) && position[5].getText().toString().equals(player) ){
            win = true;
        }
        if (position[6].getText().toString().equals(player) && position[7].getText().toString().equals(player) && position[8].getText().toString().equals(player) ){
            win = true;
        }
        if (position[0].getText().toString().equals(player) && position[3].getText().toString().equals(player) && position[6].getText().toString().equals(player) ){
            win = true;
        }
        if (position[1].getText().toString().equals(player) && position[4].getText().toString().equals(player) && position[7].getText().toString().equals(player) ){
            win = true;
        }
        if (position[2].getText().toString().equals(player) && position[5].getText().toString().equals(player) && position[8].getText().toString().equals(player) ){
            win = true;
        }
        if (position[0].getText().toString().equals(player) && position[4].getText().toString().equals(player) && position[8].getText().toString().equals(player) ){
            win = true;
        }
        if (position[6].getText().toString().equals(player) && position[4].getText().toString().equals(player) && position[2].getText().toString().equals(player) ){
            win = true;
        }
        return win;
    }
    }


