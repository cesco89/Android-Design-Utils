package com.dsht.libs.activities;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.dsht.libs.R;
import com.dsht.libs.fragments.SampleFragment;
import com.mikepenz.iconics.IconicsDrawable;

import dsht.com.ui.utils.views.activities.BaseCoordinatorContainerActivity;

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
  protected FabPosition getFabPosition() {
    return null;
  }

  @Override
  protected FabSize getFabSize() {
    //This thime we don't want fab!
    return FabSize.NONE;
  }

  @Override
  protected void onFabClick(View v) {
    //Nothing to do here!
  }

  @Override
  protected void setFabImage(FloatingActionButton fab) {
    //same here!
  }

  @Override
  protected boolean fabAutoHide() {
    //No fab!
    return false;
  }
}
