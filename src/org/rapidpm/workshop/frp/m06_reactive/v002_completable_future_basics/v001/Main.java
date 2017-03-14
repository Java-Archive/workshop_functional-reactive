package org.rapidpm.workshop.frp.m06_reactive.v002_completable_future_basics.v001;

import java.util.concurrent.CompletableFuture;

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

    final CompletableFuture<Void> f = c.thenApplyAsync((value) -> value + " step01")
        .thenApplyAsync((value) -> value + " step02")
        .thenApplyAsync((value) -> value + " step03")
        .thenAcceptAsync(System.out::println);

    f.join();

  }

}
