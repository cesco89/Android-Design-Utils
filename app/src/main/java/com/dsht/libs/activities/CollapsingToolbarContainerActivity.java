package com.dsht.libs.activities;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.dsht.libs.R;
import com.dsht.libs.fragments.SampleFragment;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.squareup.picasso.Picasso;

import dsht.com.ui.utils.views.activities.BaseCollapsingContainerActivity;
import dsht.com.ui.utils.views.utils.FabBuilder;

/**
 * Created by francesco on 30/05/15.
 */
public class CollapsingToolbarContainerActivity extends BaseCollapsingContainerActivity {
  @Override
  protected void setupToolbar(Toolbar toolbar) {

  }

  @Override
  protected void setBackdropImage(ImageView backdrop) {
    Picasso.with(this)
        .load(R.drawable.sample_bg)
        .into(backdrop);
  }

  @Override
  protected String getCollapsingToolbarTitle() {
    return "Sample title";
  }

  @Override
  protected void setInitialFragment(int containerID) {
    getSupportFragmentManager()
        .beginTransaction()
        .replace(containerID, new SampleFragment())
        .commit();
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
        Toast.makeText(CollapsingToolbarContainerActivity.this, "TEST!", Toast.LENGTH_SHORT).show();
      }
    };
  }

  @Override
  protected Drawable getFabImageAsDrawable() {
    return new IconicsDrawable(this, GoogleMaterial.Icon.gmd_email).color(Color.WHITE);
  }

  @Override
  protected Bitmap getFabImageAsBitmap() {
    return null;
  }


  @Override
  protected boolean fabAutoHide() {
    return false;
  }
}
