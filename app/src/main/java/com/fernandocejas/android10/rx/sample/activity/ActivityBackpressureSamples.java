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
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.fernandocejas.android10.rx.sample.R;
import com.fernandocejas.android10.rx.sample.data.DataManager;
import com.fernandocejas.android10.rx.sample.data.NumberGenerator;
import com.fernandocejas.android10.rx.sample.data.StringGenerator;
import com.fernandocejas.android10.rx.sample.executor.JobExecutor;
import com.fernandocejas.arrow.optional.Optional;
import com.fernandocejas.arrow.strings.Strings;
import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import org.jetbrains.annotations.NotNull;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.exceptions.MissingBackpressureException;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

public class ActivityBackpressureSamples extends BaseActivity {

  @Bind(R.id.tv_sampleDescription) TextView tv_sampleDescription;
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
    dataManager = new DataManager(new StringGenerator(),
        new NumberGenerator(), JobExecutor.getInstance());
  }

  @OnClick(R.id.btn_backpressureException) void onBackpressureExceptionClick() {
    dataManager.milliseconds()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.computation())
        .subscribe(new BackpressureSubscriber(getString(R.string.btn_text_backpressure_exception)));
  }

  @RxLogSubscriber
  private class BackpressureSubscriber extends Subscriber<Long> {

    private final String name;

    private int itemsEmmited = 0;

    BackpressureSubscriber(String name) {
      this.name = name;
    }

    @Override public void onNext(Long item) {
      itemsEmmited++;
    }

    @Override public void onCompleted() {
      printData(Operation.success());
    }

    @Override public void onError(Throwable throwable) {
      if (throwable instanceof MissingBackpressureException) {
        printData(Operation.failure(throwable));
      }
    }

    private void printData(@NotNull Operation operation) {
      tv_sampleDescription.setText(name);
      tv_itemsEmitted.setText(String.valueOf(itemsEmmited));
      String resultMessage = Strings.EMPTY;
      switch (operation.result) {
        case SUCCESS:
          resultMessage = getString(R.string.string_success);
          break;
        case FAILURE:
          if (operation.throwable.isPresent()) {
            resultMessage = String.format(getString(R.string.string_failure),
                operation.throwable.get().toString());
          } else {
            resultMessage = getString(R.string.string_failure);
          }
          break;
      }
      tv_result.setText(resultMessage);
    }
  }

  private static class Operation {
    enum Result {
      SUCCESS,
      FAILURE
    }

    private final Optional<Throwable> throwable;
    private final Result result;

    static Operation success() {
      return new Operation(null, Result.SUCCESS);
    }

    static Operation failure(Throwable throwable) {
      return new Operation(throwable, Result.FAILURE);
    }

    private Operation(Throwable throwable, Result result) {
      this.throwable = Optional.fromNullable(throwable);
      this.result = result;
    }
  }
}
