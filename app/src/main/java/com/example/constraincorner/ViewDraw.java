package com.example.constraincorner;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

public class ViewDraw extends ViewGroup {
    private Paint paint = new Paint();
    public ViewDraw(Context context) {
        super(context);
        init();
    }

    public ViewDraw(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ViewDraw(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ViewDraw(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    private void init(){
        paint.setColor(Color.RED);
        paint.setTextSize(100);
        paint.setStrokeWidth(50);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        canvas.drawLine(0f, 0f, 100, 200, paint);
        canvas.drawArc(new RectF(100,100,400,400), 0f, 360f, true, paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(50);
        canvas.drawCircle(600,600, 500, paint);
        Log.d("xuedi", "dispatchdraw");
    }
}
