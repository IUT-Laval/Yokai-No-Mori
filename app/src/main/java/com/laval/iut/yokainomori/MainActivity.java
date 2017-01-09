package com.laval.iut.yokainomori;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.laval.iut.yokainomori.pages.Credits;
import com.laval.iut.yokainomori.pages.Game;
import com.laval.iut.yokainomori.pages.Home;
import com.laval.iut.yokainomori.pages.Page;
import com.laval.iut.yokainomori.pages.PageListener;
import com.laval.iut.yokainomori.pages.PageName;
import com.laval.iut.yokainomori.pages.Rules;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private FragmentTransaction tx;

    private Home home;
    private Game game;
    private Rules rules;
    private Credits credits;

    private Map<PageName, Page> pages;

    private PageName pageActuel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        home = new Home();
        game = new Game();
        rules = new Rules();




        pages = new HashMap<>();
        pages.put(PageName.HOME, home);
        pages.put(PageName.GAME, game);
        pages.put(PageName.RULES, rules);

        for(Map.Entry<PageName, Page> p : pages.entrySet()) {
            p.getValue().addPageListeners(new PageListener() {
                @Override
                public void pageChange(PageName pageName) {
                    changePage(pageName);
                }
            });
        }

        pageActuel = PageName.HOME;
        changePage(pageActuel);


    }



    public void changePage(PageName pageName) {

        if (pages.containsKey(pageName)) {
            tx = getSupportFragmentManager().beginTransaction();
            if (!pageActuel.equals(PageName.HOME)) {
                switch (pageActuel) {
                    case CREDIT:
                        tx.setCustomAnimations(R.anim.slide_in_bot, R.anim.slide_out_bot);
                        break;
                    case GAME:
                        tx.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right);
                        break;
                    case RULES:
                        tx.setCustomAnimations(R.anim.slide_in_bot, R.anim.slide_out_bot);
                        break;
                    default:
                        break;
                }
            } else {
                switch (pageName) {
                    case CREDIT:
                        tx.setCustomAnimations(R.anim.slide_in_top, R.anim.slide_out_top);
                        break;
                    case GAME:
                        tx.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);
                        break;
                    case RULES:
                        tx.setCustomAnimations(R.anim.slide_in_top, R.anim.slide_out_top);
                        break;
                    default:
                        break;
                }
            }
            pageActuel = pageName;

            tx.replace(R.id.fragment, pages.get(pageName)).commit();
        }
        else
            finish();
    }
    @Override
    public void onBackPressed(){
        if (pageActuel.equals(PageName.GAME))
            game.displayExitDialog();
        else if (!pageActuel.equals(PageName.HOME))
            changePage(PageName.HOME);
        else
            super.onBackPressed();
    }

    public Game getGame() {
        return game;
    }
}
