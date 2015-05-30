package com.dsht.libs.fragments;

import android.content.Context;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import dsht.com.ui.utils.views.fragments.BaseRefreshableRecyclerFragment;

/**
 * Created by francesco on 30/05/15.
 */
public class SampleFragment extends BaseRefreshableRecyclerFragment {


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
  protected SwipeRefreshLayout.OnRefreshListener getOnRefreshListener() {
    return new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
          @Override
          public void run() {
            stopRefreshing();
          }
        }, 5000);
      }
    };
  }

  @Override
  protected int getOrientation() {
    return 0;
  }

  @Override
  protected ViewGroup.LayoutParams getRecyclerViewLayoutParams() {
    return null;
  }

  @Override
  protected RecyclerView.Adapter getRecyclerAdapter() {
    return new MyAdapter(getActivity(), buildSampleItems());
  }

  @Override
  protected int getSwipeDistance() {
    return 200;
  }

  @Override
  protected boolean isRefreshingEnabled() {
    return false;
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

  private ArrayList buildSampleItems() {
    ArrayList<String> items = new ArrayList<>();
    for (int i = 0; i<100; i++) {
      items.add("Item "+i);
    }
    return items;
  }
}
