package com.pterodactylogan.othello;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //PLAYER IS WHITE TILES, COMPUTER IS BLACK TILES
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BoardView board = (BoardView) findViewById(R.id.board_view);
        board.setStatusListener(new BoardView.GameStatusListener() {
            @Override
            public void updateText(String text) {
                TextView status = (TextView) findViewById(R.id.status);
                status.setText(text);
            }
        });

        final  BoardView myBoard = (BoardView) findViewById(R.id.board_view);
        Button resetButton = (Button) findViewById(R.id.reset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset(myBoard);
            }
        });


    }



    public void reset(BoardView v){
        v.resetGame();
    }


}
