package dsht.com.ui.utils.views.activities;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dsht.com.ui.utils.R;

/**
 * Created by francesco on 30/05/15.
 */
public abstract class BaseRecyclerCoordinatorActivity<AdapterType extends RecyclerView.Adapter> extends AppCompatActivity {

  private CoordinatorLayout coordinator;
  private Toolbar toolbar;
  private AppBarLayout appbar;
  private RecyclerView recycler;
  private LayoutInflater inflater;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.base_recycler_coordinator_activity);

    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    coordinator = (CoordinatorLayout) findViewById(R.id.coordinator);
    appbar = (AppBarLayout) findViewById(R.id.appbar);
    recycler = (RecyclerView) findViewById(R.id.recycler);
    inflater = this.getLayoutInflater();
  }

  @Override
  protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    setupToolbar(toolbar);

    if(getPinnedAppbarView(inflater) != null) {
      appbar.addView(getPinnedAppbarView(inflater));
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

}
