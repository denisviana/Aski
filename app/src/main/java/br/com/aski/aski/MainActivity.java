package br.com.aski.aski;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;

import java.util.ArrayList;

import br.com.aski.aski.adapters.ViewPageAdapter;

public class MainActivity extends AppCompatActivity {

    private Fragment currentFragment = new Fragment();
    private HomeActivity homeFragment = new HomeActivity();
    private Fragment recompensasFragment = new Fragment();
    private Fragment comprasFragment = new Fragment();
    private Fragment perfilFragment = new Fragment();

    private ViewPageAdapter viewPageAdapter;
    private AHBottomNavigationAdapter navigationAdapter;
    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();
    private AHBottomNavigationViewPager navigationViewPager;
    private AHBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initUI();




    }


    private void initViews(){
        navigationViewPager = (AHBottomNavigationViewPager) findViewById(R.id.view_pager);
        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigator);

    }

    private void initUI(){

        navigationViewPager.setOffscreenPageLimit(3);

        viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager());
        viewPageAdapter.add(homeFragment);
        viewPageAdapter.add(recompensasFragment);
        viewPageAdapter.add(comprasFragment);
        viewPageAdapter.add(perfilFragment);
        navigationViewPager.setAdapter(viewPageAdapter);

        currentFragment = viewPageAdapter.getCurrentFragment();

        AHBottomNavigationItem item_home = new AHBottomNavigationItem("Home", R.drawable.ic_home);
        AHBottomNavigationItem item_recompensas = new AHBottomNavigationItem("Recompensas", R.drawable.ic_recompensa);
        AHBottomNavigationItem item_compras = new AHBottomNavigationItem("Compras", R.drawable.ic_compras);
        AHBottomNavigationItem item_perfil = new AHBottomNavigationItem("Perfil", R.drawable.person);

        bottomNavigationItems.add(item_home);
        bottomNavigationItems.add(item_recompensas);
        bottomNavigationItems.add(item_compras);
        bottomNavigationItems.add(item_perfil);

        bottomNavigation.addItems(bottomNavigationItems);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        bottomNavigation.setAccentColor(Color.parseColor("#038090"));
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));


        AHNotification notification = new AHNotification.Builder()
                .setText("3")
                .setBackgroundColor(ContextCompat.getColor(this,R.color.colorBottomNavigationNotification))
                .setTextColor(ContextCompat.getColor(this,android.R.color.white))
                .build();

        bottomNavigation.setNotification(notification,3);

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {

                if(currentFragment == null){
                    currentFragment = viewPageAdapter.getCurrentFragment();
                }

                navigationViewPager.setCurrentItem(position,false);
                currentFragment = viewPageAdapter.getCurrentFragment();

                return true;
            }
        });

    }

}
