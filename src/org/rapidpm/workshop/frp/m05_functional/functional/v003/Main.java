package org.rapidpm.workshop.frp.m05_functional.functional.v003;

import java.util.function.Function;

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
 * Created by Sven Ruppert - RapidPM - Team on 14.03.17.
 */
public class Main {
  public static void main(String[] args) {

    final Function<
        Function<Integer, Integer>,
        Function<
            Function<Integer, Integer>,
            Function<Integer, Integer>>> higherCompose = higherCompose();

//    final Integer apply = <Integer,Integer,Integer>higherCompose() // won´t compile
    final Integer apply = Main.<Integer, Integer, Integer>higherCompose()
        .apply(x -> 2 * x)  // second
        .apply(y -> y + 1)  // first
        .apply(2);

    System.out.println("apply = " + apply);


    Function<Integer, Integer> func = (x) -> x + 2;
    int result = func
        .compose((Function<Integer, Integer>) x -> x + 10)
        .compose((Function<Integer, Integer>) x -> x + 5)
//        .compose(x -> x + 10)
//        .compose(x -> x + 5)
        .apply(2);


  }


  public static <T, U, V> Function<Function<U, V>, Function<Function<T, U>, Function<T, V>>> higherCompose() {
    return (Function<U, V> f) -> (Function<T, U> g) -> (T x) -> f.apply(g.apply(x));
  }

}
