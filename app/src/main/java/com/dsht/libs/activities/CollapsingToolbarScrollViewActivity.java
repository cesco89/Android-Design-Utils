package com.dsht.libs.activities;

import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.dsht.libs.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import dsht.com.ui.utils.views.activities.BaseCollapsingScrollViewActivity;

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
}
