package com.laval.iut.yokainomori.pages;

import android.app.AlertDialog;
import android.content.ClipData;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.util.ArrayMap;
import android.support.v4.widget.DrawerLayout;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.laval.iut.yokainomori.R;
import com.laval.iut.yokainomori.core.Jeu;
import com.laval.iut.yokainomori.core.Jeu34;
import com.laval.iut.yokainomori.core.JeuListener;
import com.laval.iut.yokainomori.core.Joueur;
import com.laval.iut.yokainomori.core.JoueurListener;
import com.laval.iut.yokainomori.core.Pion;
import com.laval.iut.yokainomori.core.PionEvoluable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lione on 10/12/2016.
 */

public class Game extends Page {

    private ViewGroup root;

    private Jeu jeu;

    private Pion selectedPawn;
    private Integer indexSelectedPawn;

    private int lines;
    private int rows;
    private ImageView[][] boardPawns;
    private Map<String, LinearLayout> reserveLinearLayouts;
    private Map<String, List<ImageView>> listReserve;
    //private Map<String, LinearLayout> currentPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.fragment_game, null);

        init();

        return root;
    }

    public void init() {

        ((LinearLayout) root.findViewById(R.id.board)).removeAllViews();
        ((LinearLayout) root.findViewById(R.id.reserve1)).removeAllViews();
        ((LinearLayout) root.findViewById(R.id.reserve2)).removeAllViews();

        jeu = new Jeu34();
        lines = jeu.getPlateau().getHauteur();
        rows = jeu.getPlateau().getLargeur();


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
                displayWinDialog(gagnant);
            }

            @Override
            public void demandeEvolutionPion(PionEvoluable pion) {
                displayEvolutionDialog(pion);
            }

            @Override
            public void evoluePion(Pion pion) {
                changeViewPion(pion);
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
        listReserve.put(jeu.getGestionnaireJoueur().getJoueur(0).getNom(), new ArrayList<ImageView>());
        listReserve.put(jeu.getGestionnaireJoueur().getJoueur(1).getNom(), new ArrayList<ImageView>());


        for (int i = lines - 1; i >= 0; i--) {
            LinearLayout tempLinearLayout = new LinearLayout(root.getContext());
            LinearLayout.LayoutParams paramsTempLinearLayout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, .2f);
            tempLinearLayout.setLayoutParams(paramsTempLinearLayout);
            tempLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
            for (int j = 0; j < rows; j++) {
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
                params.setMargins(10, 5, 10, 5);
                caseBoard.setLayoutParams(params);
                tempLinearLayout.addView(caseBoard);
                boardPawns[j][i] = caseBoard;
                final int iFinal = i;
                final int jFinal = j;

                caseBoard.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            if (jeu.getGestionnaireJoueur().getJoueurActuel().getPions().contains(jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[jFinal][iFinal]))) {
                                ClipData data = ClipData.newPlainText("", "");
                                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                                v.startDrag(data, shadowBuilder, v, 0);
                                //selectedX = jFinal;
                                //selectedY = iFinal;
                                selectedPawn = jeu.getGestionnairePion().get(jeu.getPlateau().getCases()[jFinal][iFinal]);
                            }
                            return true;
                        } else {
                            return false;
                        }
                    }
                });

                caseBoard.setOnDragListener(new View.OnDragListener() {
                    @Override
                    public boolean onDrag(View v, DragEvent event) {
                        switch (event.getAction()) {
                            case DragEvent.ACTION_DRAG_STARTED:
                                break;
                            case DragEvent.ACTION_DRAG_ENTERED:
                                break;
                            case DragEvent.ACTION_DRAG_EXITED:
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
        // menu pause
        final DrawerLayout drawerLayout = (DrawerLayout) root.findViewById(R.id.drawer_layout);
        NavigationView navigationViewJ1 = (NavigationView) root.findViewById(R.id.navigationViewJ1);
        navigationViewJ1.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.quitter:
                                changePage(PageName.HOME);
                                return true;
                            case R.id.recommencer:
                                init();
                                drawerLayout.closeDrawers();
                                return true;
                            case R.id.reprendre:
                                drawerLayout.closeDrawers();
                                return true;
                            case R.id.options:
                                //changePage(PageName.);
                                return true;
                            case R.id.match_nul:
                                drawerLayout.closeDrawers();
                                jeu.matchNul();
                                displayDrawDialog();
                                return true;
                        }
                        return false;
                    }
                });
        NavigationView navigationViewJ2 = (NavigationView) root.findViewById(R.id.navigationViewJ2);
        navigationViewJ2.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.quitter:
                                changePage(PageName.HOME);
                                return true;
                            case R.id.recommencer:
                                init();
                                drawerLayout.closeDrawers();
                                return true;
                            case R.id.reprendre:
                                drawerLayout.closeDrawers();
                                return true;
                            case R.id.options:
                                //changePage(PageName.);
                                return true;
                            case R.id.match_nul:
                                drawerLayout.closeDrawers();
                                jeu.matchNul();
                                displayDrawDialog();
                                return true;
                        }
                        return false;
                    }
                });
    }

    public void capture(int img, final String nomJoueur, boolean isRetourne) {
        final ImageView caseBoard = new ImageView(root.getContext());
        if (isRetourne)
            caseBoard.setRotation(180);
        else
            caseBoard.setRotation(0);
        caseBoard.setImageResource(img);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, .25f);
        params.setMargins(10, 10, 10, 10);
        caseBoard.setAdjustViewBounds(true);
        caseBoard.setLayoutParams(params);

        listReserve.get(nomJoueur).add(caseBoard);
        reserveLinearLayouts.get(nomJoueur).addView(caseBoard);

        caseBoard.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (jeu.getGestionnaireJoueur().getJoueurActuel().getReserve().contains(jeu.getGestionnaireJoueur().getJoueur(nomJoueur).getReserve().get(listReserve.get(nomJoueur).indexOf(caseBoard)))) {
                        ClipData data = ClipData.newPlainText("", "");
                        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                        v.startDrag(data, shadowBuilder, v, 0);
                        selectedPawn = jeu.getGestionnaireJoueur().getJoueur(nomJoueur).getReserve().get(listReserve.get(nomJoueur).indexOf(caseBoard));
                        indexSelectedPawn = listReserve.get(nomJoueur).indexOf(caseBoard);
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

    // change l'aspect des pieces lors d'un changement de joueur
    public void setCurrentPlayer(String nomJoueur) {
        for (ImageView img : listReserve.get(jeu.getGestionnaireJoueur().getJoueurActuel().getNom())) {
            img.clearColorFilter();
        }
        for (ImageView img : listReserve.get(jeu.getGestionnaireJoueur().getJoueurAdverse().getNom())) {
            img.setColorFilter(Color.parseColor("#B2333333"));
        }
        for (Pion pion : jeu.getGestionnaireJoueur().getJoueurActuel().getPions()) {
            int x = jeu.getGestionnairePion().getKey(pion).getX();
            int y = jeu.getGestionnairePion().getKey(pion).getY();
            boardPawns[x][y].clearColorFilter();
        }
        for (Pion pion : jeu.getGestionnaireJoueur().getJoueurAdverse().getPions()) {
            int x = jeu.getGestionnairePion().getKey(pion).getX();
            int y = jeu.getGestionnairePion().getKey(pion).getY();
            boardPawns[x][y].setColorFilter(Color.parseColor("#B2333333"));
        }
    }

    public void displayEvolutionDialog(final PionEvoluable pion) {

        final AlertDialog dialog = new AlertDialog.Builder(root.getContext())
                .setView(getActivity().getLayoutInflater().inflate(R.layout.evolution_dialog, null))
                .setCancelable(false)
                .show();

        ImageView imgPawn = (ImageView) dialog.findViewById(R.id.viewPawn);
        imgPawn.setImageResource(pion.getImg());
        ImageView imgEvolution = (ImageView) dialog.findViewById(R.id.viewEvolution);
        imgEvolution.setImageResource(pion.getEvolution().getImg());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#96000000")));
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.custom_dialog);
        ((Button)(dialog.findViewById(R.id.see_button))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int x = jeu.getGestionnairePion().getKey(pion).getX();
                final int y = jeu.getGestionnairePion().getKey(pion).getY();
                boardPawns[x][y].setColorFilter(Color.parseColor("#70ffd700"));
                root.findViewById(R.id.disable_listener_layout).setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            displayEvolutionDialog(pion);
                            v.setOnTouchListener(null);
                            boardPawns[x][y].clearColorFilter();
                            return true;
                        }
                        else
                            return false;
                    }
                });
                dialog.dismiss();
            }
        });
        ((Button)(dialog.findViewById(R.id.no_button))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jeu.getGestionnaireJoueur().joueurSuivant();
                dialog.dismiss();
            }
        });
        ((Button)(dialog.findViewById(R.id.yes_button))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PionEvoluable pionE = (PionEvoluable) pion;
                pionE.evoluer();
                changeViewPion(pion);
                jeu.getGestionnaireJoueur().joueurSuivant();
                dialog.dismiss();
            }
        });
        if(jeu.getGestionnaireJoueur().getIndexJoueurActuel()==1){
            ((LinearLayout)dialog.findViewById(R.id.dialog_layout)).setRotation(180);
        }

    }

    public void changeViewPion(Pion pion) {
        int x = jeu.getGestionnairePion().getKey(pion).getX();
        int y = jeu.getGestionnairePion().getKey(pion).getY();
        boardPawns[x][y].setImageResource(pion.getImg());
    }
    public void displayWinDialog(int gagnant){
        final AlertDialog dialog = new AlertDialog.Builder(root.getContext())
                .setView(getActivity().getLayoutInflater().inflate(R.layout.win_dialog, null))
                .setCancelable(false)
                .show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#96000000")));
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.custom_dialog);
        ((ImageButton)(dialog.findViewById(R.id.home_button))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePage(PageName.HOME);
                dialog.dismiss();
            }
        });
        ((ImageButton)(dialog.findViewById(R.id.replay_button))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
                dialog.dismiss();
            }
        });
        if (gagnant == 1)
            ((LinearLayout)dialog.findViewById(R.id.dialog_layout)).setRotation(180);
        dialog.getWindow().setLayout((this.getView().getWidth()*3)/4,WindowManager.LayoutParams.WRAP_CONTENT);
    }
    public void displayDrawDialog(){
        final AlertDialog dialog = new AlertDialog.Builder(root.getContext())
                .setView(getActivity().getLayoutInflater().inflate(R.layout.draw_dialog, null))
                .setCancelable(false)
                .show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#96000000")));
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.custom_dialog);
        ((ImageButton)(dialog.findViewById(R.id.home_button))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePage(PageName.HOME);
                dialog.dismiss();
            }
        });
        ((ImageButton)(dialog.findViewById(R.id.replay_button))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
                dialog.dismiss();
            }
        });
        ((ImageButton)(dialog.findViewById(R.id.home_button2))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePage(PageName.HOME);
                dialog.dismiss();
            }
        });
        ((ImageButton)(dialog.findViewById(R.id.replay_button2))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
                dialog.dismiss();
            }
        });
        dialog.getWindow().setLayout((this.getView().getWidth()*3)/4,WindowManager.LayoutParams.WRAP_CONTENT);
    }

}
