/**
 * Copyright (C) 2015 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.rx.sample.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.fernandocejas.android10.rx.sample.R;
import com.fernandocejas.android10.rx.sample.data.DataManager;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

public class SampleActivityObservableTransfor extends Activity {

  @InjectView(R.id.btn_flatMap) Button btn_flatMap;
  @InjectView(R.id.btn_concatMap) Button btn_concatMap;

  private DataManager dataManager;

  private Subscription subscription;

  public static Intent getCallingIntent(Context context) {
    return new Intent(context, SampleActivityObservableTransfor.class);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sample_observervable_transform);

    ButterKnife.inject(this);
    initialize();
  }

  @Override
  protected void onDestroy() {
    this.subscription.unsubscribe();
    super.onDestroy();
  }

  private void initialize() {
    this.subscription = Subscriptions.empty();
  }

  @OnClick(R.id.btn_flatMap) void onFlatMapClick() {

  }

  @OnClick(R.id.btn_concatMap) void onConcatMapClick() {

  }
}
