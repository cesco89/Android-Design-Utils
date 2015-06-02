package com.dsht.libs.activities;

import android.graphics.Color;
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
  protected FabPosition getFabPosition() {
    return FabPosition.TOP_RIGHT;
  }

  @Override
  protected FabSize getFabSize() {
    return FabSize.FAB_NORMAL;
  }

  @Override
  protected void onFabClick(View v) {
    Toast.makeText(this, "TEST!", Toast.LENGTH_SHORT).show();
  }

  @Override
  protected void setFabImage(FloatingActionButton fab) {
    fab.setImageDrawable(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_share).color(Color.WHITE));
  }

  @Override
  protected boolean fabAutoHide() {
    return false;
  }
}
