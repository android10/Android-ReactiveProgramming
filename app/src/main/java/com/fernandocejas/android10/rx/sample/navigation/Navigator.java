/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.rx.sample.navigation;

import android.content.Context;
import android.content.Intent;
import com.fernandocejas.android10.rx.sample.activity.ActivityBackpressureSamples;
import com.fernandocejas.android10.rx.sample.activity.ActivityObservableCompositionSamples;
import com.fernandocejas.android10.rx.sample.activity.ActivityObservableConcatVsFlatMapSample;
import com.fernandocejas.android10.rx.sample.activity.ActivitySubscriberSample;

public class Navigator {

  public Navigator() {
    //empty
  }

  public void navigateToSubscriberSample(Context context) {
    if (context != null) {
      Intent intentToLaunch = ActivitySubscriberSample.getCallingIntent(context);
      context.startActivity(intentToLaunch);
    }
  }

  public void navigateToObservableCompositionSamples(Context context) {
    if (context != null) {
      Intent intentToLaunch = ActivityObservableCompositionSamples.getCallingIntent(context);
      context.startActivity(intentToLaunch);
    }
  }

  public void navigateToBackpressureSamples(Context context) {
    if (context != null) {
      Intent intentToLaunch = ActivityBackpressureSamples.getCallingIntent(context);
      context.startActivity(intentToLaunch);
    }
  }

  public void navigateToObservableConcatVsFlatMap(Context context) {
    if (context != null) {
      Intent intentToLaunch = ActivityObservableConcatVsFlatMapSample.getCallingIntent(context);
      context.startActivity(intentToLaunch);
    }
  }
}
