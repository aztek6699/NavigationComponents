package haq.app.navigationcomponents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import haq.app.navigationcomponents.baseclass.BaseFragment;
import haq.app.navigationcomponents.baseclass.utils.MyFragmentNavigation;
import haq.app.navigationcomponents.databinding.ActivityMainBinding;
import haq.app.navigationcomponents.fragments.HomeFragment;
import haq.app.navigationcomponents.fragments.ListFragment;
import haq.app.navigationcomponents.fragments.OptionsFragment;
import haq.app.navigationcomponents.fragments.RandomFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    public final int mContainerId = R.id.frame_container;

    ActivityMainBinding binding;

    private MyFragmentNavigation fragmentNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        fragmentNavigation = new MyFragmentNavigation(getSupportFragmentManager(), mContainerId);

        initBottomViewNavigation();
    }

    public MyFragmentNavigation getFragmentNavigation() {
        return fragmentNavigation;
    }

    private void initBottomViewNavigation () {

        Bundle b = new Bundle();
        b.putInt(Constants.TAG_KEY, Constants.HOME_TAG);
        HomeFragment newFragment = new HomeFragment();
        newFragment.setArguments(b);
        fragmentNavigation.gotoNewFragment(Constants.HOME_TAG, newFragment);

        binding.navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                if (menuItem.getItemId() == R.id.navigation_home) {

                    if(fragmentNavigation.getNavigationCount(Constants.HOME_TAG) > 0) {
                        fragmentNavigation.gotoFragment(Constants.HOME_TAG);
                    }

                    return true;
                } else if (menuItem.getItemId() == R.id.navigation_list) {

                    if(fragmentNavigation.getNavigationCount(Constants.LIST_TAG) > 0) {
                        fragmentNavigation.gotoFragment(Constants.LIST_TAG);
                    } else {
                        Bundle b = new Bundle();
                        b.putInt(Constants.TAG_KEY, Constants.LIST_TAG);
                        ListFragment newFragment = new ListFragment();
                        newFragment.setArguments(b);
                        fragmentNavigation.gotoNewFragment(Constants.LIST_TAG, newFragment);
                    }

                    return true;
                } else if (menuItem.getItemId() == R.id.navigation_random) {

                    if(fragmentNavigation.getNavigationCount(Constants.RANDOM_TAG) > 0) {
                        fragmentNavigation.gotoFragment(Constants.RANDOM_TAG);
                    } else {
                        Bundle b = new Bundle();
                        b.putInt(Constants.TAG_KEY, Constants.RANDOM_TAG);
                        RandomFragment newFragment = new RandomFragment();
                        newFragment.setArguments(b);
                        fragmentNavigation.gotoNewFragment(Constants.RANDOM_TAG, newFragment);
                    }

                    return true;
                } else if (menuItem.getItemId() == R.id.navigation_options) {

                    if(fragmentNavigation.getNavigationCount(Constants.OPTIONS_TAG) > 0) {
                        fragmentNavigation.gotoFragment(Constants.OPTIONS_TAG);
                    } else {
                        Bundle b = new Bundle();
                        b.putInt(Constants.TAG_KEY, Constants.OPTIONS_TAG);
                        OptionsFragment newFragment = new OptionsFragment();
                        newFragment.setArguments(b);
                        fragmentNavigation.gotoNewFragment(Constants.OPTIONS_TAG, newFragment);
                    }

                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    public void showBackButton() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void hideBackButton() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
    }

    @Override
    public void onBackPressed()
    {

        if(fragmentNavigation.getNavigationCount(Constants.HOME_TAG) < 2 &&
                fragmentNavigation.getNavigationCount(Constants.LIST_TAG) < 2 &&
                fragmentNavigation.getNavigationCount(Constants.RANDOM_TAG) < 2 &&
                fragmentNavigation.getNavigationCount(Constants.OPTIONS_TAG) < 2) {

            super.onBackPressed();
        } else {
            BaseFragment fragment = (BaseFragment) getSupportFragmentManager().getFragments().get(0);

            if(fragmentNavigation.getNavigationCount(fragment.getFragmentTag()) > 1) {
                fragmentNavigation.goBack(fragment.getFragmentTag());
            }
        }
    }
}
