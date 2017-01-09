package com.laval.iut.yokainomori.pages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.laval.iut.yokainomori.R;

/**
 * Created by Tchiq on 09/01/2017.
 */

public class Credits extends Page {

    public ViewGroup root;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.fragment_credits, container, false);

        init();

        return root;
    }

    public void init() {
        ((Button)root.findViewById(R.id.back_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePage(PageName.HOME);
            }
        });
    }

}
