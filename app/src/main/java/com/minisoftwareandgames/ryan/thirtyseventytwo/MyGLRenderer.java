package com.minisoftwareandgames.ryan.thirtyseventytwo;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import com.minisoftwareandgames.ryan.thirtyseventytwo.Objects.Hexagon;
import com.minisoftwareandgames.ryan.thirtyseventytwo.Objects.Square;
import com.minisoftwareandgames.ryan.thirtyseventytwo.Objects.Triangle;

import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by ryan on 12/22/15.
 */
public class MyGLRenderer implements GLSurfaceView.Renderer {

    private Triangle mTriangle;
    private Triangle mTriangle2;
    private Square mSquare;
    private ArrayList<Hexagon> mHexagons;
    private float mAngle;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // Set the background frame color
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        mTriangle = new Triangle();
        mTriangle2 = new Triangle();
        mTriangle2.adjustCoordinates(0.2f, 0.2f, 0.0f);
        mSquare = new Square();
        addHexagons();
    }

    private void addHexagons() {
        // we need to add 19 hexagons
        if (mHexagons == null) {
            mHexagons = new ArrayList<>();
            for (int index = 0; index < 19; index++) {
                mHexagons.add(new Hexagon());
            }
            // left most column                 x      y      z
            mHexagons.get(0).adjustCoordinates(0.56f, 0.325f, 0.0f);
            mHexagons.get(1).adjustCoordinates(0.56f, 0.0f, 0.0f);
            mHexagons.get(2).adjustCoordinates(0.56f, -0.325f, 0.0f);

            // left middle column
            mHexagons.get(3).adjustCoordinates(0.28f, 0.4875f, 0.0f);
            mHexagons.get(4).adjustCoordinates(0.28f, 0.1625f, 0.0f);
            mHexagons.get(5).adjustCoordinates(0.28f, -0.1625f, 0.0f);
            mHexagons.get(6).adjustCoordinates(0.28f, -0.4875f, 0.0f);

            // center column
            mHexagons.get(7).adjustCoordinates(0.0f, 0.65f, 0.0f);
            mHexagons.get(8).adjustCoordinates(0.0f, 0.325f, 0.0f);
            mHexagons.get(9).adjustCoordinates(0.0f, 0.0f, 0.0f);       // center block
            mHexagons.get(10).adjustCoordinates(0.0f, -0.325f, 0.0f);
            mHexagons.get(11).adjustCoordinates(0.0f, -0.65f, 0.0f);

            // right middle column
            mHexagons.get(12).adjustCoordinates(-0.28f, 0.4875f, 0.0f);
            mHexagons.get(13).adjustCoordinates(-0.28f, 0.1625f, 0.0f);
            mHexagons.get(14).adjustCoordinates(-0.28f, -0.1625f, 0.0f);
            mHexagons.get(15).adjustCoordinates(-0.28f, -0.4875f, 0.0f);

            // right most column
            mHexagons.get(16).adjustCoordinates(-0.56f, 0.325f, 0.0f);
            mHexagons.get(17).adjustCoordinates(-0.56f, 0.0f, 0.0f);
            mHexagons.get(18).adjustCoordinates(-0.56f, -0.325f, 0.0f);
        }
    }

    @Override
    public void onDrawFrame(GL10 gl) {

        // Draw background color
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        // Set GL_MODELVIEW transformation mode
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();   // reset the matrix to its default state

        // When using GL_MODELVIEW, you must set the view point
        GLU.gluLookAt(gl, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

        // Draw square
//        mSquare.draw(gl);

        // Create a rotation for the triangle

        // Use the following code to generate constant rotation.
        // Leave this code out when using TouchEvents.
        // long time = SystemClock.uptimeMillis() % 4000L;
        // float angle = 0.090f * ((int) time);

//        gl.glRotatef(mAngle, 0.0f, 0.0f, 1.0f);

        // Draw triangle
//        mTriangle.draw(gl);
//        mTriangle2.draw(gl);
        for (Hexagon hexagon : mHexagons) {
            hexagon.draw(gl);
        }
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        // Adjust the viewport based on geometry changes
        // such as screen rotations
        gl.glViewport(0, 0, width, height);

        // make adjustments for screen ratio
        float ratio = (float) width / height;
        gl.glMatrixMode(GL10.GL_PROJECTION);        // set matrix to projection mode
        gl.glLoadIdentity();                        // reset the matrix to its default state
        gl.glFrustumf(-ratio, ratio, -1, 1, 3, 7);  // apply the projection matrix
    }

    /**
     * Returns the rotation angle of the triangle shape (mTriangle).
     *
     * @return - A float representing the rotation angle.
     */
    public float getAngle() {
        return mAngle;
    }

    /**
     * Sets the rotation angle of the triangle shape (mTriangle).
     */
    public void setAngle(float angle) {
        mAngle = angle;
    }

}