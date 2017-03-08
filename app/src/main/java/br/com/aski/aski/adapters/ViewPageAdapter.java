package br.com.aski.aski.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by denisvcosta on 16/02/2017.
 */

public class ViewPageAdapter extends FragmentPagerAdapter {


    private ArrayList<Fragment> fragments = new ArrayList<>();
    private Fragment currentFragment;

    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public void add(Fragment fragment){
        this.fragments.add(fragment);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {

       if(getCurrentFragment() != object){
           currentFragment = ((Fragment) object);
       }

        super.setPrimaryItem(container, position, object);
    }

    public Fragment getCurrentFragment(){
        return currentFragment;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
       return fragments.size();
    }
}
