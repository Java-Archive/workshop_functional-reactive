package org.rapidpm.workshop.frp.m05_functional.v002_memoizing;

import java.util.function.BiFunction;
import java.util.function.Function;

import static org.rapidpm.workshop.frp.core.model.Memoizer.memoize;

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
 * Created by RapidPM - Team on 10.12.16.
 */
public class M05V007 {

  private static <T1, T2, R> Function<T1, Function<T2, R>> create(BiFunction<T1, T2, R> biFunction) {
    return memoize(x -> memoize(y -> biFunction.apply(x, y)));
  }

  public static void main(String[] args) {
    final Function<Integer, Function<Integer, Integer>> function = create((x, y) -> x * y);
    System.out.println("memoizationFunction = " + function.apply(2).apply(3));
    System.out.println("memoizationFunction = " + function.apply(2).apply(3));
  }
}
