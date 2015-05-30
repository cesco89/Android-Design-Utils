package dsht.com.ui.utils.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dsht.com.ui.utils.R;

/**
 * Created by francesco on 25/05/15.
 */
public abstract class BaseRefreshableRecyclerFragment<AdapterType extends RecyclerView.Adapter> extends Fragment {

  private RecyclerView mRecycler;
  private SwipeRefreshLayout mRefresh;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.base_refresh_recycler_layout, container, false);
    mRecycler = (RecyclerView) v.findViewById(R.id.recycler_view);
    mRefresh = (SwipeRefreshLayout) v.findViewById(R.id.refresh_layout);
    return v;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    if(getRecyclerViewLayoutParams() != null) {
      mRecycler.setLayoutParams(getRecyclerViewLayoutParams());
    }
    if(getLayoutManager() != null) {
      GridLayoutManager glm = getLayoutManager();
      glm.setOrientation(getOrientation());
      glm.setSpanSizeLookup(getSpanSizeLookup());
      mRecycler.setLayoutManager(glm);
    }else{
      mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    if(getRecyclerAdapter() != null) {
      mRecycler.setAdapter(getRecyclerAdapter());
    }

    if(getSwipeDistance() != 0) {
      mRefresh.setDistanceToTriggerSync(getSwipeDistance());
    }

    mRefresh.setEnabled(isRefreshingEnabled());
  }

  @Override
  public void onResume() {
    super.onResume();
    if(getOnRefreshListener() != null) {
      mRefresh.setOnRefreshListener(getOnRefreshListener());
    }
  }

  @Override
  public void onPause() {
    super.onPause();
    mRefresh.setOnRefreshListener(null);
  }

  public AdapterType getAdapter() {
    return (AdapterType) mRecycler.getAdapter();
  }

  public void setAdapter(AdapterType adapter) {
    mRecycler.setAdapter(adapter);
  }

  public void startRefreshing() {
    mRefresh.setRefreshing(true);
  }

  public void stopRefreshing() {
    mRefresh.setRefreshing(false);
  }

  public void enableRefresh() {
    mRefresh.setEnabled(true);
  }

  public void disableRefresh() {
    mRefresh.setEnabled(false);
  }

  protected abstract int getColumnsCount();

  protected abstract GridLayoutManager.SpanSizeLookup getSpanSizeLookup();

  protected abstract GridLayoutManager getLayoutManager();

  protected abstract SwipeRefreshLayout.OnRefreshListener getOnRefreshListener();

  protected abstract int getOrientation();

  protected abstract ViewGroup.LayoutParams getRecyclerViewLayoutParams();

  protected abstract AdapterType getRecyclerAdapter();

  protected abstract int getSwipeDistance();

  protected abstract boolean isRefreshingEnabled();
}
