package com.fernandocejas.android10.rx.sample.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.fernandocejas.android10.rx.sample.R;
import com.fernandocejas.android10.rx.sample.navigation.Navigator;

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
public class ActivityObservableCompositionSamples extends Activity {

  private Navigator navigator;

  public static Intent getCallingIntent(Context context) {
    return new Intent(context, ActivityObservableCompositionSamples.class);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_samples_observable_composition);
    ButterKnife.inject(this);
    this.initialize();
  }

  private void initialize() {
    this.navigator = new Navigator();
  }

  @OnClick(R.id.btn_sampleConcatVsFlatMap) void navigateToObservableConcatVsFlatMap() {
    this.navigator.navigateToObservableConcatVsFlatMap(this);
  }
}
