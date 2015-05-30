package com.dsht.libs.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.dsht.libs.R;

/**
 * Created by francesco on 30/05/15.
 */
public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    setSupportActionBar(((Toolbar)findViewById(R.id.toolbar)));

    ((Button)findViewById(R.id.btn1)).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(MainActivity.this, RecyclerCoordinatorActivity.class));
      }
    });

    ((Button)findViewById(R.id.btn2)).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(MainActivity.this, ContainerCoordinatorActivity.class));
      }
    });

    ((Button)findViewById(R.id.btn3)).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(MainActivity.this, CollapsingToolbarScrollViewActivity.class));
      }
    });
    ((Button)findViewById(R.id.btn4)).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(MainActivity.this, CollapsingToolbarContainerActivity.class));
      }
    });
  }
}
