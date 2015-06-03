package dsht.com.ui.utils.views.activities;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
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
import dsht.com.ui.utils.views.utils.FabBuilder;
import dsht.com.ui.utils.views.utils.ScrollAwareFABBehavior;

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
  private AppBarLayout.LayoutParams toolbarParams;
  private AppBarLayout.LayoutParams pinnedViewParams;

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
    toolbarParams = new AppBarLayout.LayoutParams(toolbar.getLayoutParams());
    pinnedViewParams = new AppBarLayout.LayoutParams(pinnedContainer.getLayoutParams());

    if(!toolbarAutoHide()) {
      toolbarParams.setScrollFlags(0);
      toolbar.setLayoutParams(toolbarParams);
    }

    if(!pinnedViewAutoHide()) {
      pinnedViewParams.setScrollFlags(0);
      pinnedContainer.setLayoutParams(pinnedViewParams);

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
    if(getPinnedAppbarView(inflater) != null) {
      pinnedContainer.addView(getPinnedAppbarView(inflater));
    }else{
      pinnedContainer.setVisibility(View.GONE);
    }

    setInitialFragment(R.id.container);

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

  protected abstract boolean toolbarAutoHide();

  protected abstract boolean pinnedViewAutoHide();

  protected abstract FabBuilder.FabPosition getFabPosition();

  protected abstract FabBuilder.FabSize getFabSize();

  protected abstract View.OnClickListener getOnFabClickListener();

  protected abstract Drawable getFabImageAsDrawable();

  protected abstract Bitmap getFabImageAsBitmap();

  protected abstract boolean fabAutoHide();


}
