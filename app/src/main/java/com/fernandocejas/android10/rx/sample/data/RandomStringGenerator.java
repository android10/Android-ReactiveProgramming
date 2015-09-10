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
package com.fernandocejas.android10.rx.sample.data;

import java.util.Random;

public class RandomStringGenerator {

  private static final int DEFAULT_STRING_LENGHT = 12;
  private static final char[] symbols;

  static {
    StringBuilder tmpSymbols = new StringBuilder();
    for (char numberChar = '0'; numberChar <= '9'; numberChar++) {
      tmpSymbols.append(numberChar);
    }
    for (char letterChar = 'a'; letterChar <= 'z'; letterChar++) {
      tmpSymbols.append(letterChar);
    }
    symbols = tmpSymbols.toString().toCharArray();
  }

  private final int stringLenght;
  private final char[] charArray;
  private final Random random;

  public RandomStringGenerator() {
    this(DEFAULT_STRING_LENGHT);
  }

  public RandomStringGenerator(int stringLength) {
    this.stringLenght = stringLength;
    this.charArray = new char[this.stringLenght];
    this.random = new Random();
  }

  String nextString() {
    for (int i = 0; i < charArray.length; i++) {
      charArray[i] = symbols[random.nextInt(symbols.length)];
    }
    return String.valueOf(charArray);
  }
}
