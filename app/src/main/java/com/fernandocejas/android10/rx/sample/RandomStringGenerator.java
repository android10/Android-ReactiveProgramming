/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.rx.sample;

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

  public String nextString() {
    for (int i = 0; i < charArray.length; i++) {
      charArray[i] = symbols[random.nextInt(symbols.length)];
    }
    return String.valueOf(charArray);
  }
}
