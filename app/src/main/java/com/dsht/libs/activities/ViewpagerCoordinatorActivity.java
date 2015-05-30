package com.dsht.libs.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.dsht.libs.R;
import com.dsht.libs.fragments.SampleFragment;

import java.util.ArrayList;

import dsht.com.ui.utils.views.activities.BaseCoordinatorViewPagerActivity;

/**
 * Created by francesco on 30/05/15.
 */
public class ViewpagerCoordinatorActivity extends BaseCoordinatorViewPagerActivity {

  @Override
  protected FragmentPagerAdapter getPagerAdapter() {

    MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
    adapter.addFragment(new SampleFragment(), "SAMPLE 1");
    adapter.addFragment(new SampleFragment(), "SAMPLE 2");
    adapter.addFragment(new SampleFragment(), "SAMPLE 3");

    return adapter;
  }

  @Override
  protected void setupToolbar(Toolbar toolbar) {
    toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
      @Override
      public boolean onMenuItemClick(MenuItem item) {
        //DO STUFF
        Toast.makeText(ViewpagerCoordinatorActivity.this, "Clicked "+item.getTitle(), Toast.LENGTH_SHORT).show();
        return false;
      }
    });
  }

  @Override
  protected ViewPager.OnPageChangeListener getOnPageChangeListener() {
    return new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }

      @Override
      public void onPageSelected(int position) {
        Toast.makeText(ViewpagerCoordinatorActivity.this,"Selected: "+getAdapter().getPageTitle(position), Toast.LENGTH_SHORT ).show();
      }

      @Override
      public void onPageScrollStateChanged(int state) {

      }
    };
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  class MyPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();

    public MyPagerAdapter(FragmentManager fm) {
      super(fm);
    }

    public void addFragment(Fragment fragment, String title) {
      fragments.add(fragment);
      titles.add(title);
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
      return titles.get(position);
    }
  }
}