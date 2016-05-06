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
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.fernandocejas.android10.rx.sample.R;
import com.fernandocejas.android10.rx.sample.data.DataManager;
import com.fernandocejas.android10.rx.sample.data.NumberGenerator;
import com.fernandocejas.android10.rx.sample.data.StringGenerator;
import com.fernandocejas.android10.rx.sample.executor.JobExecutor;
import com.fernandocejas.arrow.annotations.See;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.Subscriptions;

@See(ref = "http://fernandocejas.com/2015/01/11/rxjava-observable-tranformation-concatmap-vs-flatmap/")
public class ActivityObservableConcatVsFlatMapSample extends BaseActivity {

  private static final String LOG_TAG = "ConcatVsFlatMap";
  private static final String SEPARATOR = " ";

  @Bind(R.id.tv_streamOriginalOrder) TextView tv_streamOriginalOrder;
  @Bind(R.id.tv_flatMapResult) TextView tv_flatMapResult;
  @Bind(R.id.tv_concatMapResult) TextView tv_concatMapResult;

  private DataManager dataManager;
  private Subscription subscription;

  public static Intent getCallingIntent(Context context) {
    return new Intent(context, ActivityObservableConcatVsFlatMapSample.class);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sample_observervable_composition_flat_vs_concat);

    ButterKnife.bind(this);
    initialize();
    populateData();
  }

  @Override
  protected void onDestroy() {
    subscription.unsubscribe();
    super.onDestroy();
  }

  private void initialize() {
    dataManager = new DataManager(new StringGenerator(), new NumberGenerator(),
        JobExecutor.getInstance());
    subscription = Subscriptions.unsubscribed();
  }

  @OnClick(R.id.btn_flatMap) void onFlatMapClick() {
    final Observer<Integer> observer = new Observer<Integer>() {
      final StringBuilder stringBuilder = new StringBuilder(40);

      @Override public void onNext(Integer number) {
        stringBuilder.append(number);
        stringBuilder.append(SEPARATOR);
        debugLog("onFlatMapClick() ------>>>> " + number);
      }

      @Override public void onCompleted() {
        stringBuilder.append("Complete!");
        printFlatMapResult(stringBuilder.toString());
        showToast("flatMap() Sequence Completed!!!");
      }

      @Override public void onError(Throwable e) {
        // handle the exception
      }
    };

    buildNumbersObservable()
        .flatMap(dataManager::squareOfAsync)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(observer);
  }

  @OnClick(R.id.btn_concatMap) void onConcatMapClick() {
    final Observer<Integer> observer = new Observer<Integer>() {
      final StringBuilder stringBuilder = new StringBuilder(40);

      @Override public void onNext(Integer number) {
        stringBuilder.append(number);
        stringBuilder.append(SEPARATOR);
        debugLog("onConcatMapClick() ------>>>> " + number);
      }

      @Override public void onCompleted() {
        stringBuilder.append("Complete!");
        printConcatMapResult(stringBuilder.toString());
        showToast("concatMap() Sequence Completed!!!");
      }

      @Override public void onError(Throwable e) {
        // handle the exception
      }
    };

    buildNumbersObservable()
        .concatMap(dataManager::squareOfAsync)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(observer);
  }

  private Observable<Integer> buildNumbersObservable() {
    return dataManager.numbers();
  }

  private void showToast(String message) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
  }

  private void debugLog(String message) {
    Log.d(LOG_TAG, message);
  }

  private void printFlatMapResult(String result) {
    tv_flatMapResult.setText(result);
  }

  private void printConcatMapResult(String result) {
    tv_concatMapResult.setText(result);
  }

  private void populateData() {
    StringBuilder stringBuilder = new StringBuilder(15);
    for (int number : dataManager.getNumbersSynchronously()) {
      stringBuilder.append(number);
      stringBuilder.append(SEPARATOR);
    }
    this.tv_streamOriginalOrder.setText(stringBuilder.toString());
  }
}
