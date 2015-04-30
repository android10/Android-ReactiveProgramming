package com.fernandocejas.android10.rx.sample.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import butterknife.ButterKnife;
import com.fernandocejas.android10.rx.sample.R;
import com.fernandocejas.android10.rx.sample.data.DataManager;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

public class SampleActivityBackpressure extends Activity {

  private DataManager dataManager;
  private Subscription subscription;

  public static Intent getCallingIntent(Context context) {
    return new Intent(context, SampleActivityBackpressure.class);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sample_backpressure);

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
    this.dataManager = new DataManager();
  }
}
