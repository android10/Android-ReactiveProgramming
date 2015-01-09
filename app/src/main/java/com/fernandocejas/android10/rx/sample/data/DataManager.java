/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.rx.sample.data;

import com.fernandocejas.android10.rx.sample.util.RandomStringGenerator;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;

public class DataManager {

  private final int[] numbers = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};

  private final List<String> elements;
  private final RandomStringGenerator randomStringGenerator;

  public DataManager() {
    this.elements = new ArrayList<>();
    this.randomStringGenerator = new RandomStringGenerator();

    populateUsernameList();
  }

  public Observable getNumbers() {
    return Observable.just(numbers);
  }

  public Observable<String> getElements() {
    return Observable.from(elements);
  }

  public Observable<String> getNewElement() {
    return Observable.just(randomStringGenerator.nextString());
  }

  private void populateUsernameList() {
    for (int i = 0; i < 10; i++) {
      this.elements.add(randomStringGenerator.nextString());
    }
  }
}
