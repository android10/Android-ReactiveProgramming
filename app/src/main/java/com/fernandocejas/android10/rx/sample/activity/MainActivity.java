/**
 * Copyright (C) 2015 android10.org Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fernandocejas.android10.rx.sample.activity;

import android.os.Bundle;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.fernandocejas.android10.rx.sample.R;
import com.fernandocejas.frodo.annotation.RxLogObservable;
import java.util.Arrays;
import rx.Observable;

public class MainActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
  }

  @OnClick(R.id.btn_sampleObserver) void navigateToObserverSample() {
    //navigator.navigateToSubscriberSample(this);
    Observable.zip(sampleOne(), sampleTwo(), (integer, integer2) -> integer + integer2).subscribe();
  }

  @OnClick(R.id.btn_sampleBackpressure) void navigateToBackpressureSample() {
    //navigator.navigateToBackpressureSamples(this);
    Observable.zip(sampleOne(), sampleTwo(), (integer, integer2) -> integer + integer2
    ).subscribe();
  }

  @OnClick(R.id.btn_sampleObservableComposition) void navigateToObservableCompositionSamples() {
    navigator.navigateToObservableCompositionSamples(this);
  }

  @RxLogObservable
  private Observable<Integer> sampleOne() {
    return Observable.from(Arrays.asList(1, 2, 3, 4, 5));
  }

  @RxLogObservable
  private Observable<Integer> sampleTwo() {
    return Observable.from(Arrays.asList(10, 20, 30, 40, 50));
  }
}
