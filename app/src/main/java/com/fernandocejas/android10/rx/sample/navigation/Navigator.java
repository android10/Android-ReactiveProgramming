/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.rx.sample.navigation;

import android.content.Context;
import android.content.Intent;
import com.fernandocejas.android10.rx.sample.activity.SampleActivityObservable;
import com.fernandocejas.android10.rx.sample.activity.SampleActivityObservableTransfor;
import com.fernandocejas.android10.rx.sample.activity.SampleActivityObserver;

public class Navigator {

  public Navigator() {
    //empty
  }

  public void navigateToObserverSample(Context context) {
    if (context != null) {
      Intent intentToLaunch = SampleActivityObserver.getCallingIntent(context);
      context.startActivity(intentToLaunch);
    }
  }

  public void navigateToObservableSample(Context context) {
    if (context != null) {
      Intent intentToLaunch = SampleActivityObservable.getCallingIntent(context);
      context.startActivity(intentToLaunch);
    }
  }

  public void navigateToObservableTransformSample(Context context) {
    if (context != null) {
      Intent intentToLaunch = SampleActivityObservableTransfor.getCallingIntent(context);
      context.startActivity(intentToLaunch);
    }
  }
}
