/**
 * Copyright (C) 2015 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.rx.sample.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.fernandocejas.android10.rx.sample.R;
import com.fernandocejas.android10.rx.sample.data.DataManager;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.subscriptions.Subscriptions;

public class SampleActivityObservableTransfor extends Activity {

  private static final String LOG_TAG = "SampleActivityObservableTransfor";

  private static final String SEPARATOR = " ";

  @InjectView(R.id.tv_streamOriginalOrder) TextView tv_streamOriginalOrder;
  @InjectView(R.id.tv_flatMapResult) TextView tv_flatMapResult;
  @InjectView(R.id.tv_concatMapResult) TextView tv_concatMapResult;
  @InjectView(R.id.btn_flatMap) Button btn_flatMap;
  @InjectView(R.id.btn_concatMap) Button btn_concatMap;

  private DataManager dataManager;

  private Subscription subscription;

  private final Func1<Integer, Observable<Integer>> SQUARE_OF_NUMBER =
      new Func1<Integer, Observable<Integer>>() {
        @Override public Observable<Integer> call(Integer number) {
          return dataManager.squareOf(number);
        }
      };

  public static Intent getCallingIntent(Context context) {
    return new Intent(context, SampleActivityObservableTransfor.class);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sample_observervable_transform);

    ButterKnife.inject(this);
    initialize();
    populateData();
  }

  @Override
  protected void onDestroy() {
    this.subscription.unsubscribe();
    super.onDestroy();
  }

  private void initialize() {
    this.dataManager = new DataManager();
    this.subscription = Subscriptions.empty();
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

    this.buildNumbersObservable().flatMap(SQUARE_OF_NUMBER)
        .observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
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

    this.buildNumbersObservable().concatMap(SQUARE_OF_NUMBER)
        .observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
  }

  private Observable<Integer> buildNumbersObservable() {
    return this.dataManager.getNumbers();
  }

  private void showToast(String message) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
  }

  private void debugLog(String message) {
    Log.d(LOG_TAG, message);
  }

  private void printFlatMapResult(String result) {
    this.tv_flatMapResult.setText(result);
  }

  private void printConcatMapResult(String result) {
    this.tv_concatMapResult.setText(result);
  }

  private void populateData() {
    StringBuilder stringBuilder = new StringBuilder(15);
    for (int number : dataManager.getNumbersSync()) {
      stringBuilder.append(number);
      stringBuilder.append(SEPARATOR);
    }
    this.tv_streamOriginalOrder.setText(stringBuilder.toString());
  }
}
