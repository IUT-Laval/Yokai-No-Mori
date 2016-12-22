package com.laval.iut.yokainomori.pages;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lione on 10/12/2016.
 */

public abstract class Page extends Fragment {

    private List<PageListener> pageListener = new ArrayList<>();

    public void addPageListeners(PageListener l) {
        pageListener.add(l);
    }

    public void changePage(PageName pageName) {
        for (PageListener l : pageListener) {
            l.pageChange(pageName);
        }
    }

}
