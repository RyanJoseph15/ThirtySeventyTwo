package com.minisoftwareandgames.ryan.thirtyseventytwo.Fragments;

import android.support.v4.app.Fragment;
import android.view.View;

import com.minisoftwareandgames.ryan.thirtyseventytwo.Objects.mShape;

import java.util.ArrayList;

/**
 * Created by ryan on 12/22/15.
 */
public abstract class GameFragment extends Fragment {

    protected int TILECOUNT;
    protected ArrayList<mShape> tiles = new ArrayList<>();

    public GameFragment() {

    }

    protected void saveState() {

    }

    protected void setupGrid(GameFragment gameFragment, View view) {
        if (gameFragment instanceof HexagonalGame) {

        } else if (gameFragment instanceof SquareGame) {

        } else if (gameFragment instanceof TriangleGame) {

        }
    }

}
