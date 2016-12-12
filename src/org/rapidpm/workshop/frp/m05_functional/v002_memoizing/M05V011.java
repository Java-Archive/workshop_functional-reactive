package org.rapidpm.workshop.frp.m05_functional.v002_memoizing;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

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
public class M05V011 {

  @FunctionalInterface
  public interface TriFunction<T1, T2,T3, R> {
    R apply(T1 t1, T2 t2, T3 t3);

    default <V> TriFunction<T1, T2,T3, V> andThen(Function<? super R, ? extends V> after) {
      Objects.requireNonNull(after);
      return (T1 t1, T2 t2, T3 t3) -> after.apply(apply(t1, t2, t3));
    }
  }

  public static <T1, T2,T3, R> TriFunction<T1, T2,T3, R> memoize(final TriFunction<T1, T2,T3, R> threeFunc) {
    final TriFunction<T1, T2,T3, Supplier<R>> threeFuncSupplier = (x, y, z) -> () -> threeFunc.apply(x, y,z);
    final Function<T1, Function<T2, Function<T3, R>>> transformed
        = Memoizer.memoize(
            x -> Memoizer.memoize(
                y -> Memoizer.memoize(
                    z -> threeFuncSupplier.apply(x, y,z).get())));
    return (x, y, z) -> transformed.apply(x).apply(y).apply(z);
  }

  public static void main(String[] args) {
    final TriFunction<Integer, Integer, Integer, Integer> function = memoize((x, y, z) -> {
      System.out.println("execute x/y/z = " + x + " / " + y + " / " + z);
      return x * y * z;
    });

    System.out.println("memoizationFunction = " + function.apply(2,3,-1));
    System.out.println("memoizationFunction = " + function.apply(2,3,-1));
  }


}
