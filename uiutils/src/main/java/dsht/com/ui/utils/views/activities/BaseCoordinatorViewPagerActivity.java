package dsht.com.ui.utils.views.activities;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import dsht.com.ui.utils.R;
import dsht.com.ui.utils.views.utils.FabBuilder;
import dsht.com.ui.utils.views.utils.ScrollAwareFABBehavior;

/**
 * Created by francesco on 30/05/15.
 */
public abstract class BaseCoordinatorViewPagerActivity<AdapterType extends FragmentPagerAdapter> extends AppCompatActivity {

  private ViewPager pager;
  private TabLayout tabs;
  private Toolbar toolbar;
  private CoordinatorLayout coordinator;
  private LayoutInflater inflater;
  private AppBarLayout.LayoutParams toolbarParams;
  private AppBarLayout.LayoutParams tabsParams;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.base_coordinator_viewpager_activity);
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    coordinator = (CoordinatorLayout) findViewById(R.id.coordinator);
    setSupportActionBar(toolbar);

    tabs = (TabLayout) findViewById(R.id.tabs);
    pager = (ViewPager) findViewById(R.id.pager);

    inflater = getLayoutInflater();
    toolbarParams = new AppBarLayout.LayoutParams(toolbar.getLayoutParams());
    tabsParams = new AppBarLayout.LayoutParams(tabs.getLayoutParams());

    if(!toolbarAutoHide()) {
      toolbarParams.setScrollFlags(0);
      toolbar.setLayoutParams(toolbarParams);
    }

    if(!tabsAutoHide()) {
      tabsParams.setScrollFlags(0);
      tabs.setLayoutParams(tabsParams);
    }

    new FabBuilder(this)
        .withAnchorId(R.id.appbar)
        .withAutoHide(fabAutoHide())
        .withCoordinatorLayout(coordinator)
        .withImageDrawable(getFabImageAsDrawable())
        .withImageBitmap(getFabImageAsBitmap())
        .withOnClickListener(getOnFabClickListener())
        .withPosition(getFabPosition())
        .withSize(getFabSize())
        .build();
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

  protected abstract boolean toolbarAutoHide();

  protected abstract boolean tabsAutoHide();

  protected abstract FabBuilder.FabPosition getFabPosition();

  protected abstract FabBuilder.FabSize getFabSize();

  protected abstract View.OnClickListener getOnFabClickListener();

  protected abstract Drawable getFabImageAsDrawable();

  protected abstract Bitmap getFabImageAsBitmap();

  protected abstract boolean fabAutoHide();

}
