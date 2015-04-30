package com.fernandocejas.android10.rx.sample.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.fernandocejas.android10.rx.sample.R;
import com.fernandocejas.android10.rx.sample.navigation.Navigator;

public class MainActivity extends Activity {

  @InjectView(R.id.btn_sampleObserver) Button btn_sampleObserver;
  @InjectView(R.id.btn_sampleBackpressure) Button btn_sampleObservable;
  @InjectView(R.id.btn_sampleObservableTransformation) Button btn_sampleObservableTransformation;

  private Navigator navigator;

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
    this.navigator.navigateToObserverSample(this);
  }

  @OnClick(R.id.btn_sampleBackpressure) void navigateToBackpressureSample() {
    this.navigator.navigateToBackpressureSample(this);
  }

  @OnClick(R.id.btn_sampleObservableTransformation) void navigateToObservableTransformSample() {
    this.navigator.navigateToObservableTransformSample(this);
  }
}
