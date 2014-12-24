/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.rx.sample;

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

  public List<String> getElementsList() {
    return this.elements;
  }

  public void addRandomString() {
    this.elements.add(randomStringGenerator.nextString());
  }

  private void populateUsernameList() {
    for (int i = 0; i < 10; i++) {
      this.elements.add(randomStringGenerator.nextString());
    }
  }
}
