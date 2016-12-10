/**
 * Copyright (C) 2016 android10.org Open Source Project
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
package com.fernandocejas.android10.rx.sample.activity.backpressure;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.fernandocejas.android10.rx.sample.R;
import com.fernandocejas.android10.rx.sample.activity.BaseActivity;
import com.fernandocejas.android10.rx.sample.data.DataManager;
import com.fernandocejas.android10.rx.sample.data.NumberGenerator;
import com.fernandocejas.android10.rx.sample.data.StringGenerator;
import com.fernandocejas.android10.rx.sample.executor.JobExecutor;
import com.fernandocejas.arrow.strings.Strings;
import java.util.concurrent.TimeUnit;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

public class ActivityBackpressureSamples extends BaseActivity implements
    BackpressureSubscriber.BackPressureResultListener {

  @Bind(R.id.sv_container) ScrollView sv_container;
  @Bind(R.id.tv_sampleDescription) TextView tv_sampleDescription;
  @Bind(R.id.tv_itemsRequested) TextView tv_itemsRequested;
  @Bind(R.id.tv_itemsProcessed) TextView tv_itemsProcessed;
  @Bind(R.id.tv_itemsEmitted) TextView tv_itemsEmitted;
  @Bind(R.id.tv_result) TextView tv_result;

  private Subscription subscription;
  private DataManager dataManager;

  public static Intent getCallingIntent(Context context) {
    return new Intent(context, ActivityBackpressureSamples.class);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initialize();
  }

  @Override
  protected void onDestroy() {
    subscription.unsubscribe();
    super.onDestroy();
  }

  private void initialize() {
    requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
    setContentView(R.layout.activity_samples_backpressure);

    ButterKnife.bind(this);
    clearUpUI();

    subscription = Subscriptions.unsubscribed();
    dataManager = new DataManager(new StringGenerator(),
        new NumberGenerator(), JobExecutor.getInstance());
  }

  private void clearUpUI() {
    tv_sampleDescription.setText(Strings.EMPTY);
    tv_itemsRequested.setText(Strings.EMPTY);
    tv_itemsProcessed.setText(Strings.EMPTY);
    tv_itemsEmitted.setText(Strings.EMPTY);
    tv_result.setText(Strings.EMPTY);
  }

  private void toggleProgressBar(boolean visible) {
    setProgressBarIndeterminateVisibility(visible);
  }

  @OnClick(R.id.btn_backpressureException) void onBackpressureExceptionClick() {
    //dataManager.milliseconds(1000)
    //    .observeOn(AndroidSchedulers.mainThread())
    //    .subscribeOn(Schedulers.computation())
    //    .subscribe(new BackpressureSubscriber<>(this, getString(R.string.btn_text_backpressure_exception)));
    dataManager.milliseconds(100)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.computation())
        .subscribe(new BackpressureSubscriber<>(this, getString(R.string.btn_text_backpressure_exception), 15));
  }

  @OnClick(R.id.btn_backpressureDrop) void onBackpressureDropClick() {
    dataManager.milliseconds(1000)
        .onBackpressureDrop()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.computation())
        .subscribe(new BackpressureSubscriber<>(this, getString(R.string.btn_text_backpressure_drop)));
  }

  @OnClick(R.id.btn_backpressureBuffer) void onBackpressureBuffer() {
    dataManager.milliseconds(600)
        .onBackpressureBuffer(200)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.computation())
        .subscribe(new BackpressureSubscriber<>(this, getString(R.string.btn_text_backpressure_buffer)));
  }

  @OnClick(R.id.btn_backpressureWindow) void onBackpressureWindow() {
    dataManager.milliseconds(1000)
        .window(5)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.computation())
        .subscribe(new BackpressureSubscriber<>(this, getString(R.string.btn_text_backpressure_window)));
  }

  @OnClick(R.id.btn_backpressureToList) void onBackpressureToList() {
    dataManager.milliseconds(1000)
        .toList()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.computation())
        .subscribe(new BackpressureSubscriber<>(this, getString(R.string.btn_text_backpressure_toList)));
  }

  @OnClick(R.id.btn_backpressureThrottleLast) void onBackpressureThrottleLast() {
    dataManager.milliseconds(10000)
        .throttleLast(10, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.computation())
        .subscribe(new BackpressureSubscriber<>(this, getString(R.string.btn_text_backpressure_throttleLast)));
  }

  @Override
  public void onOperationStart(String name, long itemsRequested) {
    clearUpUI();
    toggleProgressBar(true);
    sv_container.fullScroll(ScrollView.FOCUS_DOWN);
    tv_sampleDescription.setText(name);
    //This check is because you can pass a magic number to request, request(Long.MAX_VALUE),
    //to disable reactive pull backpressure and to ask the Observable to emit items at its own pace.
    //https://github.com/ReactiveX/RxJava/wiki/Backpressure
    if (itemsRequested != Long.MAX_VALUE) {
      tv_itemsRequested.setText(String.valueOf(itemsRequested));
    } else {
      tv_itemsRequested.setText("Observable emitting at its own pace");
    }
  }

  @Override public void onOperationProgress(long itemsProcessedSoFar) {
    tv_itemsProcessed.setText(String.valueOf(itemsProcessedSoFar));
  }

  @Override
  public void onOperationResult(long itemsEmitted, OperationResult operation) {
    tv_itemsEmitted.setText(String.valueOf(itemsEmitted));
    switch (operation.result()) {
      case SUCCESS:
        tv_result.setText(getString(R.string.string_success));
        tv_result.setTextColor(ColorStateList.valueOf(Color.BLUE));
        break;
      case FAILURE:
        String failureMessage;
        if (operation.throwable().isPresent()) {
          failureMessage = String.format(getString(R.string.string_failure),
              operation.throwable().get().toString());
        } else {
          failureMessage = getString(R.string.string_failure);
        }
        tv_result.setText(failureMessage);
        tv_result.setTextColor(ColorStateList.valueOf(Color.RED));
        break;
      default:
        tv_result.setText(Strings.EMPTY);
    }
    toggleProgressBar(false);
  }
}
