package com.laval.iut.yokainomori;

import android.content.ClipData;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private final int lines = 4;
    private final int rows = 3;
    private ImageView[][] boardPawns;
    //private List<TableRow> listTableRows = new ArrayList<>();
    //private List<LinearLayout> listCases = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        final LinearLayout board = (LinearLayout) findViewById(R.id.board);

        boardPawns = new ImageView[lines][rows];

        for (int i = 0;i<lines;i++) {
            LinearLayout tempLinearLayout = new LinearLayout(this);
            LinearLayout.LayoutParams paramsTempLinearLayout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, .2f);
            tempLinearLayout.setLayoutParams(paramsTempLinearLayout);
            tempLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
            for (int j = 0;j<rows;j++) {
                final ImageView caseBoard = new ImageView(this);
                caseBoard.setImageResource(R.drawable.empty);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, .25f);
                params.setMargins(10, 0, 10, 10);
                caseBoard.setLayoutParams(params);
                tempLinearLayout.addView(caseBoard);
                boardPawns[i][j] = caseBoard;
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
                            //System.out.println("koukou touch "+iFinal+" : "+jFinal);
                            //v.setVisibility(View.INVISIBLE);
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
                                //System.out.println("koukou start");
                                break;
                            case DragEvent.ACTION_DRAG_ENTERED:
                                //System.out.println("koukou enter");
                                break;
                            case DragEvent.ACTION_DRAG_EXITED :
                                //System.out.println("koukou exit");
                                break;
                            case DragEvent.ACTION_DRAG_LOCATION:
                                //System.out.println("koukou location");
                                break;
                            case DragEvent.ACTION_DRAG_ENDED:
                                //System.out.println("koukou ended");
                                break;
                            case DragEvent.ACTION_DROP:
                                //System.out.println("koukou drop "+iFinal+" : "+jFinal);
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

    }

    public void setPawn(int x, int y) {
        boardPawns[x][y].setImageResource(R.drawable.kirin);
    }

    public void removePawn(int x, int y) {
        boardPawns[x][y].setImageResource(R.drawable.empty);
    }

}
