package com.dsht.libs.activities;

import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.dsht.libs.R;
import com.dsht.libs.fragments.SampleFragment;
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
}
