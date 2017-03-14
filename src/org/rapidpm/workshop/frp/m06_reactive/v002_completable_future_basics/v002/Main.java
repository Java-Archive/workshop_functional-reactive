package org.rapidpm.workshop.frp.m06_reactive.v002_completable_future_basics.v002;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
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
 * Created by Sven Ruppert - RapidPM - Team on 08.02.17.
 */
public class Main {

  public static void main(String[] args) {
    final CompletableFuture<Long> c = CompletableFuture.supplyAsync(System::nanoTime);

    final Function<Long, String> fA = (value) -> value + " step01";
    final Function<String, String> fB = (value) -> value + " step02";
    final Function<String, String> fC = (value) -> value + " step03";

//    final CompletableFuture<Void> f = c
//        .thenApplyAsync(fA)
//        .thenCombineAsync(CompletableFuture.supplyAsync(fB), new BiFunction<String, String, String>() {
//          public String apply(final String s, final String u) {
//            return null;
//          }
//        })
//        .thenAcceptAsync(System.out::println);
//
//    f.join();


  }
}
