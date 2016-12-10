package com.laval.iut.yokainomori.pages;

import android.content.ClipData;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.laval.iut.yokainomori.R;
import com.laval.iut.yokainomori.core.Jeu;
import com.laval.iut.yokainomori.core.Jeu34;

/**
 * Created by lione on 10/12/2016.
 */

public class Game extends Page {

    private ViewGroup root;

    private Jeu jeu;

    private int lines = 4;
    private int rows = 3;
    private ImageView[][] boardPawns;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.fragment_game, null);

        jeu = new Jeu34();

        final LinearLayout board = (LinearLayout) root.findViewById(R.id.board);

        lines = jeu.getPlateau().getHauteur();
        rows = jeu.getPlateau().getLargeur();

        boardPawns = new ImageView[lines][rows];

        for (int i = 0;i<lines;i++) {
            LinearLayout tempLinearLayout = new LinearLayout(root.getContext());
            LinearLayout.LayoutParams paramsTempLinearLayout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, .2f);
            tempLinearLayout.setLayoutParams(paramsTempLinearLayout);
            tempLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
            for (int j = 0;j<rows;j++) {
                final ImageView caseBoard = new ImageView(root.getContext());
                caseBoard.setImageResource(R.drawable.empty);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, .25f);
                params.setMargins(10, 0, 10, 10);
                caseBoard.setLayoutParams(params);
                tempLinearLayout.addView(caseBoard);
                boardPawns[i][j] = caseBoard;
                System.out.println("koukou "+jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[0][0]));
                final int iFinal = i;
                final int jFinal = j;

                caseBoard.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            ClipData data = ClipData.newPlainText("", "");
                            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                            v.startDrag(data, shadowBuilder, v, 0);
                            removePawn(iFinal, jFinal);
                            return true;
                        } else {
                            return false;
                        }
                    }
                });

                caseBoard.setOnDragListener(new View.OnDragListener() {
                    @Override
                    public boolean onDrag(View v, DragEvent event) {
                        switch(event.getAction()) {
                            case DragEvent.ACTION_DRAG_STARTED:
                                break;
                            case DragEvent.ACTION_DRAG_ENTERED:
                                break;
                            case DragEvent.ACTION_DRAG_EXITED :
                                break;
                            case DragEvent.ACTION_DRAG_LOCATION:
                                break;
                            case DragEvent.ACTION_DRAG_ENDED:
                                break;
                            case DragEvent.ACTION_DROP:
                                setPawn(iFinal, jFinal);
                                break;
                            default:
                                break;
                        }
                        return true;
                    }
                });
            }
            board.addView(tempLinearLayout);
        }

        setPawn(1, 2);

        return root;
    }

    public void setPawn(int x, int y) {
        boardPawns[x][y].setImageResource(R.drawable.kirin);
    }

    public void removePawn(int x, int y) {
        boardPawns[x][y].setImageResource(R.drawable.empty);
    }

}
