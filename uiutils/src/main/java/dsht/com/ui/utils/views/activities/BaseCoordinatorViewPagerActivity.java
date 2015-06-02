package dsht.com.ui.utils.views.activities;

import android.os.Bundle;
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

/**
 * Created by francesco on 30/05/15.
 */
public abstract class BaseCoordinatorViewPagerActivity<AdapterType extends FragmentPagerAdapter> extends AppCompatActivity {

  private ViewPager pager;
  private TabLayout tabs;
  private Toolbar toolbar;
  private CoordinatorLayout coordinator;
  private FloatingActionButton fab;
  private CoordinatorLayout.LayoutParams fabParams;
  private LayoutInflater inflater;

  public enum FabPosition {
    TOP_LEFT,
    TOP_RIGHT,
    BOTTOM_LEFT,
    BOTTOM_RIGHT
  }

  public enum FabSize {
    FAB_NORMAL,
    FAB_MINI,
    NONE
  }

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
    if(getFabSize() == FabSize.FAB_NORMAL) {
      fab = (FloatingActionButton) inflater.inflate(R.layout.base_fab_normal, null);
    }else if(getFabSize() == FabSize.FAB_MINI) {
      fab = (FloatingActionButton) inflater.inflate(R.layout.base_fab_mini, null);
    }

    if(fab != null) {
      coordinator.addView(fab);
      fabParams = new CoordinatorLayout.LayoutParams(fab.getLayoutParams());
      int fabMargin = getResources().getDimensionPixelSize(R.dimen.fab_margin);
      fabParams.setMargins(fabMargin, fabMargin, fabMargin, fabMargin);
      setFabPos(getFabPosition());
      fab.setOnClickListener(c -> onFabClick(fab));
      setFabImage(fab);
    }
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

  public void setFabPosition(FabPosition position) {
    setFabPos(position);
  }

  private void setFabPos(FabPosition position) {
    if(position == FabPosition.TOP_LEFT) {
      fabParams.setAnchorId(R.id.appbar);
      fabParams.anchorGravity = (Gravity.START | Gravity.LEFT | Gravity.BOTTOM);
    }else if (position == FabPosition.TOP_RIGHT) {
      fabParams.setAnchorId(R.id.appbar);
      fabParams.anchorGravity = (Gravity.RIGHT | Gravity.END | Gravity.BOTTOM);
    }else if (position == FabPosition.BOTTOM_LEFT) {
      fabParams.gravity = (Gravity.BOTTOM | Gravity.LEFT);
    }else if (position == FabPosition.BOTTOM_RIGHT) {
      fabParams.gravity = (Gravity.BOTTOM | Gravity.RIGHT);
    }
    fab.setLayoutParams(fabParams);
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

  protected abstract FabPosition getFabPosition();

  protected abstract FabSize getFabSize();

  protected abstract void onFabClick(View v);

  protected abstract void setFabImage(FloatingActionButton fab);

}
