package dsht.com.ui.utils.views.activities;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import dsht.com.ui.utils.R;
import dsht.com.ui.utils.views.utils.FabBuilder;
import dsht.com.ui.utils.views.utils.ScrollAwareFABBehavior;

/**
 * Created by francesco on 30/05/15.
 */
public abstract class BaseCollapsingScrollViewActivity<ViewType extends View> extends AppCompatActivity {

  private Toolbar toolbar;
  private CollapsingToolbarLayout collapsingToolbar;
  private CoordinatorLayout coordinator;
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
    coordinator = (CoordinatorLayout) findViewById(R.id.coordinator);
    backdrop = (ImageView) findViewById(R.id.backdrop);
    inflater = getLayoutInflater();

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

  protected abstract FabBuilder.FabPosition getFabPosition();

  protected abstract FabBuilder.FabSize getFabSize();

  protected abstract View.OnClickListener getOnFabClickListener();

  protected abstract Drawable getFabImageAsDrawable();

  protected abstract Bitmap getFabImageAsBitmap();

  protected abstract boolean fabAutoHide();


}
