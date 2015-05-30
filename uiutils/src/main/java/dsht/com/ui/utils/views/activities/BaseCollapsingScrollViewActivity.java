package dsht.com.ui.utils.views.activities;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import dsht.com.ui.utils.R;

/**
 * Created by francesco on 30/05/15.
 */
public abstract class BaseCollapsingScrollViewActivity<ViewType extends View> extends AppCompatActivity {

  private Toolbar toolbar;
  private CollapsingToolbarLayout collapsingToolbar;
  private LinearLayout contentContainer;
  private LayoutInflater inflater;
  private ImageView backdrop;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.base_collapsing_scrollview_activity);
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    contentContainer = (LinearLayout) findViewById(R.id.content_container);
    collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
    backdrop = (ImageView) findViewById(R.id.backdrop);
    inflater = getLayoutInflater();
  }

  @Override
  protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    setupToolbar(toolbar);
    collapsingToolbar.setTitle(getCollapsingToolbarTitle());
    setBackdropImage(backdrop);
    if(addChildViews(inflater) != null) {
      for(ViewType v : addChildViews(inflater)) {
        contentContainer.addView(v);
      }
    }

  }

  protected abstract void setupToolbar(Toolbar toolbar);

  protected abstract List<ViewType> addChildViews(LayoutInflater inflater);

  protected abstract void setBackdropImage(ImageView backdrop);

  protected abstract String getCollapsingToolbarTitle();
}
