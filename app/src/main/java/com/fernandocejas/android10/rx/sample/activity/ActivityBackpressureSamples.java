package com.fernandocejas.android10.rx.sample.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import butterknife.ButterKnife;
import com.fernandocejas.android10.rx.sample.R;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

public class ActivityBackpressureSamples extends BaseActivity {

  private Subscription subscription;

  public static Intent getCallingIntent(Context context) {
    return new Intent(context, ActivityBackpressureSamples.class);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_samples_backpressure);

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
}
