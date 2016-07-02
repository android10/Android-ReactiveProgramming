/**
 * Copyright (C) 2016 android10.org Open Source Project
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
package com.fernandocejas.android10.rx.sample;

import android.app.Application;
import android.widget.Toast;
import com.fernandocejas.arrow.annotations.Issue;

public class AndroidApplication extends Application {

  @Override public void onCreate() {
    super.onCreate();
    setupRxRingBufferSize();
  }

  @Issue(ref = "https://github.com/ReactiveX/RxJava/issues/1820")
  private void setupRxRingBufferSize() {
    //Default on Android is 16 for performance reasons
    //This is a hack and should not be used it, it is for learning purpose
    final String rxBufferSizeProperty = "rx.ring-buffer.size";
    final String rxBufferSizeValue = "16";
    System.setProperty(rxBufferSizeProperty, rxBufferSizeValue);
    Toast.makeText(this, rxBufferSizeProperty + "=" + rxBufferSizeValue, Toast.LENGTH_SHORT).show();
  }
}
