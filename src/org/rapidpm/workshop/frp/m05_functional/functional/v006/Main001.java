package org.rapidpm.workshop.frp.m05_functional.functional.v006;

import java.util.function.Consumer;
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
 * Created by Sven Ruppert - RapidPM - Team on 15.03.17.
 */
public class Main001 {


  public static void main(String[] args) {
    validateFunction.apply("Hello World").bind(success, failure);
    validateFunction.apply("").bind(success, failure);
    validateFunction.apply(null).bind(success, failure);
  }


  interface Result<T> {

    void bind(Consumer<T> success, Consumer<String> failure);

    static <T> Result<T> failure(String errorMessage) {
      return new Failure<>(errorMessage);
    }

    static <T> Result<T> success(T value) {
      return new Success<>(value);
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

      private String errorMessage;

      public Failure(final String errorMessage) {
        this.errorMessage = errorMessage;
      }

      @Override
      public void bind(final Consumer<T> success, final Consumer<String> failure) {
        failure.accept(errorMessage);
      }
    }
  }





  public static Function<String, Result<String>> validateFunction = (input) ->
      (input == null) ? Result.failure("input should not null") :
          (input.isEmpty()) ? Result.failure("input should not empty") :
              Result.success(input);


  static final Consumer<String> success = System.out::println;
  static final Consumer<String> failure = x -> System.out.println( "ERROR : " + x);

}
