package ru.example.remotetaskmanager.models;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.List;

/**
 * Created by 95tox on 15.10.2016.
 */

public class ParentDrawer implements ParentListItem {

    // a recipe contains several ingredients
    private List childs;
    private String mTitle;

    public ParentDrawer(List childs, String title) {
        this.childs = childs;
        mTitle=title;
    }

    public String getTitle() {
        return mTitle;
    }



    @Override
    public List getChildItemList() {
        return childs;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}