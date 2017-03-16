package org.rapidpm.workshop.frp.m05_functional.functional.v006;

import org.rapidpm.workshop.frp.core.model.Tuple;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

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
 * Created by Sven Ruppert - RapidPM - Team on 16.03.17.
 */
public class Main002 {


  static final Consumer<String> success = System.out::println;
  static final Consumer<String> failure = x -> System.out.println("ERROR : " + x);

  public static void main(String[] args) {
    validateFunction.apply("HelloWorld").bind(success, failure);
    validateFunction.apply("").bind(success, failure);
//    validateFunction.apply(null).bind(success, failure); // null not allowed as Function input inside JDK

    System.out.println("Done");
  }

  public static Integer workOn(Integer x) {
    return (x > 0) ? x : -1 * x;
  }

  public static Integer workOnWithIf(Integer x) {
    if (x > 0) return x;
    else return -1 * x;
  }

  public static Function<Integer, Integer> workOnFnc = (x) -> (x > 0) ? x : -1 * x;

  public static Function<String, Result<String>> validateFunction = (input) ->
      Case.match(
          Case.matchCase(() -> Result.success(input)), // Default Case
          Case.matchCase(() -> input == null, () -> Result.failure("input should not null")),
          Case.matchCase(() -> input.isEmpty(), () -> Result.failure("input should not empty"))
      );

//  public static Function<String, Main001.Result<String>> validateFunction = (input) ->
//      (input == null) ? Main001.Result.failure("input should not null") :
//          (input.isEmpty()) ? Main001.Result.failure("input should not empty") :
//              Main001.Result.success(input);

  interface Result<T> {

    void bind(Consumer<T> success, Consumer<String> failure);

    static <T> Result<T> failure(String errorMessage) {
      return new Result.Failure<>(errorMessage);
    }

    static <T> Result<T> success(T value) {
      return new Result.Success<>(value);
    }

    class Success<T> implements Result<T> {

      private final T value;

      public Success(final T value) {
        this.value = value;
      }

      @Override
      public void bind(final Consumer<T> success, final Consumer<String> failure) {
        success.accept(value);
      }
    }

    class Failure<T> implements Result<T> {

      private final String errorMessage;

      public Failure(final String errorMessage) {
        this.errorMessage = errorMessage;
      }

      @Override
      public void bind(final Consumer<T> success, final Consumer<String> failure) {
        failure.accept(errorMessage);
      }
    }
  }

  public static class Case<T> extends Tuple<Supplier<Boolean>, Supplier<Result<T>>> {

    public Case(final Supplier<Boolean> booleanSupplier, final Supplier<Result<T>> resultSupplier) {
      super(booleanSupplier, resultSupplier);
    }

    public static <T> Case<T> matchCase(Supplier<Boolean> condition,
                                        Supplier<Result<T>> value) {
      return new Case<>(condition, value);
    }

    public static <T> DefaultCase<T> matchCase(Supplier<Result<T>> value) {
      return new DefaultCase<T>(() -> true, value);
    }

    @SafeVarargs
    public static <T> Result<T> match(DefaultCase<T> defaultCase, Case<T>... matchers) {

      for (final Case<T> matcher : matchers) {
        if (matcher.getT1().get()) return matcher.getT2().get();
      }
      return defaultCase.getT2().get();
    }

  }

  public static class DefaultCase<T> extends Case<T> {
    public DefaultCase(final Supplier<Boolean> booleanSupplier, final Supplier<Result<T>> resultSupplier) {
      super(booleanSupplier, resultSupplier);
    }

  }

}
