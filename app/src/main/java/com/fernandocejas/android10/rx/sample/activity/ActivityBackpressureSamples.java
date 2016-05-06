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

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.fernandocejas.android10.rx.sample.R;
import com.fernandocejas.frodo.annotation.RxLogObservable;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
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

    ButterKnife.bind(this);
    initialize();
  }

  @Override
  protected void onDestroy() {
    subscription.unsubscribe();
    super.onDestroy();
  }

  private void initialize() {
    subscription = Subscriptions.unsubscribed();
  }

  @OnClick(R.id.btn_sample) void onSampleClick() {
    //milliseconds().observeOn(AndroidSchedulers.mainThread()).subscribe();

    //final Scheduler scheduler = Schedulers.newThread();
    //milliseconds()
    //    .subscribeOn(scheduler)
    //    .observeOn(scheduler)
    //    .subscribe();

    milliseconds()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.computation())
        .subscribe(new BackpressureSubscriber());
  }

  @RxLogObservable
  private Observable<Long> milliseconds() {
    return Observable.interval(0, 1, TimeUnit.MILLISECONDS);
  }

  private static class BackpressureSubscriber extends Subscriber<Long> {
    //private final String

    @Override public void onCompleted() {

    }

    @Override public void onError(Throwable e) {

    }

    @Override public void onNext(Long aLong) {

    }
  }
}
