package com.pterodactylogan.othello;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
    }

    public void reset(View v){
        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        System.out.println("reset");

    }



}
