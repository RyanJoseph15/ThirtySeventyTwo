package com.minisoftwareandgames.ryan.thirtyseventytwo.Objects;

import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by ryan on 12/22/15.
 */
public class Triangle extends mShape {

    private FloatBuffer vertexBuffer;

    // number of coordinates per vertex in this array
    static final int COORDS_PER_VERTEX = 3;
    private float triangleCoords[] = {
            // in counterclockwise order:
            0.0f,  0.1866f, 0.0f,// top
            -0.15f, -0.0933f, 0.0f,// bottom left
            0.15f, -0.0933f, 0.0f // bottom right
    };

    float color[] = { 0.63671875f, 0.76953125f, 0.22265625f, 0.0f };

    /**
     * Sets up the drawing object data for use in an OpenGL ES context.
     */
    public Triangle() {
        setUp();
    }

    public Triangle(float xCoor, float yCoor, float zCoor) {
        adjustCoordinates(xCoor, yCoor, zCoor);
        setUp();
    }

    private void setUp() {
        // initialize vertex byte buffer for shape coordinates
        ByteBuffer bb = ByteBuffer.allocateDirect(
                // (number of coordinate values * 4 bytes per float)
                triangleCoords.length * 4);
        // use the device hardware's native byte order
        bb.order(ByteOrder.nativeOrder());

        // create a floating point buffer from the ByteBuffer
        vertexBuffer = bb.asFloatBuffer();
        // add the coordinates to the FloatBuffer
        vertexBuffer.put(triangleCoords);
        // set the buffer to read the first coordinate
        vertexBuffer.position(0);
    }

    public void adjustCoordinates(float xCoor, float yCoor, float zCoor) {
        int length = triangleCoords.length;
        for (int index = 0; index < length; index++) {
            if (index % 3 == 0) {
                triangleCoords[index] += xCoor;
            } else if (index % 3 == 1) {
                triangleCoords[index] += yCoor;
            } else if (index % 3 == 2) {
                triangleCoords[index] += zCoor;
            }
        }
        setUp();
    }

    public void invert() {
        int length = triangleCoords.length;
        for (int index = 0; index < length; index++) {
            if (index % 3 == 1) {
                // inverts the y axis
                triangleCoords[index] = 0 - triangleCoords[index];
            }
        }
    }

    private void LogCoords() {
        int length = triangleCoords.length;
        Log.d("LogCoords", " below ");
        for (int index = 0; index < length; index++) {
            if (index % 3 == 2) Log.d("LogCoords", "" +
                            triangleCoords[index - 2] + ", " +
                            triangleCoords[index - 1] + ", " +
                            triangleCoords[index]
            );
        }
    }

    /**
     * Encapsulates the OpenGL ES instructions for drawing this shape.
     *
     * @param gl - The OpenGL ES context in which to draw this shape.
     */
    public void draw(GL10 gl) {
        // Since this shape uses vertex arrays, enable them
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        // draw the shape
        gl.glColor4f(       // set color:
                color[0], color[1],
                color[2], color[3]);
        gl.glVertexPointer( // point to vertex data:
                COORDS_PER_VERTEX,
                GL10.GL_FLOAT, 0, vertexBuffer);
        gl.glDrawArrays(    // draw shape:
                GL10.GL_TRIANGLES, 0,
                triangleCoords.length / COORDS_PER_VERTEX);

        // Disable vertex array drawing to avoid
        // conflicts with shapes that don't use it
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }

}
