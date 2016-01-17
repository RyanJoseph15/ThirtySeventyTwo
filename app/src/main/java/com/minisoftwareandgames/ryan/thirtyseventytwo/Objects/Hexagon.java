package com.minisoftwareandgames.ryan.thirtyseventytwo.Objects;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLUtils;

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
//    private ShortBuffer drawListBuffer;
    static final int COORDS_PER_VERTEX = 3;
    private float hexagonCoords[] = {
            -0.09f, -0.15588f, 0.0f,    // bot left
            -0.18f, 0.0f, 0.0f,         // mid left
            0.09f, -0.15588f, 0.0f,     // bot right
            -0.09f, 0.15588f, 0.0f,     // top left
            0.18f, 0.0f, 0.0f,          // mid right
            0.09f, 0.15588f, 0.0f,      // top right
    };

    private FloatBuffer textureBuffer;
    private int[] textures = new int[1];
    private float texture[] = {
            // Mapping coordinates for the vertices
            0.75f, 1.0f,     // top right
            1.0f, 0.5f,         // mid right
            0.25f, 1.0f,     // top left
            0.75f, 0.0f,     // bot right
            0.0f, 0.5f,         // mid left
            0.25f, 0.0f,     // bot left
    };

    // the hexagon needs to be built with triangles so these are 4 that will do it.
//    private final short drawOrder[] = { 0,4,5, 0,1,4, 3,1,4, 1,2,3 }; // order to draw vertices

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

        // for the texture
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(texture.length * 4);
        byteBuffer.order(ByteOrder.nativeOrder());
        textureBuffer = byteBuffer.asFloatBuffer();
        textureBuffer.put(texture);
        textureBuffer.position(0);
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
        // bind to the previously generated texture
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);

        // Since this shape uses vertex arrays, enable them
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        // draw the shape
        gl.glColor4f(       // set color
                color[0], color[1],
                color[2], color[3]);
        gl.glVertexPointer( // point to vertex data:
                COORDS_PER_VERTEX,
                GL10.GL_FLOAT, 0, vertexBuffer);
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, hexagonCoords.length / 3);    // len/3 per axis

        // Disable vertex array drawing to avoid
        // conflicts with shapes that don't use it
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
    }

    public void loadGLTexture(GL10 gl, Context context, String string) {
        // get bitmap from string to load into the texture
        Bitmap bitmap = StringToBitmap(string);

        // generate one texture pointer
        gl.glGenTextures(1, textures, 0);
        // ...and bind it to our array
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);

        // create nearest filtered texture
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

        // Use Android GLUtils to specify a two-dimensional texture image from our bitmap
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);

        // Clean up
        bitmap.recycle();
    }

}
