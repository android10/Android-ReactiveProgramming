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
package com.fernandocejas.android10.rx.sample.activity.backpressure;

import com.fernandocejas.arrow.optional.Optional;

class OperationResult {
  enum Result {
    SUCCESS,
    FAILURE
  }
  private final Optional<Throwable> throwable;
  private final Result result;

  private OperationResult(Throwable throwable, Result result) {
    this.throwable = Optional.fromNullable(throwable);
    this.result = result;
  }

  static OperationResult success() {
    return new OperationResult(null, Result.SUCCESS);
  }

  static OperationResult failure(Throwable throwable) {
    return new OperationResult(throwable, Result.FAILURE);
  }

  Optional<Throwable> throwable() {
    return throwable;
  }

  Result result() {
    return result;
  }
}
