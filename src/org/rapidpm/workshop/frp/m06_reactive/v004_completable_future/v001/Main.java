package org.rapidpm.workshop.frp.m06_reactive.v004_completable_future.v001;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiFunction;
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
 * Created by RapidPM - Team on 17.10.16.
 */
public class Main {


  public static void main(String[] args) {

    final int availableProcessors = Runtime.getRuntime().availableProcessors();
    final ExecutorService executorServiceCPU = Executors.newWorkStealingPool(availableProcessors);


    final CompletableFuture<StepA> stepA = CompletableFuture
        .supplyAsync(new Supplier<StepA>() {
          @Override
          public StepA get() {
            // do some long running stuff
            final StepA step = new StepA();
            step.message = "Step A done";
            System.out.println("step = " + step);
            return step;
          }
        }, executorServiceCPU);


    final CompletableFuture<StepB> stepB = CompletableFuture
        .supplyAsync(new Supplier<StepB>() {
          @Override
          public StepB get() {
            // do some stuff
            final StepB step = new StepB();
            step.message = "Step B done";
            System.out.println("step = " + step);
            return step;
          }
        }, executorServiceCPU);


    final CompletableFuture<StepC> stepC = CompletableFuture
        .supplyAsync(new Supplier<StepC>() {
          @Override
          public StepC get() {
            // do some stuff
            final StepC step = new StepC();
            step.message = "Step C done";
            System.out.println("step = " + step);
            return step;
          }
        }, executorServiceCPU);


    final CompletableFuture<Result> stepAstepBCombined = stepA
        .thenCombineAsync(stepB, new BiFunction<StepA, StepB, Result>() {
          @Override
          public Result apply(final StepA stepA, final StepB stepB) {
            final Result result = new Result();
            result.message = stepA.message + " - " + stepB.message;
            System.out.println("result = " + result);
            return result;
          }
        });


    final CompletableFuture<Result> stepABstepCCombined = stepC
        .thenCombineAsync(stepAstepBCombined, new BiFunction<StepC, Result, Result>() {
          @Override
          public Result apply(final StepC stepC, final Result result) {
            final Result resultC = new Result();
            resultC.message = stepC.message + " - " + result.message;
            System.out.println("result = " + resultC);
            return resultC;
          }
        });


    stepABstepCCombined.thenAccept(System.out::println);

  }


  public static class StepA {

    private String message;

    @Override
    public String toString() {
      return "StepA{" +
          "message='" + message + '\'' +
          '}';
    }
  }

  public static class StepB {

    private String message;

    @Override
    public String toString() {
      return "StepB{" +
          "message='" + message + '\'' +
          '}';
    }
  }

  public static class StepC {

    private String message;

    @Override
    public String toString() {
      return "StepC{" +
          "message='" + message + '\'' +
          '}';
    }
  }


  public static class Result {

    private String message;

    @Override
    public String toString() {
      return "Result{" +
          "message='" + message + '\'' +
          '}';
    }
  }

}
