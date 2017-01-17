package zz.hanhan.testtablegreendao.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import zz.hanhan.testtablegreendao.fragment.MyFragment;

/**
 * Created by lenovo on 2017/1/15.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private final ArrayList<MyFragment> fragments;

    public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<MyFragment> fragments) {
        super(fm);
        this.fragments=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getTitle();
    }
}
