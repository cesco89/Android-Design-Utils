package dsht.com.ui.utils.views.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import dsht.com.ui.utils.R;

/**
 * Created by francesco on 03/06/15.
 */
public class FabBuilder<DrawableType extends Drawable, BitmapType extends Bitmap> {

  public enum FabPosition {
    TOP_LEFT,
    TOP_RIGHT,
    BOTTOM_LEFT,
    BOTTOM_RIGHT
  }

  public enum FabSize {
    FAB_NORMAL,
    FAB_MINI,
    NONE
  }

  private CoordinatorLayout.LayoutParams fabParams;
  private CoordinatorLayout parent;
  private int anchorId;
  private FabSize fabSize;
  private FabPosition fabPosition;
  private Context context;
  private View.OnClickListener listener;
  private DrawableType drawable;
  private BitmapType bitmap;
  private LayoutInflater inflater;
  private FloatingActionButton fab;
  private boolean autoHide;
  private CoordinatorLayout.Behavior behavior;

  public FabBuilder(Context _context) {
    this.context = _context;
    this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }

  public FabBuilder withCoordinatorLayout(CoordinatorLayout _parent) {
    this.parent = _parent;
    return this;
  }

  public FabBuilder withSize(FabSize size) {
    this.fabSize = size;
    return this;
  }

  public FabBuilder withPosition(FabPosition position) {
    this.fabPosition = position;
    return this;
  }

  public FabBuilder withOnClickListener(View.OnClickListener onClickListener) {
    this.listener = onClickListener;
    return this;
  }

  public FabBuilder withImageDrawable(DrawableType _drawable) {
    this.drawable = _drawable;
    return this;
  }

  public FabBuilder withAnchorId(int _ancordId) {
    this.anchorId = _ancordId;
    return this;
  }

  public FabBuilder withImageBitmap(BitmapType _bitmap) {
    this.bitmap = _bitmap;
    return this;
  }

  public FabBuilder withAutoHide(boolean _autohide) {
    this.autoHide = _autohide;
    return this;
  }

  public FabBuilder withCoordinatorLayoutBehavior(CoordinatorLayout.Behavior _behavior) {
    this.behavior = _behavior;
    return this;
  }

  public FloatingActionButton getFab() {
    return this.fab;
  }

  public CoordinatorLayout.LayoutParams getFabLayoutParams() {
    return fabParams;
  }

  public void build() {
    if(fabSize != FabSize.NONE) {
      if (fabSize == FabSize.FAB_MINI) {
        fab = (FloatingActionButton) inflater.inflate(R.layout.base_fab_mini, null, false);
      } else if (fabSize == FabSize.FAB_NORMAL) {
        fab = (FloatingActionButton) inflater.inflate(R.layout.base_fab_normal, null, false);
      }
      parent.addView(fab);
      fabParams = new CoordinatorLayout.LayoutParams(fab.getLayoutParams());
      int fabMargin = context.getResources().getDimensionPixelSize(R.dimen.fab_margin);
      fabParams.setMargins(fabMargin, fabMargin, fabMargin, fabMargin);
      if (fabPosition == FabPosition.TOP_LEFT) {
        fabParams.setAnchorId(R.id.appbar);
        fabParams.anchorGravity = (Gravity.START | Gravity.LEFT | Gravity.BOTTOM);
      } else if (fabPosition == FabPosition.TOP_RIGHT) {
        fabParams.setAnchorId(R.id.appbar);
        fabParams.anchorGravity = (Gravity.RIGHT | Gravity.END | Gravity.BOTTOM);
      } else if (fabPosition == FabPosition.BOTTOM_LEFT) {
        fabParams.gravity = (Gravity.BOTTOM | Gravity.LEFT);
        if (autoHide) {
          if(behavior != null) {
            fabParams.setBehavior(behavior);
          }else {
            fabParams.setBehavior(new ScrollAwareFABBehavior(context));
          }
        }
      } else if (fabPosition == FabPosition.BOTTOM_RIGHT) {
        fabParams.gravity = (Gravity.BOTTOM | Gravity.RIGHT);
        if (autoHide) {
          if(behavior != null) {
            fabParams.setBehavior(behavior);
          }else {
            fabParams.setBehavior(new ScrollAwareFABBehavior(context));
          }
        }
      }
      fab.setLayoutParams(fabParams);
      if (drawable != null) {
        fab.setImageDrawable(drawable);
      } else if (bitmap != null) {
        fab.setImageBitmap(bitmap);
      }
      fab.setOnClickListener(listener);
    }
  }



}
