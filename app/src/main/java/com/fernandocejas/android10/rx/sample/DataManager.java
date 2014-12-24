/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.rx.sample;

import java.util.ArrayList;
import java.util.List;
import rx.Observable;

public class DataManager {

  private final List<String> usernames;
  private final RandomStringGenerator randomStringGenerator;

  public DataManager() {
    this.usernames = new ArrayList<>();
    this.randomStringGenerator = new RandomStringGenerator();

    populateUsernameList();
  }

  public Observable<String> getUsernames() {
    return null;
  }

  public void addRandomString() {
    this.usernames.add(randomStringGenerator.nextString());
  }

  private void populateUsernameList() {
    for (int i = 0; i < 10; i++) {
      this.usernames.add(randomStringGenerator.nextString());
    }
  }
}
