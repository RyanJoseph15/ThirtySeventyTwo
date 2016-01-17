package com.minisoftwareandgames.ryan.thirtyseventytwo.Objects;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;

/**
 * Created by ryan on 12/22/15.
 */
public abstract class mShape {

    protected Bitmap StringToBitmap(String text) {

        int size = 256;
        Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(200);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        Rect textBounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), textBounds);
        canvas.drawText(text, size/2, size/2 - (paint.descent() + paint.ascent())/2, paint);

        return bitmap;
    }

    public abstract void adjustCoordinates(float xCoor, float yCoor, float zCoor);

}
