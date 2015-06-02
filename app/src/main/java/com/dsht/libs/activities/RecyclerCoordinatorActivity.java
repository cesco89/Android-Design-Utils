package com.dsht.libs.activities;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dsht.libs.R;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import java.util.ArrayList;

import dsht.com.ui.utils.views.activities.BaseRecyclerCoordinatorActivity;


public class RecyclerCoordinatorActivity extends BaseRecyclerCoordinatorActivity {

  @Override
  public void setupToolbar(Toolbar toolbar) {

  }

  @Override
  protected RecyclerView.Adapter getRecyclerAdapter() {
    return new MyAdapter(this, buildSampleItems());
  }

  @Override
  protected int getColumnsCount() {
    return 1;
  }

  @Override
  protected GridLayoutManager.SpanSizeLookup getSpanSizeLookup() {
    return null;
  }

  @Override
  protected GridLayoutManager getLayoutManager() {
    return null;
  }

  @Override
  protected int getOrientation() {
    return LinearLayoutManager.VERTICAL;
  }

  @Override
  protected ViewGroup.LayoutParams getRecyclerViewLayoutParams() {
    return null;
  }

  @Override
  protected View getPinnedAppbarView(LayoutInflater inflater) {
    View pinned = inflater.inflate(R.layout.pinned_appbar_sample, null, false);
    return pinned;
  }

  @Override
  protected FabPosition getFabPosition() {
    return FabPosition.TOP_LEFT;
  }

  @Override
  protected FabSize getFabSize() {
    return FabSize.FAB_MINI;
  }

  @Override
  protected void onFabClick(View v) {
    Toast.makeText(this, "TEST!", Toast.LENGTH_SHORT).show();
  }

  @Override
  protected void setFabImage(FloatingActionButton fab) {
    fab.setImageDrawable(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_add).color(Color.WHITE));
  }

  @Override
  protected boolean fabAutoHide() {
    return false;
  }

  private ArrayList buildSampleItems() {
    ArrayList<String> items = new ArrayList<>();
    for (int i = 0; i<100; i++) {
      items.add("Item "+i);
    }
    return items;
  }

  class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    ArrayList<String> items;

    public MyAdapter(Context context, ArrayList<String> list) {
      this.items = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext())
          .inflate(android.R.layout.simple_list_item_1, parent, false);
      return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
      holder.text.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
      return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

      TextView text;

      public ViewHolder(View itemView) {
        super(itemView);
        text = (TextView) itemView.findViewById(android.R.id.text1);
      }
    }
  }
}
