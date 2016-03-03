package com.steinsvik.fabricmanagaer.tabs;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.steinsvik.fabricmanagaer.R;

/**
 * Created by Gigabyte on 3/1/2016.
 */
public class MyTabLayout extends TabLayout {

    public MyTabLayout(Context context) {
        super(context);
    }

    public MyTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void createTabs(){
        addTab("DATE");
        addTab("MONTH");
        addTab("YEAR");
    }

    private void addTab(String contentDescription) {
        View tabView = LayoutInflater.from(getContext()).inflate(R.layout.tab_icon, null);
        TextView title = (TextView) tabView.findViewById(R.id.tabTitle);
        title.setText(contentDescription);
        Tab tab = newTab();
        tab.setCustomView(tabView);
        addTab(tab);
    }
}
