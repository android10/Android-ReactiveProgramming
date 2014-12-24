package com.fernandocejas.android10.rx.sample;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

  private DataManager dataManager = new DataManager();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }
}
