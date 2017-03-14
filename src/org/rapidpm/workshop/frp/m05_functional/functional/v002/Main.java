package org.rapidpm.workshop.frp.m05_functional.functional.v002;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Copyright (C) 2017 RapidPM - Sven Ruppert
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
 * Created by Sven Ruppert - RapidPM - Team on 08.02.17.
 */
public class Main {


  public static void main(String[] args) {
    final BiFunction<Integer, Integer, Integer> biFuncA = (a, b) -> a + b;

    final BiFunction<Integer, Integer, Integer> andThen = biFuncA.andThen(new Function<Integer, Integer>() {
      @Override
      public Integer apply(final Integer integer) {
        return integer + 10;
      }
    });


    final BiFunction<Integer, Integer, Integer> biFuncB = (a, b) -> a - b;

    final Integer apply = biFuncA.apply(biFuncB.apply(2, 3), 1);

//    final BiFunction<BiFunction<Integer, Integer, Integer>, Integer, Integer> biFuncC
//        = (biFunc, i) -> biFunc.apply(a, b) + i;
//    final Integer apply1 = biFuncC.apply(biFuncA, 20);

    final BiFunction<Integer, Integer, Integer> doWork = new MyLegacyClassA()::doWork;
    final BiFunction<String, String, String> doSomething = new MyLegacyClassB()::doSomething;

    final Integer workOnBiFuncA1 = workOnBiFunc(doWork, () -> 1, () -> 2);
    final Integer workOnBiFuncA2 = workOnBiFunc(new MyLegacyClassA()::doWork, () -> 1, () -> 2);
    final String workOnBiFuncB  = workOnBiFunc(doSomething, () -> "1", () -> "2");
  }

  public static <T1, T2, R> R workOnBiFunc(final BiFunction<T1, T2, R> func,
                                           final Supplier<T1> supA, final Supplier<T2> supB) {
    return func.apply(supA.get(), supB.get());
  }

  public static class MyLegacyClassA {
    public Integer doWork(Integer a, Integer b) {
      return a + b;
    }
  }

  public static class MyLegacyClassB {
    public String doSomething(String a, String b) {
      return a + b;
    }
  }

}
