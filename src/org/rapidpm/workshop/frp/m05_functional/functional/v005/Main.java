package org.rapidpm.workshop.frp.m05_functional.functional.v005;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Stream;

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
 * Created by RapidPM - Team on 27.10.16.
 */
public class Main {

  public static final ExecutorService threadPool = Executors.newFixedThreadPool(5);

  public interface OptionalFunction<T, R> extends Function<T, Optional<R>> {
    default Optional<R> apply(T t) {
      try {
        final R result = workOn(t);
        return Optional.of(result);
      } catch (Exception e1) {
        //e1.printStackTrace();
        // start async Exceptionhandling
        CompletableFuture.runAsync(() -> {
          System.out.println("e1 = " + e1);
          System.out.println("t = " + t);
        }, threadPool);
        return Optional.empty();
      }
    }
    R workOn(T t);
  }

  public static void main(String[] args) {

    final OptionalFunction<String, Integer> parseInt = Integer::parseInt;

    Stream
        .of("1", "2", "3", "A")
        .map(parseInt)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .forEach(System.out::println);

  }

}
