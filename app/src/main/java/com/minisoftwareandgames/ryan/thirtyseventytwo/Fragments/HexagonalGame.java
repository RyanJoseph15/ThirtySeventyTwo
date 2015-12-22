package com.minisoftwareandgames.ryan.thirtyseventytwo.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minisoftwareandgames.ryan.thirtyseventytwo.R;

/**
 * Created by ryan on 12/22/15.
 */
public class HexagonalGame extends GameFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hexagonal_game_fragment, container, false);
        super.setupGrid(this, view);
        return view;
    }



}
