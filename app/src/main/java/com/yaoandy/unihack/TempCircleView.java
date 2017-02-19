package com.yaoandy.unihack;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class TempCircleView extends View {


    private Paint mBackPaint;
    private Paint mFrontPaint;
    private Paint mTextPaint;
    private Paint mGrayPaint;
    private Paint mGrayTextPaint;
    private float mStrokeWidth = 10;
    private float mHalfStrokeWidth = mStrokeWidth / 2;
    private float mRadius = 200;
    private RectF mRect;
    private int mMax = 100;
    private int mWidth;
    private int mHeight;
    private float textSize = 120;


    public TempCircleView(Context context) {
        super(context);
        init();
    }

    public TempCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TempCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        mBackPaint = new Paint();
        mBackPaint.setColor(Color.WHITE);
        mBackPaint.setAntiAlias(true);
        mBackPaint.setStyle(Paint.Style.STROKE);
        mBackPaint.setStrokeWidth(mStrokeWidth);

        mFrontPaint = new Paint();
        mFrontPaint.setColor(getResources().getColor(R.color.colorPrimary));
        mFrontPaint.setAntiAlias(true);
        mFrontPaint.setStyle(Paint.Style.STROKE);
        mFrontPaint.setStrokeWidth(mStrokeWidth);


        mTextPaint = new Paint();
        mTextPaint.setColor(getResources().getColor(R.color.colorPrimaryDark));
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(textSize);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        mGrayTextPaint = new Paint();
        mGrayTextPaint.setColor(Color.GRAY);
        mGrayTextPaint.setAntiAlias(true);
        mGrayTextPaint.setTextSize(65);
        mGrayTextPaint.setTextAlign(Paint.Align.CENTER);

        mGrayPaint = new Paint();
        mGrayPaint.setColor(getResources().getColor(R.color.gray));
        mGrayPaint.setAntiAlias(true);
        mGrayPaint.setStyle(Paint.Style.STROKE);
        mGrayPaint.setStrokeWidth(mStrokeWidth);
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
        float angle = MainActivity.temp / (float) mMax * 360;
        if(MainActivity.temp == 0){
            canvas.drawCircle(mWidth / 2, mHeight / 2, mRadius, mGrayPaint);
            canvas.drawArc(mRect, -90, angle, false, mFrontPaint);
            canvas.drawText(MainActivity.temp + "°C", mWidth / 2 + mHalfStrokeWidth, mHeight / 2 + mHalfStrokeWidth + textSize / 2 - 10, mGrayTextPaint);
            postInvalidate();
            return;
        }
        canvas.drawCircle(mWidth / 2, mHeight / 2, mRadius, mGrayPaint);
        canvas.drawArc(mRect, -90, angle, false, mFrontPaint);
        canvas.drawText(MainActivity.temp + "°C", mWidth / 2 + mHalfStrokeWidth, mHeight / 2 + mHalfStrokeWidth + textSize / 2 - 10, mTextPaint);
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