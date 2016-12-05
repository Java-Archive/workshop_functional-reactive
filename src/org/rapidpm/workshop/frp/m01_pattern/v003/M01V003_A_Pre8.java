package org.rapidpm.workshop.frp.m01_pattern.v003;

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
public class M01V003_A_Pre8 {

  public interface CalculatorService {
    double calculate(double value);
  }

  // Default we want to Decorate
  public static class CalculatorDefaultService implements CalculatorService {
    @Override
    public double calculate(final double value) {
      final double result = value + 100;
      System.out.println(this.getClass().getSimpleName() + " - in / out = " + value + " - " + result);
      return result;
    }
  }


  //not nice...
  public static abstract class AbstractCalculatorService implements CalculatorService {
    private final CalculatorService delegator;

    public AbstractCalculatorService(final CalculatorService delegator) {
      this.delegator = delegator;
    }

    protected abstract double calculateImpl(final double value);

    public final double calculate(final double value) {
      return calculateImpl(delegator.calculate(value));
    }
  }



  public static class RoundUpService extends AbstractCalculatorService {
    public RoundUpService(final CalculatorService delegator) {
      super(delegator);
    }

    protected double calculateImpl(double value) {
      final double result = Math.ceil(value);
      System.out.println(this.getClass().getSimpleName() + " - in / out = " + value + " - " + result);
      return result;
    }
  }

  public static class AddHalfService extends AbstractCalculatorService {
    public AddHalfService(final CalculatorService delegator) {
      super(delegator);
    }

    protected double calculateImpl(double value) {
      final double result = value + 0.5d;
      System.out.println(this.getClass().getSimpleName() + " - in / out = " + value + " - " + result);
      return result;
    }
  }

  public static class SubOneService extends AbstractCalculatorService {
    public SubOneService(final CalculatorService delegator) {
      super(delegator);
    }

    protected double calculateImpl(double value) {
      final double result = value - 1;
      System.out.println(this.getClass().getSimpleName() + " - in / out = " + value + " - " + result);
      return result;
    }
  }


  public static void main(String[] args) {
    final CalculatorService calculatorService =
        new RoundUpService(
            new AddHalfService(
                new SubOneService(
                    new CalculatorDefaultService())));

    final double calculate = calculatorService.calculate(10.3d);
    System.out.println("calculate = " + calculate);


  }
}
