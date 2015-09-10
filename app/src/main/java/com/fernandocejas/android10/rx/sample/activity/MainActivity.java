package com.fernandocejas.android10.rx.sample.activity;

import android.os.Bundle;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.fernandocejas.android10.rx.sample.R;
import com.fernandocejas.android10.rx.sample.navigation.Navigator;

public class MainActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.inject(this);
    this.initialize();
  }

  private void initialize() {
    this.navigator = new Navigator();
  }

  @OnClick(R.id.btn_sampleObserver) void navigateToObserverSample() {
    this.navigator.navigateToSubscriberSample(this);
  }

  @OnClick(R.id.btn_sampleBackpressure) void navigateToBackpressureSample() {
    this.navigator.navigateToBackpressureSamples(this);
  }

  @OnClick(R.id.btn_sampleObservableComposition) void navigateToObservableCompositionSamples() {
    this.navigator.navigateToObservableCompositionSamples(this);
  }
}
