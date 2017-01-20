package com.pterodactylogan.othello;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by demouser on 1/13/17.
 */

public class BoardView extends View {

    boolean comp = false;

    private Paint mBackgroundPaint;
    BoardStructure b = new BoardStructure(8);
    private BoardStructure mGame = new BoardStructure(8);

    public interface GameStatusListener {
        void updateText(String text);
    }
    private GameStatusListener mStatus;

    public void setStatusListener(GameStatusListener listener){
        mStatus= listener;
    }

    public BoardView(Context context) {
        super(context);
        init();
    }

    /**
     * Creates a board view
     * @param context
     * @param attrs
     */
    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();


    }

    private Paint mLinePaint;
    private Paint mWhitePaint;

    public BoardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BoardView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        setMeasuredDimension(width, width);
    }


    public void onDraw(Canvas canvas) {
        int cellSize = getWidth() / 8;
        mLinePaint.setStrokeWidth(cellSize/50.0f);
        canvas.drawRect(0, 0, getWidth(), getHeight(), mBackgroundPaint);
        for (int i = 1; i < 8; i++) {
            canvas.drawLine(cellSize * i, 0, cellSize * i, getHeight(), mLinePaint);
            canvas.drawLine(0, cellSize * i, getWidth(), cellSize * i, mLinePaint);
        }

        BoardStructure.OthelloCell cell;
        //Log.d("on Draw:", b.nicerToString());
        for (int r = 0; r < b.BoardSize; r++) {
            for (int c = 0; c < b.BoardSize; c++) {
                cell = b.eval(r, c);

                if (cell == BoardStructure.OthelloCell.BLACK) {
                    canvas.drawCircle(c*cellSize + cellSize/2, r*cellSize+cellSize/2, .35f*cellSize, mLinePaint);
                    //System.out.println("BLACK CELL"+ r+ ", "+ c);
                } else if (cell == BoardStructure.OthelloCell.WHITE) {

                    canvas.drawCircle(c*cellSize + cellSize/2, r*cellSize+cellSize/2, 0.35f*cellSize, mWhitePaint);
                    //System.out.println("WHITE CELL at "+ r+ ", "+ c);
                }
            }
        }
    }


    public void updateGame(BoardStructure game) {
        mGame = game;
        postInvalidate();
    }

    public BoardStructure getGame() {
        return mGame;
    }

    private void playerTurn(int x, int y){
        int[] winner = b.evalWin();
        if(winner[0]!=0){
            comp = true;
            if(winner[0]==1){
                mStatus.updateText("You won! Score: "+ winner[1]+" to " +winner[2]);
            }else if(winner[0]==2){
                mStatus.updateText("You lost :( Score: "+winner[1]+" to "+winner[2]);
            }else{
                mStatus.updateText("It was a tie! Score: "+winner[1]+" to "+winner[2]);
            }
            return;
        }
        comp = false;
        if(b.isStuck(true)){
            mStatus.updateText("you have no moves; it is the computer's turn");
            postInvalidate();
            computerTurn();
            return;
        }
        boolean valid = b.placeTile(true, y, x);
        //Log.d("player move", x+", "+y);
        if(valid) {
            postInvalidate();
            computerTurn();
        }
    }

    private final Handler handler = new Handler();

    final Runnable compTurn = new Runnable() {
        @Override
        public void run(){
            comp=true;
            int [] move = b.getGoodMove(false);
            if(move==null){
                mStatus.updateText("computer has no moves; it is your turn");
            }else {
                b.placeTile(false, move[0], move[1]);
            }
            postInvalidate();
            comp=false;
        }
    };

    private void computerTurn(){
        int[] winner = b.evalWin();
        if(winner[0]!=0){
            comp = true;
            if(winner[0]==1){
                mStatus.updateText("You won! Score: "+ winner[1]+" to " +winner[2]);
            }else if(winner[0]==2){
                mStatus.updateText("You lost :( Score: "+winner[1]+" to "+winner[2]);
            }else{
                mStatus.updateText("It was a tie! Score: "+winner[1]+" to "+winner[2]);
            }
            return;
        }
        handler.postDelayed(compTurn, 500);
    }

    private void init(){
        b = new BoardStructure(8);
        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setColor(Color.parseColor("#161616"));
        mLinePaint.setStrokeWidth(4);

        mWhitePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mWhitePaint.setColor(Color.parseColor("#ffffff"));
        mWhitePaint.setStrokeWidth(4);
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(Color.rgb(19, 96, 3));
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //Log.d("wow", "touch event!");
                if (motionEvent.getActionMasked() == MotionEvent.ACTION_DOWN) {
                    //Log.d("event", "down");
                    return true;
                } else if (motionEvent.getActionMasked() == MotionEvent.ACTION_UP) {
                    int cellSize = getWidth() / 8;
                    int x = (int) (motionEvent.getX() / cellSize);
                    int y = (int) (motionEvent.getY() / cellSize);
                    //Log.d("coords", x +"  "+y);
                    if(!comp) playerTurn(x,y);
                    return true;
                }
                return false;
            }
        });
    }
}
