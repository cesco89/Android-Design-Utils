package com.dsht.libs.activities;

import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import com.dsht.libs.R;
import com.dsht.libs.fragments.SampleFragment;

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
    return inflater.inflate(R.layout.pinned_appbar_sample, null, false);
  }

  @Override
  protected void setInitialFragment(int containerID) {
    getSupportFragmentManager()
        .beginTransaction()
        .replace(containerID, new SampleFragment())
        .commit();
  }
}
