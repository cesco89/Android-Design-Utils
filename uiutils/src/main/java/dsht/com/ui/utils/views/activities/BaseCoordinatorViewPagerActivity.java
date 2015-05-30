package dsht.com.ui.utils.views.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import dsht.com.ui.utils.R;

/**
 * Created by francesco on 30/05/15.
 */
public abstract class BaseCoordinatorViewPagerActivity<AdapterType extends FragmentPagerAdapter> extends AppCompatActivity {

  private ViewPager pager;
  private TabLayout tabs;
  private Toolbar toolbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.base_coordinator_viewpager_activity);
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    tabs = (TabLayout) findViewById(R.id.tabs);
    pager = (ViewPager) findViewById(R.id.pager);
  }

  @Override
  protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    setupToolbar(toolbar);
    if(getPagerAdapter() != null) {
      pager.setAdapter(getPagerAdapter());
    }
    if(getOnPageChangeListener() != null) {
      pager.setOnPageChangeListener(getOnPageChangeListener());
    }
    tabs.setupWithViewPager(pager);


  }

  public AdapterType getAdapter() {
    return (AdapterType) pager.getAdapter();
  }

  public void setAdapter(AdapterType adapter) {
    pager.setAdapter(adapter);
  }

  protected abstract AdapterType getPagerAdapter();

  protected abstract void setupToolbar(Toolbar toolbar);

  protected abstract ViewPager.OnPageChangeListener getOnPageChangeListener();

}
