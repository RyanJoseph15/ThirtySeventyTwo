package com.minisoftwareandgames.ryan.thirtyseventytwo.Objects;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by ryan on 12/22/15.
 */
public class Hexagon extends mShape {

    private FloatBuffer vertexBuffer;
    private ShortBuffer drawListBuffer;

    static final int COORDS_PER_VERTEX = 3;
    private float hexagonCoords[] = {
            -0.09f, 0.15588f, 0.0f,  // top left
            0.09f, 0.15588f, 0.0f,   // top right
            0.18f, 0.0f, 0.0f,  // mid right
            0.09f, -0.15588f, 0.0f,  // bot right
            -0.09f, -0.15588f, 0.0f, // bot left
            -0.18f, 0.0f, 0.0f  // mid left
    };

    // the hexagon needs to be built with triangles so these are 4 that will do it.
    private final short drawOrder[] = { 0,4,5, 0,1,4, 3,1,4, 1,2,3 }; // order to draw vertices

    float color[] = { 0.2f, 0.709803922f, 0.898039216f, 1.0f };

    public Hexagon() {
        setUp();
    }

    public Hexagon(float xCoor, float yCoor, float zCoor) {
        adjustCoordinates(xCoor, yCoor, zCoor);
        setUp();
    }

    private void setUp() {
        // initialize vertex byte buffer for shape coordinates
        ByteBuffer bb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 4 bytes per float)
                hexagonCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(hexagonCoords);
        vertexBuffer.position(0);

        // initialize byte buffer for the draw list
        ByteBuffer dlb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 2 bytes per short)
                drawOrder.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        drawListBuffer = dlb.asShortBuffer();
        drawListBuffer.put(drawOrder);
        drawListBuffer.position(0);
    }

    public void adjustCoordinates(float xCoor, float yCoor, float zCoor) {
        int length = hexagonCoords.length;
        for (int index = 0; index < length; index++) {
            if (index % 3 == 0) {
                hexagonCoords[index] += xCoor;
            } else if (index % 3 == 1) {
                hexagonCoords[index] += yCoor;
            } else if (index % 3 == 2) {
                hexagonCoords[index] += zCoor;
            }
        }
        setUp();
    }

    public void draw(GL10 gl) {
        // Since this shape uses vertex arrays, enable them
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        // draw the shape
        gl.glColor4f(       // set color
                color[0], color[1],
                color[2], color[3]);
        gl.glVertexPointer( // point to vertex data:
                COORDS_PER_VERTEX,
                GL10.GL_FLOAT, 0, vertexBuffer);
        gl.glDrawElements(  // draw shape:
                GL10.GL_TRIANGLES,
                drawOrder.length, GL10.GL_UNSIGNED_SHORT,
                drawListBuffer);

        // Disable vertex array drawing to avoid
        // conflicts with shapes that don't use it
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }

}
