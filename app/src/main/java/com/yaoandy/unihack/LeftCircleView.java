package com.yaoandy.unihack;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class LeftCircleView extends View {


    private Paint mCheckPaint;
    private Paint mCrossPaint;
    private Paint mTextPaint;
    private Paint mGrayPaint;
    private Paint mGrayTextPaint;
    private float mStrokeWidth = 20;
    private float mHalfStrokeWidth = mStrokeWidth / 2;
    private float mRadius = 140;
    private RectF mRect;
    private int mCircleProgress = 0;
    private int mMax = 100;
    private int mWidth;
    private int mHeight;
    private int mCrossProgress = 0;
    private int check1 = 0;


    public LeftCircleView(Context context) {
        super(context);
        init();
    }

    public LeftCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LeftCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        mCheckPaint = new Paint();
        mCheckPaint.setColor(getResources().getColor(R.color.dark_green));
        mCheckPaint.setAntiAlias(true);
        mCheckPaint.setStyle(Paint.Style.STROKE);
        mCheckPaint.setStrokeWidth(mStrokeWidth);

        mCrossPaint = new Paint();
        mCrossPaint.setColor(Color.RED);
        mCrossPaint.setAntiAlias(true);
        mCrossPaint.setStyle(Paint.Style.STROKE);
        mCrossPaint.setStrokeWidth(mStrokeWidth);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getRealSize(widthMeasureSpec);
        mHeight = getRealSize(heightMeasureSpec);
        setMeasuredDimension(mWidth, mHeight);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        initRect();
        if(mCircleProgress <= 360){
            mCircleProgress += 4;
        }
        float angle = mCircleProgress / (float) mMax * 360;
        canvas.drawArc(mRect, -90, angle, false, mCrossPaint);
        if(mCircleProgress > 80 && mCrossProgress <= 140){
            mCrossProgress += 4;
        }
        canvas.drawLine(80, 80, 80 + mCrossProgress, 80 + mCrossProgress, mCrossPaint);
        canvas.drawLine(mWidth - 80, 80, mWidth - 80 - mCrossProgress, 80 + mCrossProgress, mCrossPaint);
        postInvalidate();
    }

    public int getRealSize(int measureSpec) {
        int result = 1;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        if (mode == MeasureSpec.AT_MOST || mode == MeasureSpec.UNSPECIFIED) {
            result = (int) (mRadius * 2 + mStrokeWidth);
        } else {
            result = size;
        }

        return result;
    }

    private void initRect() {
        if (mRect == null) {
            mRect = new RectF();
            int viewSize = (int) (mRadius * 2);
            int left = (mWidth - viewSize) / 2;
            int top = (mHeight - viewSize) / 2;
            int right = left + viewSize;
            int bottom = top + viewSize;
            mRect.set(left, top, right, bottom);
        }
    }


}