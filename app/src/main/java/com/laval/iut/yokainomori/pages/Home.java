package com.laval.iut.yokainomori.pages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.laval.iut.yokainomori.R;

/**
 * Created by lione on 10/12/2016.
 */
public class Home extends Page {

    private ViewGroup root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

}
