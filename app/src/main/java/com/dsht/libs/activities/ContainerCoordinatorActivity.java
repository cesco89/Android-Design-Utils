package com.dsht.libs.activities;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.dsht.libs.R;
import com.dsht.libs.fragments.SampleFragment;
import com.mikepenz.iconics.IconicsDrawable;

import dsht.com.ui.utils.views.activities.BaseCoordinatorContainerActivity;
import dsht.com.ui.utils.views.utils.FabBuilder;

/**
 * Created by francesco on 30/05/15.
 */
public class ContainerCoordinatorActivity extends BaseCoordinatorContainerActivity {
  @Override
  protected void setupToolbar(Toolbar toolbar) {

  }

  @Override
  protected View getPinnedAppbarView(LayoutInflater inflater) {
    return null;
  }

  @Override
  protected void setInitialFragment(int containerID) {
    getSupportFragmentManager()
        .beginTransaction()
        .replace(containerID, new SampleFragment())
        .commit();
  }

  @Override
  protected boolean toolbarAutoHide() {
    return false;
  }

  @Override
  protected boolean pinnedViewAutoHide() {
    return true;
  }

  @Override
  protected FabBuilder.FabPosition getFabPosition() {
    return null;
  }

  @Override
  protected FabBuilder.FabSize getFabSize() {
    //This thime we don't want fab!
    return FabBuilder.FabSize.NONE;
  }

  @Override
  protected View.OnClickListener getOnFabClickListener() {
    return null;
  }

  @Override
  protected Drawable getFabImageAsDrawable() {
    return null;
  }

  @Override
  protected Bitmap getFabImageAsBitmap() {
    return null;
  }


  @Override
  protected boolean fabAutoHide() {
    //No fab!
    return false;
  }
}
