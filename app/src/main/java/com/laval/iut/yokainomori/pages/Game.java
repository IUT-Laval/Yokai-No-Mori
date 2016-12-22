package com.laval.iut.yokainomori.pages;

import android.content.ClipData;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
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
import com.laval.iut.yokainomori.core.Jeu56;
import com.laval.iut.yokainomori.core.JeuListener;
import com.laval.iut.yokainomori.core.Joueur;
import com.laval.iut.yokainomori.core.JoueurListener;
import com.laval.iut.yokainomori.core.Pion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lione on 10/12/2016.
 */

public class Game extends Page {

    private ViewGroup root;

    private Jeu jeu;

    private int selectedX = 0;
    private int selectedY = 0;
    private Pion selectedPawn;
    private Integer indexSelectedPawn;

    private int lines;
    private int rows;
    private ImageView[][] boardPawns;
    private Map<String, LinearLayout> reserveLinearLayouts;
    private Map<String, List<ImageView>> listReserve;
    private Map<String, LinearLayout> currentPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.fragment_game, null);

        jeu = new Jeu56();
        lines = jeu.getPlateau().getHauteur();
        rows = jeu.getPlateau().getLargeur();

        final LinearLayout winPanel = (LinearLayout) root.findViewById(R.id.winPanel);

        jeu.addJeuListeners(new JeuListener() {
            @Override
            public void init() {
            }

            @Override
            public void deplacePion(Pion pion) {
                setPawn(
                    pion.getImg(),
                    jeu.getGestionnairePion().getKey(pion).getX(),
                    jeu.getGestionnairePion().getKey(pion).getY(),
                    jeu.isRetourne(pion)
                );
            }

            @Override
            public void capturePion(Pion pion, String nomJoueur) {
                capture(pion.getImg(), nomJoueur, jeu.isRetourne(pion));
            }

            @Override
            public void finPartie(int gagnant) {
                winPanel.setVisibility(View.VISIBLE);
                if (gagnant == 1)
                    winPanel.setRotation(180);
            }
        });

        jeu.getGestionnaireJoueur().addJoueurListeners(new JoueurListener() {
            @Override
            public void changeJoueur(Joueur joueur) {
                setCurrentPlayer(joueur.getNom());
            }
        });

        final LinearLayout board = (LinearLayout) root.findViewById(R.id.board);

        lines = jeu.getPlateau().getHauteur();
        rows = jeu.getPlateau().getLargeur();

        //boardPawns = new ImageView[lines][rows];
        boardPawns = new ImageView[rows][lines];
        reserveLinearLayouts = new ArrayMap<>();
        reserveLinearLayouts.put(jeu.getGestionnaireJoueur().getJoueur(0).getNom(), (LinearLayout) root.findViewById(R.id.reserve2));
        reserveLinearLayouts.put(jeu.getGestionnaireJoueur().getJoueur(1).getNom(), (LinearLayout) root.findViewById(R.id.reserve1));

        listReserve = new ArrayMap<>();
        listReserve.put(jeu.getGestionnaireJoueur().getJoueur(0).getNom(), new ArrayList());
        listReserve.put(jeu.getGestionnaireJoueur().getJoueur(1).getNom(), new ArrayList());

        currentPlayer = new ArrayMap<>();
        currentPlayer.put(jeu.getGestionnaireJoueur().getJoueur(0).getNom(), (LinearLayout) root.findViewById(R.id.active2));
        currentPlayer.put(jeu.getGestionnaireJoueur().getJoueur(1).getNom(), (LinearLayout) root.findViewById(R.id.active1));

        for (int i = lines-1;i>=0;i--) {
            LinearLayout tempLinearLayout = new LinearLayout(root.getContext());
            LinearLayout.LayoutParams paramsTempLinearLayout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, .2f);
            tempLinearLayout.setLayoutParams(paramsTempLinearLayout);
            tempLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
            for (int j = 0;j<rows;j++) {
                final ImageView caseBoard = new ImageView(root.getContext());
                if (jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[j][i]) != null) {
                    caseBoard.setImageResource(jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[j][i]).getImg());
                    if (jeu.isRetourne(jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[j][i])))
                        caseBoard.setRotation(180);
                    else
                        caseBoard.setRotation(0);
                } else
                    caseBoard.setImageResource(R.drawable.empty);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, .25f);
                params.setMargins(10, 0, 10, 10);
                caseBoard.setLayoutParams(params);
                tempLinearLayout.addView(caseBoard);
                boardPawns[j][i] = caseBoard;
                final int iFinal = i;
                final int jFinal = j;

                caseBoard.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            ClipData data = ClipData.newPlainText("", "");
                            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                            v.startDrag(data, shadowBuilder, v, 0);
                            //selectedX = jFinal;
                            //selectedY = iFinal;
                            selectedPawn = jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[jFinal][iFinal]);
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
                                if (selectedPawn != null) {
                                    int oldX = 0;
                                    int oldY = 0;
                                    boolean capture = true;
                                    String nomJoueur = jeu.getGestionnaireJoueur().getJoueurActuel().getNom();
                                    if (jeu.getGestionnairePion().getKey(selectedPawn) != null) {
                                        oldX = jeu.getGestionnairePion().getKey(selectedPawn).getX();
                                        oldY = jeu.getGestionnairePion().getKey(selectedPawn).getY();
                                        capture = false;
                                    }
                                    boolean done = jeu.jouer(
                                            //jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[selectedX][selectedY]),
                                            selectedPawn,
                                            jeu.getPlateau().getCases()[jFinal][iFinal]
                                    );
                                    if (done) {
                                        if (!capture)
                                            removePawn(oldX, oldY);
                                        else
                                            removePawn(nomJoueur, indexSelectedPawn);
                                    }
                                }
                                indexSelectedPawn = null;
                                selectedPawn = null;
                                //setPawn(iFinal, jFinal);
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

        setCurrentPlayer(jeu.getGestionnaireJoueur().getJoueurActuel().getNom());


        final LinearLayout pausePanel = (LinearLayout) root.findViewById(R.id.pausePanel);
        ImageView pause1 = (ImageView) root.findViewById(R.id.pause1);
        ImageView pause2 = (ImageView) root.findViewById(R.id.pause2);
        pause1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pausePanel.setRotation(180);
                pausePanel.setVisibility(View.VISIBLE);
            }
        });
        pause2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pausePanel.setVisibility(View.VISIBLE);
            }
        });

        return root;
    }

    public void capture(int img, final String nomJoueur, boolean isRetourne) {
        final ImageView caseBoard = new ImageView(root.getContext());
        if (isRetourne)
            caseBoard.setRotation(180);
        else
            caseBoard.setRotation(0);
        caseBoard.setImageResource(img);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, .25f);
        params.setMargins(50, 10, 50, 10);
        caseBoard.setLayoutParams(params);

        listReserve.get(nomJoueur).add(caseBoard);
        reserveLinearLayouts.get(nomJoueur).addView(caseBoard);

        caseBoard.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Joueur j = jeu.getGestionnaireJoueur().getJoueur(nomJoueur);
                    if (j != null) {
                        if (listReserve.get(nomJoueur).indexOf(caseBoard) >= 0 && listReserve.get(nomJoueur).indexOf(caseBoard) < j.getReserve().size()) {
                            ClipData data = ClipData.newPlainText("", "");
                            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                            v.startDrag(data, shadowBuilder, v, 0);
                            selectedPawn = j.getReserve().get(listReserve.get(nomJoueur).indexOf(caseBoard));
                            indexSelectedPawn = listReserve.get(nomJoueur).indexOf(caseBoard);
                        }
                    }
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    public void setPawn(int image, int x, int y, boolean isRetourne) {
        if (isRetourne)
            boardPawns[x][y].setRotation(180);
        else
            boardPawns[x][y].setRotation(0);
        boardPawns[x][y].setImageResource(image);
    }

    public void removePawn(int x, int y) {
        boardPawns[x][y].setImageResource(R.drawable.empty);
    }

    public void removePawn(String nomJoueur, int index) {
        reserveLinearLayouts.get(nomJoueur).removeView(listReserve.get(nomJoueur).get(index));
        listReserve.get(nomJoueur).remove(index);
    }

    public void setCurrentPlayer(String nomJoueur) {
        for(Map.Entry<String, LinearLayout> entry : currentPlayer.entrySet()) {
            entry.getValue().setBackgroundColor(getResources().getColor(R.color.inactive));
        }
        currentPlayer.get(nomJoueur).setBackgroundColor(getResources().getColor(R.color.active));
    }

}
