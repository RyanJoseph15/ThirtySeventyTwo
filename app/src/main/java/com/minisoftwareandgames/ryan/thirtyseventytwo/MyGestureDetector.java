package com.minisoftwareandgames.ryan.thirtyseventytwo;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by ryan on 12/22/15.
 */
public class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {

    private int swipe_Min_Distance = 25;
    private int swipe_Max_Distance = 2000;
    private int swipe_Min_Velocity = 20;

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {

        try {
            if (Math.abs(e1.getY() - e2.getY()) > swipe_Max_Distance) {
                Log.i("onFling", "not registered: too long");
                return false;
            }
            int topbot = -1; // 1 top to bottom; 0 bottom to top;
            int leftright = -1; // 1 left to right; 0 right to left;
            if ((Math.abs((velocityX + velocityY) / 2)) > swipe_Min_Velocity) {
                double grad = (double) ((e1.getY() - e2.getY()) / (e1.getX() - e2
                        .getX()));
                float theta = (float) Math.toDegrees(Math.atan(grad));
                Log.i("onFling",
                        String.valueOf(theta) + ": X = "
                                + String.valueOf(e1.getX() - e2.getX())
                                + ": Y = "
                                + String.valueOf(e1.getY() - e2.getY()));
                if (Math.abs(theta) >= 75.0 && Math.abs(theta) <= 90.0) {
                    if (e2.getY() - e1.getY() > swipe_Min_Distance) {
                        Log.v("onFling", "Top to Bot");
                        topbot = 1;
                    } else if (e1.getY() - e2.getY() > swipe_Min_Distance) {
                        Log.v("onFling", "Bot to Top");
                        topbot = 0;
                    }
                } else if (theta >= -45.0 && theta <= -15.0) {
                    if (e2.getY() - e1.getY() > swipe_Min_Distance
                            && e1.getX() - e2.getX() > swipe_Min_Distance) {
                        Log.v("onFling", "Diagonal: Top to Bot, Right to Left");
                        topbot = 1;
                        leftright = 0;
                    } else if (e1.getY() - e2.getY() > swipe_Min_Distance
                            && e2.getX() - e1.getX() > swipe_Min_Distance) {
                        Log.v("onFling", "Diagonal: Bot to Top, Left to Right");
                        topbot = 0;
                        leftright = 1;
                    }
                } else if (theta >= 15.0 && theta <= 45.0) {
                    if (e2.getY() - e1.getY() > swipe_Min_Distance
                            && e2.getX() - e1.getX() > swipe_Min_Distance) {
                        Log.v("onFling", "Diagonal: Top to Bot, Left to Right");
                        topbot = 1;
                        leftright = 1;
                    } else if (e1.getY() - e2.getY() > swipe_Min_Distance
                            && e1.getX() - e2.getX() > swipe_Min_Distance) {
                        Log.v("onFling", "Diagonal: Bot to Top, Right to Left");
                        topbot = 0;
                        leftright = 0;
                    }
                }
            }
            // Do appropriate movement
//            Game.move(topbot, leftright);
        } catch (Exception e) {
            Log.i("onFling", "not registered: exception");
        }
        return super.onFling(e1, e2, velocityX, velocityY);
    }

}
