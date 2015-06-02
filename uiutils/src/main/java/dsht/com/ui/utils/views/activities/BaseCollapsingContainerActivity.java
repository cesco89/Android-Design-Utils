package dsht.com.ui.utils.views.activities;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import dsht.com.ui.utils.R;
import dsht.com.ui.utils.views.utils.ScrollAwareFABBehavior;

/**
 * Created by francesco on 30/05/15.
 */
public abstract class BaseCollapsingContainerActivity<FragmentType extends Fragment> extends AppCompatActivity {


  private Toolbar toolbar;
  private CollapsingToolbarLayout collapsingToolbar;
  private CoordinatorLayout coordinator;
  private LayoutInflater inflater;
  private ImageView backdrop;

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
    setContentView(R.layout.base_collapsing_container_activity);
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
    coordinator = (CoordinatorLayout) findViewById(R.id.coordinator);
    backdrop = (ImageView) findViewById(R.id.backdrop);
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
    collapsingToolbar.setTitle(getCollapsingToolbarTitle());
    setBackdropImage(backdrop);
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

  public void replaceFragment(FragmentType fragment, String tag) {
    this.getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.container, fragment, tag)
        .commit();
  }

  protected abstract void setupToolbar(Toolbar toolbar);

  protected abstract void setBackdropImage(ImageView backdrop);

  protected abstract String getCollapsingToolbarTitle();

  protected abstract void setInitialFragment(int containerID);

  protected abstract FabPosition getFabPosition();

  protected abstract FabSize getFabSize();

  protected abstract void onFabClick(View v);

  protected abstract void setFabImage(FloatingActionButton fab);

  protected abstract boolean fabAutoHide();

}
