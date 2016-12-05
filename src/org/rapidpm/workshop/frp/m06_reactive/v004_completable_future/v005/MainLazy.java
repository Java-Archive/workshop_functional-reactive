package org.rapidpm.workshop.frp.m06_reactive.v004_completable_future.v005;



import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiFunction;

import static java.util.concurrent.CompletableFuture.supplyAsync;

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
public class MainLazy {

  //
  public static int availableProcessors() {
    return Runtime.getRuntime().availableProcessors();
  }

  public static final ExecutorService executorServiceCPU = Executors.newWorkStealingPool(availableProcessors());
  public static final ExecutorService executorServiceLocalDisk = Executors.newWorkStealingPool(availableProcessors() * 10);
  public static final ExecutorService executorServiceNetworkRest = Executors.newWorkStealingPool(availableProcessors() * 20);


  // Operator
  private static BiFunction<StepC, Result, Result> stepCResultResultBiFunction = (stepC, result) -> {
    final Result resultC = new Result();
    resultC.message = stepC.message + " - " + result.message + " merged AB c";
    System.out.println("result = " + resultC);
    return resultC;
  };

  public static BiFunction<StepA, StepB, Result> stepAStepBResultBiFunction = (stepA, stepB) -> {
    final Result result = new Result();
    result.message = stepA.message + " - " + stepB.message + " merged AB";
    System.out.println("result = " + result);
    return result;
  };

  public static void main(String[] args) {

    supplyAsync(MainLazy::getStepCSupplier, executorServiceNetworkRest)
        .thenCombineAsync(supplyAsync(MainLazy::getStepASupplier, executorServiceCPU)
                .thenCombineAsync(supplyAsync(MainLazy::getStepBSupplier, executorServiceLocalDisk),
                    stepAStepBResultBiFunction),
            stepCResultResultBiFunction)
        .thenAccept(System.out::println);

  }


  private static StepC getStepCSupplier() {
    // do some stuff
    final StepC step = new StepC();
    step.message = "Step C done";
    System.out.println("step = " + step);
    return step;
  }

  private static StepB getStepBSupplier() {
    // do some stuff
    final StepB step = new StepB();
    step.message = "Step B done";
    System.out.println("step = " + step);
    return step;
  }

  public static StepA getStepASupplier() {
    // do some stuff
    final StepA step = new StepA();
    step.message = "Step A done";
    System.out.println("step = " + step);
    return step;
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
