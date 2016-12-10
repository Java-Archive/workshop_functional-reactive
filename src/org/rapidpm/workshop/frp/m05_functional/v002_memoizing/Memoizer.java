package org.rapidpm.workshop.frp.m05_functional.v002_memoizing;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
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


public class Memoizer<T, U> {
  private final Map<T, U> memoizationCache = new ConcurrentHashMap<>();

  private Function<T, U> doMemoize(final Function<T, U> function) {
    return input -> memoizationCache.computeIfAbsent(input, function);
  }

  public static <T, U> Function<T, U> memoize(final Function<T, U> function) {
    return new Memoizer<T, U>().doMemoize(function);
  }

  public static <T1, T2, R> BiFunction<T1, T2, R> memoize(final BiFunction<T1, T2, R> biFunc) {
    final BiFunction<T1, T2, Supplier<R>> biFuncSupplier = (x, y) -> () -> biFunc.apply(x, y);
    final Function<T1, Function<T2, R>> transformed = Memoizer.memoize(x -> Memoizer.memoize(y -> biFuncSupplier.apply(x, y).get()));
    return (x, y) -> transformed.apply(x).apply(y);
  }
}
