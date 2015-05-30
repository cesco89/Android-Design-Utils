package dsht.com.ui.utils.views.activities;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.ImageView;

import dsht.com.ui.utils.R;

/**
 * Created by francesco on 30/05/15.
 */
public abstract class BaseCollapsingContainerActivity<FragmentType extends Fragment> extends AppCompatActivity {


  private Toolbar toolbar;
  private CollapsingToolbarLayout collapsingToolbar;
  private LayoutInflater inflater;
  private ImageView backdrop;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.base_collapsing_container_activity);
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
    backdrop = (ImageView) findViewById(R.id.backdrop);
    inflater = getLayoutInflater();
  }

  @Override
  protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    setupToolbar(toolbar);
    collapsingToolbar.setTitle(getCollapsingToolbarTitle());
    setBackdropImage(backdrop);
    setInitialFragment(R.id.container);

  }

  public void replaceFragment(FragmentType fragment, String tag) {
    this.getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.container, fragment, tag)
        .commit();
  }

  protected abstract void setupToolbar(Toolbar toolbar);

  protected abstract void setBackdropImage(ImageView backdrop);

  protected abstract String getCollapsingToolbarTitle();

  protected abstract void setInitialFragment(int containerID);

}
