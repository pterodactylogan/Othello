package com.pterodactylogan.othello;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by demouser on 1/13/17.
 */

public class BoardView extends View {

    private Paint mBackgroundPaint;

    public BoardView(Context context) {
        super(context);
        init();
    }

    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

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
        if (widthMeasureSpec == 0 || heightMeasureSpec == 0) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
        int size = Math.min(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(size, size);
    }

    public void onDraw(Canvas canvas){
        canvas.drawRect(0,0, getWidth(), getHeight(), mBackgroundPaint);

    }

    private void init(){
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(Color.rgb(0,200,0));
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                event.getX(); //this is the x coordiate of the touch
                event.getY(); //this is the y coodrinate of the touch
                event.getActionMasked(); //tells you whether it was a down/up press
                return false;
            }
        });
    }
}
