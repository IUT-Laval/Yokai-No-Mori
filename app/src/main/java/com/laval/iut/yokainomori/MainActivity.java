package com.laval.iut.yokainomori;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.laval.iut.yokainomori.pages.Game;
import com.laval.iut.yokainomori.pages.Home;
import com.laval.iut.yokainomori.pages.Page;
import com.laval.iut.yokainomori.pages.PageListener;
import com.laval.iut.yokainomori.pages.PageName;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private FragmentTransaction tx;

    private Home home;
    private Game game;

    private Map<PageName, Page> pages;

    private PageName pageActuel;
    private PageName previousPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        home = new Home();
        game = new Game();

        pages = new HashMap<>();
        pages.put(PageName.HOME, home);
        pages.put(PageName.GAME, game);

        for(Map.Entry<PageName, Page> p : pages.entrySet()) {
            p.getValue().addPageListeners(new PageListener() {
                @Override
                public void pageChange(PageName pageName) {
                    changePage(pageName);
                }
            });
        }

        pageActuel = PageName.GAME;
        changePage(pageActuel);

    }

    public void changePage(PageName pageName) {
        if (pages.containsKey(pageName)) {
            if (pageActuel != PageName.SETTINGS) {
                previousPage = pageActuel;
                pageActuel = pageName;
                tx = getSupportFragmentManager().beginTransaction();
                tx.replace(R.id.fragment, pages.get(pageName));
                tx.commit();
            } else {
                pageActuel = previousPage;
                previousPage = PageName.SETTINGS;
                tx = getSupportFragmentManager().beginTransaction();
                tx.replace(R.id.fragment, pages.get(pageActuel));
                tx.commit();
            }
        }
    }

}
