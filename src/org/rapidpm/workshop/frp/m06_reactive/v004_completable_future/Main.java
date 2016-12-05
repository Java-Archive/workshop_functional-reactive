package org.rapidpm.workshop.frp.m06_reactive.v004_completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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
 * Created by RapidPM - Team on 25.10.16.
 */
public class Main {


  public static void main(String[] args) {

    //Arrays.parallelSort();


    final CompletableFuture<Void> voidCF = CompletableFuture.runAsync(()-> System.out.println("Hoppel"));
    try {
      final Void aVoid = voidCF.get();
      final boolean cancel = voidCF.cancel(true);

      final CompletableFuture<Void> voidCompletableFuture = voidCF
          .whenComplete((aVoid1, throwable) -> System.out.println("aVoid = " + aVoid1));

    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }


    final CompletableFuture<String> stringCF = CompletableFuture.supplyAsync(() -> "juhu");

//    final String s = stringCF.get();
    final String s1 = stringCF.getNow("blöööööd");

    final int numberOfDependents = stringCF.getNumberOfDependents();
//    stringCF.


  }




}
