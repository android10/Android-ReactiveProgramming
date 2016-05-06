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

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.schedulers.Schedulers;

public class DataManager {

  private final StringGenerator stringGenerator;
  private final NumberGenerator numberGenerator;
  private final Executor jobExecutor;

  public DataManager(StringGenerator stringGenerator,
      NumberGenerator numberGenerator, Executor jobExecutor) {
    this.stringGenerator = stringGenerator;
    this.numberGenerator = numberGenerator;
    this.jobExecutor = jobExecutor;
  }

  public Observable<Integer> numbers() {
    return Observable.from(numberGenerator.numbers());
  }

  public Observable<Long> milliseconds() {
    return Observable.interval(0, 1, TimeUnit.MILLISECONDS);
  }

  public Observable<Integer> squareOfAsync(int number) {
    return Observable.just(number * number).subscribeOn(Schedulers.from(jobExecutor));
  }

  public Observable<String> elements() {
    return Observable.from(stringGenerator.randomStringList());
  }

  public Observable<String> newElement() {
    return Observable
        .just(stringGenerator.nextString())
        .map((string -> "RandomItem" + string));
  }

  public List<Integer> getNumbersSynchronously() {
    return numberGenerator.numbers();
  }
}
