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

  private final List<String> elements;
  private final RandomStringGenerator randomStringGenerator;

  public DataManager() {
    this.elements = new ArrayList<>();
    this.randomStringGenerator = new RandomStringGenerator();

    populateUsernameList();
  }

  public Observable<String> getElements() {
    Observable data = Observable.from(elements);
    return data;
  }

  public Observable<String> getNewElement() {
    Observable data = Observable.just(randomStringGenerator.nextString());
    return data;
  }

  private void populateUsernameList() {
    for (int i = 0; i < 10; i++) {
      this.elements.add(randomStringGenerator.nextString());
    }
  }
}
