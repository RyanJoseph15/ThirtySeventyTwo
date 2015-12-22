package com.minisoftwareandgames.ryan.thirtyseventytwo.Fragments;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minisoftwareandgames.ryan.thirtyseventytwo.MyGLSurfaceView;
import com.minisoftwareandgames.ryan.thirtyseventytwo.Objects.mShape;
import com.minisoftwareandgames.ryan.thirtyseventytwo.R;

/**
 * Created by ryan on 12/22/15.
 */
public class SquareGame extends GameFragment {

    private GLSurfaceView mGLView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.square_game_fragment, container, false);
//        super.setupGrid(this, view);
        mGLView = new MyGLSurfaceView(getActivity());
        this.getActivity().setContentView(mGLView);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        mGLView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mGLView.onResume();
    }

}
