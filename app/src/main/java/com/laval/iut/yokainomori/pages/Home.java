package com.laval.iut.yokainomori.pages;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.laval.iut.yokainomori.R;

/**
 * Created by lione on 10/12/2016.
 */
public class Home extends Page {

    private ViewGroup root;
    private ImageButton boutonJouer;
    private ImageButton boutonQuitter;
    protected ImageButton boutonCredits;
    protected ImageButton boutonRegles;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        init();

        return root;
    }

    public void init() {

        ImageButton boutonJouer = (ImageButton) root.findViewById(R.id.boutonJouer);
        ImageButton boutonCredits = (ImageButton) root.findViewById(R.id.boutonCredits);
        ImageButton boutonRegles = (ImageButton) root.findViewById(R.id.boutonRegles);
        ImageButton boutonQuitter = (ImageButton) root.findViewById(R.id.boutonQuitter);



        // LIsteners pour ouvrir les pages
        boutonJouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        boutonCredits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        boutonRegles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        boutonQuitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // Touch Listeners pour bien mettre en évidence quand on appuie sur un bouton //
        boutonJouer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    // Pressed
                    case MotionEvent.ACTION_DOWN :
                        boutonJouer.getBackground().setAlpha(160);
                        return true;
                    
                    // Released
                    case MotionEvent.ACTION_UP :
                        boutonJouer.getBackground.setAlpha(255);
                        return true;
                }
                return false;
            }
        });

        boutonCredits.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    // Pressed
                    case MotionEvent.ACTION_DOWN :
                        boutonCredits.getBackground().setAlpha(160);
                        return true;

                    // Released
                    case MotionEvent.ACTION_UP :
                        boutonCredits.getBackground().setAlpha(255);
                        return true;
                }
                return false;
            }
        });

        boutonRegles.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    // Pressed
                    case MotionEvent.ACTION_DOWN :
                        boutonRegles.getBackground().setAlpha(160);
                        return true;

                    // Released
                    case MotionEvent.ACTION_UP :
                        boutonRegles.getBackground().setAlpha(255);
                        return true;
                }
                return false;
            }
        });

        boutonQuitter.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    // Pressed
                    case MotionEvent.ACTION_DOWN :
                        boutonQuitter.getBackground().setAlpha(160);
                        return true;

                    // Released
                    case MotionEvent.ACTION_UP :
                        boutonQuitter.getBackground().setAlpha(255);
                        return true;
                }
                return false;
            }
        });



    }

}
