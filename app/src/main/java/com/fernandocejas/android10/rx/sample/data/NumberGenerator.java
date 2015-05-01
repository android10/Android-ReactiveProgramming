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
package com.fernandocejas.android10.rx.sample.data;

import android.os.SystemClock;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func2;

public class NumberGenerator {

  public static final Func2<Integer, Integer, Integer> sumNumbers =
      new Func2<Integer, Integer, Integer>() {
        @Override public Integer call(Integer integer, Integer integer2) {
          return integer + integer2;
        }
      };

  public NumberGenerator() {}

  public Observable<Integer> getResults() {
    return Observable.zip(getNumbersFast(), getNumbersSlow(), sumNumbers);
  }

  private Observable<Integer> getNumbersSlow() {
    return Observable.create(new Observable.OnSubscribe<Integer>() {

      @Override public void call(Subscriber<? super Integer> subscriber) {
        for (int i = 1; i < 100000; i++) {
          SystemClock.sleep(50);
          subscriber.onNext(i);
        }
        subscriber.onCompleted();
      }
    });
  }

  private Observable<Integer> getNumbersFast() {
    return Observable.create(new Observable.OnSubscribe<Integer>() {

      @Override public void call(Subscriber<? super Integer> subscriber) {
        for (int i = 1; i < 100000; i++) {
          subscriber.onNext(i);
        }
        subscriber.onCompleted();
      }
    });
  }
}
