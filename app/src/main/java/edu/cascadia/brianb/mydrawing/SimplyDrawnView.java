package edu.cascadia.brianb.mydrawing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Edited by Brian Bansenauer on 10/18/15.
 */
public class SimplyDrawnView extends View {

    private Paint mPaint;
    private Path mPath;
    private float mWidth, mHeight;

    public SimplyDrawnView(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        mPaint = new Paint();
        mPath = new Path();
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE); //draw background

        //canvas.drawBitmap(createBeeBitmap(),0,0,null);
        //Draw line
        mPaint.setColor(Color.BLACK);
        canvas.drawLine(mWidth, 0, 200, mHeight, mPaint);

        //Draw red line
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(10);

        //Draw green lines
        int firstGreenLine = 30;
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(10);

        // draw green lines starting at y coordinate = 30 and 50 px afterwards
        for (int i = 0; i <= 5; i++) {
            // reduce alpha by 40 for each cycle
            mPaint.setAlpha(250 - (i*40));
            canvas.drawLine(0, firstGreenLine+(50*i), mWidth, firstGreenLine+(50*i), mPaint);
        }

        //Draw Text
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(24.0f);
        mPaint.setAntiAlias(true);

        float hCenter = mWidth / 2;
        float vCenter = mHeight / 2;

        // draw simple black text
        canvas.drawText(" I am drawing", 0, vCenter, mPaint);

        // draw text on a path
        mPaint.setColor(Color.CYAN);
        mPaint.setTextSize(32f);

        mPath.addArc(5.0f, vCenter, mWidth-30, vCenter+300, 195.0f, 150.0f);


        canvas.drawTextOnPath("drawing on a path can be lots of fun", mPath, 10, 20, mPaint);

        //Draw filled, opaque, and open ovals

        // draw filled gray transparent circle
        mPaint.setColor(Color.GRAY);
        mPaint.setAlpha(50);
        canvas.drawCircle(hCenter, vCenter, 60, mPaint);

        // draw a tiny red filled circle
        mPaint.setColor(Color.RED);
        mPaint.setAlpha(250);
        canvas.drawCircle(hCenter, vCenter, 4, mPaint);

        // draw outer black circle
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(2.5f);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(hCenter, vCenter, 61, mPaint);

        // draw a red line from the center of the screen to the lower right corner
        mPaint.setColor(Color.RED);
        canvas.drawLine(hCenter, vCenter, mWidth, mHeight, mPaint);

        //Draw bee bitmap
        Bitmap tempBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bee);
        canvas.drawBitmap(tempBitmap,0,0, null);

        }

}
