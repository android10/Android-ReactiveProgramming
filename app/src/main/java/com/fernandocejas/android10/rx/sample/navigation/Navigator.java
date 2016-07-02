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
package com.fernandocejas.android10.rx.sample.navigation;

import android.content.Context;
import android.content.Intent;
import com.fernandocejas.android10.rx.sample.activity.backpressure.ActivityBackpressureSamples;
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
