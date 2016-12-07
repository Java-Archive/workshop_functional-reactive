package org.rapidpm.workshop.frp.m02_pattern.v003;

import java.util.Objects;

/**
 * Copyright (C) 2010 RapidPM
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Created by RapidPM - Team on 04.12.16.
 */
public class M02V003_B {

  @FunctionalInterface
  public interface CalculatorService {
    double calculate(double value);
  }

  @FunctionalInterface
  public interface CalculatorDecoratorService extends CalculatorService {

    default CalculatorDecoratorService calculateBefore(CalculatorService before) {
      Objects.requireNonNull(before);
      return (double value) -> calculate(before.calculate(value));
    }
  }



  public static final CalculatorService CalculatorDefaultService = (value) -> {
    final double result = value + 100;
    System.out.println("CalculatorDefaultService - in / out = " + value + " - " + result);
    return result;
  };

  public static final CalculatorDecoratorService RoundUpService = (value) -> {
    final double result = Math.ceil(value);
    System.out.println("RoundUpService - in / out = " + value + " - " + result);
    return result;
  };

  public static final CalculatorDecoratorService AddHalfService = (value) -> {
    final double result = value + 0.5d;
    System.out.println("AddHalfService - in / out = " + value + " - " + result);
    return result;
  };
  public static final CalculatorDecoratorService SubOneService = (value) -> {
    final double result = value - 1;
    System.out.println("SubOneService - in / out = " + value + " - " + result);
    return result;
  };



  public static void main( String[] args ) {

    final double calculate = RoundUpService
        .calculateBefore(AddHalfService)
        .calculateBefore(SubOneService)
        .calculateBefore(CalculatorDefaultService)
        .calculate(10.3d);

    System.out.println("calculate = " + calculate);

    final double calculateShort = RoundUpService
        .calculateBefore(value -> value + 0.5d)
        .calculateBefore(value -> value - 1)
        .calculateBefore(CalculatorDefaultService)
        .calculate(10.3d);

    System.out.println("calculateShort = " + calculateShort);

  }
}
