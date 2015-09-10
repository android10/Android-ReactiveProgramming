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
import rx.Subscription;
import rx.subscriptions.Subscriptions;

public class ActivityBackpressureSamples extends Activity {

  @InjectView(R.id.btn_backpressureSample) Button btn_backpressureSample;
  @InjectView(R.id.btn_backpressureBuffer) Button btn_backpressureBuffer;

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

  @OnClick(R.id.btn_backpressureSample) void onButtonSampleClick() {
  }
}
