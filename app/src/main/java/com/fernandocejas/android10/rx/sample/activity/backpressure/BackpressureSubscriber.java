/**
 * Copyright (C) 2016 android10.org Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fernandocejas.android10.rx.sample.activity.backpressure;

import io.reactivex.subscribers.DisposableSubscriber;
import java.lang.ref.WeakReference;

class BackpressureSubscriber<T> extends DisposableSubscriber<T> {

  interface BackPressureResultListener {
    void onOperationStart(String name, long itemsRequested);

    void onOperationProgress(long itemsProcessedSoFar);

    void onOperationResult(long itemsEmitted, OperationResult operation);
  }

  private final WeakReference<BackPressureResultListener> backPressureListener;
  private final String name;
  private final long itemsRequested;
  private boolean shouldRequestItem;

  private long itemsEmitted = 0;
  private long itemsRequestedProcessed = 0;

  BackpressureSubscriber(BackPressureResultListener listener, String name) {
    this(listener, name, Long.MAX_VALUE);
  }

  BackpressureSubscriber(BackPressureResultListener listener, String name, long requestedItems) {
    this.backPressureListener = new WeakReference<>(listener);
    this.name = name;
    this.itemsRequested = requestedItems;
    this.shouldRequestItem = requestedItems != Long.MAX_VALUE;
  }

  @Override
  public void onStart() {
    if (shouldRequestItem) {
      request(itemsRequested);
    }
    sendStartData();
  }

  @Override
  public void onNext(T item) {
    itemsEmitted++;
    sendProgressData(itemsEmitted);
    requestMoreItems();
  }

  @Override
  public void onComplete() {
    sendResultData(OperationResult.success());
  }

  @Override
  public void onError(Throwable throwable) {
    //if (throwable instanceof MissingBackpressureException) {
    //    sendResultData(OperationResult.failure(throwable));
    //}
  }

  private void requestMoreItems() {
    itemsRequestedProcessed++;
    if (shouldRequestItem && itemsRequestedProcessed >= itemsRequested) {
      request(itemsRequested);
      itemsRequestedProcessed = 0;
    }
  }

  private void sendStartData() {
    if (backPressureListener.get() != null) {
      backPressureListener.get().onOperationStart(name, itemsRequested);
    }
  }

  private void sendProgressData(long itemsProcessedSoFar) {
    if (backPressureListener.get() != null) {
      backPressureListener.get().onOperationProgress(itemsProcessedSoFar);
    }
  }

  private void sendResultData(OperationResult operation) {
    if (backPressureListener.get() != null) {
      backPressureListener.get().onOperationResult(itemsEmitted, operation);
    }
  }

  //    private static class BackPressureProducer<T> implements Producer {
  //        private final Subscriber<T> subscriber;
  //
  //        BackPressureProducer(Subscriber subscriber) {
  //            this.subscriber = subscriber;
  //        }
  //
  //        @Override
  //        public void request(long requested) {
  //            for (int i = 0; i < requested && !subscriber.isUnsubscribed(); i++) {
  ////                subscriber.onNext(T);
  //            }
  //        }
  //    }
}
