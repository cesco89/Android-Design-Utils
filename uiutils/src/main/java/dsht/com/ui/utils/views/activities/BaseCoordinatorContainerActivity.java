package dsht.com.ui.utils.views.activities;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

  protected abstract void setupToolbar(Toolbar toolbar);

  protected abstract View getPinnedAppbarView(LayoutInflater inflater);

  protected abstract void setInitialFragment(int containerID);

  public void replaceFragment(FragmentType fragment, String tag) {
    this.getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.container, fragment, tag)
        .commit();
  }

}
