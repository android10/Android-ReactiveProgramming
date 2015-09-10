package com.fernandocejas.android10.rx.sample.activity;

import android.app.Activity;
import android.os.Bundle;
import com.fernandocejas.android10.rx.sample.navigation.Navigator;

public abstract class BaseActivity extends Activity {
  Navigator navigator;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }
}
