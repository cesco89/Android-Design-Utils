package dsht.com.ui.utils.views.activities;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import dsht.com.ui.utils.R;

/**
 * Created by francesco on 30/05/15.
 */
public abstract class BaseCoordinatorContainerActivity<FragmentType extends Fragment> extends AppCompatActivity {
  private CoordinatorLayout coordinator;
  private Toolbar toolbar;
  private AppBarLayout appbar;
  private FrameLayout container;
  private LayoutInflater inflater;
  private FrameLayout pinnedContainer;
  private FloatingActionButton fab;
  private CoordinatorLayout.LayoutParams fabParams;

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
    setContentView(R.layout.base_coordinator_container_activity);

    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    coordinator = (CoordinatorLayout) findViewById(R.id.coordinator);
    appbar = (AppBarLayout) findViewById(R.id.appbar);
    container = (FrameLayout) findViewById(R.id.container);
    pinnedContainer = (FrameLayout) findViewById(R.id.pinned_container);
    inflater = this.getLayoutInflater();

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
    if(getPinnedAppbarView(inflater) != null) {
      pinnedContainer.addView(getPinnedAppbarView(inflater));
    }else{
      pinnedContainer.setVisibility(View.GONE);
    }

    setInitialFragment(R.id.container);

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

  public void replaceFragment(FragmentType fragment, String tag) {
    this.getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.container, fragment, tag)
        .commit();
  }

  protected abstract void setupToolbar(Toolbar toolbar);

  protected abstract View getPinnedAppbarView(LayoutInflater inflater);

  protected abstract void setInitialFragment(int containerID);

  protected abstract FabPosition getFabPosition();

  protected abstract FabSize getFabSize();

  protected abstract void onFabClick(View v);

  protected abstract void setFabImage(FloatingActionButton fab);


}
