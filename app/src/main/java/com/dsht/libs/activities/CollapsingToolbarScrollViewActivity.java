package com.dsht.libs.activities;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.dsht.libs.R;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import dsht.com.ui.utils.views.activities.BaseCollapsingScrollViewActivity;
import dsht.com.ui.utils.views.utils.FabBuilder;

/**
 * Created by francesco on 30/05/15.
 */
public class CollapsingToolbarScrollViewActivity extends BaseCollapsingScrollViewActivity {


  @Override
  protected void setupToolbar(Toolbar toolbar) {

  }

  @Override
  protected List addChildViews(LayoutInflater inflater) {
    List<View> items = new ArrayList<>();
    for(int i=0; i<20; i++) {
      items.add(inflater.inflate(R.layout.scrollview_sample_item, null, false));
    }
    return items;
  }

  @Override
  protected void setBackdropImage(ImageView backdrop) {
    Picasso.with(this)
        .load(R.drawable.sample_bg)
        .into(backdrop);

  }

  @Override
  protected String getCollapsingToolbarTitle() {
    return "Sample scrolling";
  }

  @Override
  protected FabBuilder.FabPosition getFabPosition() {
    return FabBuilder.FabPosition.TOP_RIGHT;
  }

  @Override
  protected FabBuilder.FabSize getFabSize() {
    return FabBuilder.FabSize.FAB_NORMAL;
  }

  @Override
  protected View.OnClickListener getOnFabClickListener() {
    return new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(CollapsingToolbarScrollViewActivity.this, "TEST!", Toast.LENGTH_SHORT).show();
      }
    };
  }

  @Override
  protected Drawable getFabImageAsDrawable() {
    return new IconicsDrawable(this, GoogleMaterial.Icon.gmd_airplanemode_on).color(Color.WHITE);
  }

  @Override
  protected Bitmap getFabImageAsBitmap() {
    return null;
  }


  @Override
  protected boolean fabAutoHide() {
    //We don't need to hide it!
    return false;
  }
}
