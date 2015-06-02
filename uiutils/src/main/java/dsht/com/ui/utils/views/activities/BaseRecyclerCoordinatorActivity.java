package dsht.com.ui.utils.views.activities;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import dsht.com.ui.utils.R;
import dsht.com.ui.utils.views.utils.ScrollAwareFABBehavior;

/**
 * Created by francesco on 30/05/15.
 */
public abstract class BaseRecyclerCoordinatorActivity<AdapterType extends RecyclerView.Adapter> extends AppCompatActivity {

  private CoordinatorLayout coordinator;
  private Toolbar toolbar;
  private AppBarLayout appbar;
  private RecyclerView recycler;
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
    setContentView(R.layout.base_recycler_coordinator_activity);

    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    coordinator = (CoordinatorLayout) findViewById(R.id.coordinator);
    appbar = (AppBarLayout) findViewById(R.id.appbar);
    recycler = (RecyclerView) findViewById(R.id.recycler);
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

    if(getRecyclerViewLayoutParams() != null) {
      recycler.setLayoutParams(getRecyclerViewLayoutParams());
    }
    if(getLayoutManager() != null) {
      GridLayoutManager glm = getLayoutManager();
      glm.setOrientation(getOrientation());
      glm.setSpanSizeLookup(getSpanSizeLookup());
      recycler.setLayoutManager(glm);
    } else {
      recycler.setLayoutManager(new LinearLayoutManager(this));
    }

    if(getRecyclerAdapter() != null) {
      recycler.setAdapter(getRecyclerAdapter());
    }

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
      if(fabAutoHide()) {
        fabParams.setBehavior(new ScrollAwareFABBehavior(this));
      }
    }else if (position == FabPosition.BOTTOM_RIGHT) {
      fabParams.gravity = (Gravity.BOTTOM | Gravity.RIGHT);
      if(fabAutoHide()) {
        fabParams.setBehavior(new ScrollAwareFABBehavior(this));
      }
    }
    fab.setLayoutParams(fabParams);
  }

  public AdapterType getAdapter() {
    return (AdapterType) recycler.getAdapter();
  }

  public void setAdapter(AdapterType adapter) {
    recycler.setAdapter(adapter);
  }

  protected abstract void setupToolbar(Toolbar toolbar);

  protected abstract AdapterType getRecyclerAdapter();

  protected abstract int getColumnsCount();

  protected abstract GridLayoutManager.SpanSizeLookup getSpanSizeLookup();

  protected abstract GridLayoutManager getLayoutManager();

  protected abstract int getOrientation();

  protected abstract ViewGroup.LayoutParams getRecyclerViewLayoutParams();

  protected abstract View getPinnedAppbarView(LayoutInflater inflater);

  protected abstract FabPosition getFabPosition();

  protected abstract FabSize getFabSize();

  protected abstract void onFabClick(View v);

  protected abstract void setFabImage(FloatingActionButton fab);

  protected abstract boolean fabAutoHide();

}
