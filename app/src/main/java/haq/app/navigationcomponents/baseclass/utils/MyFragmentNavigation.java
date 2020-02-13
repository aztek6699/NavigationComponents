package haq.app.navigationcomponents.baseclass.utils;

import java.util.ArrayList;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import haq.app.navigationcomponents.Constants;
import haq.app.navigationcomponents.baseclass.BaseFragment;

public class MyFragmentNavigation {

    private ArrayList<BaseFragment> homeNavigation = new ArrayList<>();
    private ArrayList<BaseFragment> listNavigation = new ArrayList<>();
    private ArrayList<BaseFragment> randomNavigation = new ArrayList<>();
    private ArrayList<BaseFragment> optionsNavigation = new ArrayList<>();

    private FragmentManager mFragmentManager;

    private int mContainerId;

    public MyFragmentNavigation(FragmentManager fragmentManager, int containerId) {

        mFragmentManager = fragmentManager;
        mContainerId = containerId;
    }

    public void gotoNewFragment(int tag, BaseFragment fragment) {

        switch (tag) {
            case Constants.HOME_TAG:
                homeNavigation.add(fragment);
                break;
            case Constants.LIST_TAG:
                listNavigation.add(fragment);
                break;
            case Constants.RANDOM_TAG:
                randomNavigation.add(fragment);
                break;
            case Constants.OPTIONS_TAG:
                optionsNavigation.add(fragment);
                break;
        }

        gotoFragment(tag);
    }

    public int getNavigationCount(int tag) {

        switch (tag) {
            case 0:
                return homeNavigation.size();
            case 1:
                return listNavigation.size();
            case 2:
                return randomNavigation.size();
            case 3:
                return optionsNavigation.size();
        }

        return -1;
    }

    public void goBack(int tag) {

        switch (tag) {
            case Constants.HOME_TAG:
                homeNavigation.remove(getLastFragment(tag));
                break;
            case Constants.LIST_TAG:
                listNavigation.remove(getLastFragment(tag));
                break;
            case Constants.RANDOM_TAG:
                randomNavigation.remove(getLastFragment(tag));
                break;
            case Constants.OPTIONS_TAG:
                optionsNavigation.remove(getLastFragment(tag));
                break;
        }
        gotoFragment(tag);
    }

    public void gotoFragment(int tag) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.replace(mContainerId, getLastFragment(tag));
        ft.commit();
    }

    public BaseFragment getLastFragment(int tag) {

        switch (tag) {
            case 0:
                return homeNavigation.get(homeNavigation.size() - 1);
            case 1:
                return listNavigation.get(listNavigation.size() - 1);
            case 2:
                return randomNavigation.get(randomNavigation.size() - 1);
            case 3:
                return optionsNavigation.get(optionsNavigation.size() - 1);
        }
        return null;
    }
}
