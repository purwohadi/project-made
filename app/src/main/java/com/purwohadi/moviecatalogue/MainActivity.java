package com.purwohadi.moviecatalogue;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.material.tabs.TabLayout;
import com.purwohadi.moviecatalogue.Fragment.MovieFragment;
import com.purwohadi.moviecatalogue.Fragment.TvFragment;
import com.purwohadi.moviecatalogue.adapter.TabPagerAdapter;

public class MainActivity extends BaseAppCompatActivity {
    private ViewPager viewPager;

    private Fragment pageContent = new MovieFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tablayout);

        TabPagerAdapter tabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager());

        /*set page title*/
        tabPagerAdapter.addFragment(new MovieFragment(), getString(R.string.tab_movie));
        tabPagerAdapter.addFragment(new TvFragment(), getString(R.string.tab_tv));

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(tabPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.viewpager, pageContent).commit();
        } else {
            pageContent = getSupportFragmentManager().getFragment(savedInstanceState, KEY_FRAGMENT);

            getSupportFragmentManager().beginTransaction().replace(R.id.viewpager, pageContent).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.settings){
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        getSupportFragmentManager().putFragment(savedInstanceState, KEY_FRAGMENT, pageContent);
        super.onSaveInstanceState(savedInstanceState);
    }
}
