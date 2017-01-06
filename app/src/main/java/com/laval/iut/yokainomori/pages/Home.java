package com.laval.iut.yokainomori.pages;

import android.media.Image;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.laval.iut.yokainomori.MainActivity;
import com.laval.iut.yokainomori.R;
import com.laval.iut.yokainomori.core.Jeu34;
import com.laval.iut.yokainomori.core.Jeu56;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lione on 10/12/2016.
 */
public class Home extends Page {

    private ViewGroup root;
    private ImageButton boutonJouer;
    private ImageButton boutonQuitter;
    private ImageButton boutonCredits;
    private ImageButton boutonRegles;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        init();

        return root;
    }
    public void displayOptionsGameDialog(){
        final AlertDialog dialog = new AlertDialog.Builder(root.getContext())
                .setView(getActivity().getLayoutInflater().inflate(R.layout.option_game_dialog, null))
                .setCancelable(true)
                .show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#96000000")));
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.custom_dialog);
        ((Button)(dialog.findViewById(R.id.cancel_button))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        ((Button)(dialog.findViewById(R.id.launch_button))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePage(PageName.GAME);
                dialog.dismiss();
            }
        });
        final Spinner spinnerMode = (Spinner) dialog.findViewById(R.id.spinnerMode);
        final List<String> modes= new ArrayList<String>();
        final String V34 = "version 3*4";
        final String V56 = "version 5*6";
        modes.add(V34);
        modes.add(V56);
        // ajout des customs
        ArrayAdapter<String> dataAdapterMode = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,modes);
        dataAdapterMode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMode.setAdapter(dataAdapterMode);
        spinnerMode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch ((String)spinnerMode.getSelectedItem()){
                    case V34:
                        ((MainActivity)getContext()).getGame().setJeu(new Jeu34());
                                break;
                    case V56:
                        ((MainActivity)getContext()).getGame().setJeu(new Jeu56());
                        break;
                    default:
                        // ajout en parametre le xml
                        // le nom de la classe jeu 56 changera en jeuStandard par exemple
                        ((MainActivity)getContext()).getGame().setJeu(new Jeu56());
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ((MainActivity)getContext()).getGame().setJeu(new Jeu56());
            }
        });
    }


    public void init() {

        boutonJouer = (ImageButton) root.findViewById(R.id.boutonJouer);
        boutonCredits = (ImageButton) root.findViewById(R.id.boutonCredits);
        boutonRegles = (ImageButton) root.findViewById(R.id.boutonRegles);
        boutonQuitter = (ImageButton) root.findViewById(R.id.boutonQuitter);



        // Listeners pour ouvrir les pages
        boutonJouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayOptionsGameDialog();
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
                        boutonJouer.getBackground().setAlpha(255);
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
