package com.minisoftwareandgames.ryan.thirtyseventytwo;

import android.content.Context;
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

    private Context context;

    private ArrayList<Triangle> mTriangles;
    private ArrayList<Square> mSquares;
    private ArrayList<Hexagon> mHexagons;
    private float mAngle;

    public MyGLRenderer(Context context) {
        super();
        this.context = context;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        addTriangles();
        addSquares(gl);
        addHexagons(gl);

        gl.glEnable(GL10.GL_TEXTURE_2D);			//Enable Texture Mapping ( NEW )
        gl.glShadeModel(GL10.GL_SMOOTH); 			//Enable Smooth Shading
        gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f); 	//Black Background
        gl.glClearDepthf(1.0f); 					//Depth Buffer Setup
        gl.glEnable(GL10.GL_DEPTH_TEST); 			//Enables Depth Testing
        gl.glDepthFunc(GL10.GL_LEQUAL); 			//The Type Of Depth Testing To Do

        // Really Nice Perspective Calculations
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
    }

    private void addTriangles() {
        // we need 16 triangles
        if (mTriangles == null) {
            mTriangles = new ArrayList<>();
            for (int index = 0; index < 16; index++) {
                mTriangles.add(new Triangle());
            }
            float base = 0.0f;
            float FirstC = 0.6f;
            float SecondC = 0.5f;
            float ThirdC = 0.3f;
            float FourthC = 0.2f;
            float FifthC = 0.0f;
            float SixthC = -0.2f;
            float SeventhC = -0.3f;
            // left to right - first column
            mTriangles.get(0).adjustCoordinates(FirstC, FirstC, base);
//            mTriangles.get(1).adjustCoordinates(SecondC, FirstC, base);
        }
    }

    private void addSquares(GL10 gl) {
        // we need 16 squares
        if (mSquares == null) {
            mSquares = new ArrayList<>();
            for (int index = 0; index < 16; index++) {
                Square square = new Square();
                square.loadGLTexture(gl, context, "0");
                mSquares.add(square);
            }
            float First = 0.555f;
            float Second = 0.185f;
            float Third = 0 - Second;
            float Fourth = 0 - First;
            // left to right - first column     x     y     z
            mSquares.get(0).adjustCoordinates(First, First, 0.0f);
            mSquares.get(1).adjustCoordinates(First, Second, 0.0f);
            mSquares.get(2).adjustCoordinates(First, Third, 0.0f);
            mSquares.get(3).adjustCoordinates(First, Fourth, 0.0f);

            // second column
            mSquares.get(4).adjustCoordinates(Second, First, 0.0f);
            mSquares.get(5).adjustCoordinates(Second, Second, 0.0f);
            mSquares.get(6).adjustCoordinates(Second, Third, 0.0f);
            mSquares.get(7).adjustCoordinates(Second, Fourth, 0.0f);

            // third column
            mSquares.get(8).adjustCoordinates(Third, First, 0.0f);
            mSquares.get(9).adjustCoordinates(Third, Second, 0.0f);
            mSquares.get(10).adjustCoordinates(Third, Third, 0.0f);
            mSquares.get(11).adjustCoordinates(Third, Fourth, 0.0f);

            // fourth column
            mSquares.get(12).adjustCoordinates(Fourth, First, 0.0f);
            mSquares.get(13).adjustCoordinates(Fourth, Second, 0.0f);
            mSquares.get(14).adjustCoordinates(Fourth, Third, 0.0f);
            mSquares.get(15).adjustCoordinates(Fourth, Fourth, 0.0f);
        }
    }

    private void addHexagons(GL10 gl) {
        // we need to add 19 hexagons
        if (mHexagons == null) {
            mHexagons = new ArrayList<>();
            for (int index = 0; index < 19; index++) {
                Hexagon hexagon = new Hexagon();
                hexagon.loadGLTexture(gl, context, "0");
                mHexagons.add(hexagon);
            }
            float LMC = 0.56f;              // left most column
            float MLC = 0.28f;              // Mid left column
            float MRC = 0 - MLC;            // Mid right column
            float RMC = 0 - LMC;            // Right most column
            float OddTR = 0.65f;            // Odd column Top row
            float OddTMR = 0.325f;          // Odd column Top Middle row
            float EvenTR = 0.4875f;         // Even column Top row
            float EvenTMR = 0.1625f;        // Even column Top Middle row
            float OddBMR = 0 - OddTMR;      // Odd column Bottom Middle row
            float OddBR = 0 - OddTR;        // Odd column Bottom row
            float EvenBMR = 0 - EvenTMR;    // Even column Bottom middle row
            float EvenBR = 0 - EvenTR;      // Even column Bottom row
            // left most column                 x      y      z
            mHexagons.get(0).adjustCoordinates(LMC, OddTMR, 0.0f);
            mHexagons.get(1).adjustCoordinates(LMC, 0.0f, 0.0f);
            mHexagons.get(2).adjustCoordinates(LMC, OddBMR, 0.0f);

            // left middle column
            mHexagons.get(3).adjustCoordinates(MLC, EvenTR, 0.0f);
            mHexagons.get(4).adjustCoordinates(MLC, EvenTMR, 0.0f);
            mHexagons.get(5).adjustCoordinates(MLC, EvenBMR, 0.0f);
            mHexagons.get(6).adjustCoordinates(MLC, EvenBR, 0.0f);

            // center column
            mHexagons.get(7).adjustCoordinates(0.0f, OddTR, 0.0f);
            mHexagons.get(8).adjustCoordinates(0.0f, OddTMR, 0.0f);
            mHexagons.get(9).adjustCoordinates(0.0f, 0.0f, 0.0f);       // center block
            mHexagons.get(10).adjustCoordinates(0.0f, OddBMR, 0.0f);
            mHexagons.get(11).adjustCoordinates(0.0f, OddBR, 0.0f);

            // right middle column
            mHexagons.get(12).adjustCoordinates(MRC, EvenTR, 0.0f);
            mHexagons.get(13).adjustCoordinates(MRC, EvenTMR, 0.0f);
            mHexagons.get(14).adjustCoordinates(MRC, EvenBMR, 0.0f);
            mHexagons.get(15).adjustCoordinates(MRC, EvenBR, 0.0f);

            // right most column
            mHexagons.get(16).adjustCoordinates(RMC, OddTMR, 0.0f);
            mHexagons.get(17).adjustCoordinates(RMC, 0.0f, 0.0f);
            mHexagons.get(18).adjustCoordinates(RMC, OddBMR, 0.0f);
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


        // Draw triangle
//        for (Triangle triangle : mTriangles) {
//            triangle.draw(gl);
//        }
        // Draw squares
//        for (Square square : mSquares) {
//            square.draw(gl);
//        }
        // Draw hexagons
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