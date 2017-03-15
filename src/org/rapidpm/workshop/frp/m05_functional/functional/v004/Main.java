package org.rapidpm.workshop.frp.m05_functional.functional.v004;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Stream;

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
 * Created by Sven Ruppert - RapidPM - Team on 14.03.17.
 */
public class Main {


  public static <A, B, R> Function<A, Function<B, R>> currying(BiFunction<A, B, R> biFunc) {
    return a -> b -> biFunc.apply(a, b);
  }

  public static <A, B, R> BiFunction<A, B, R> unCurrying(Function<A, Function<B, R>> func) {
    return (a, b) -> func.apply(a).apply(b);
  }


  public static <A, B, R> Function<B, Function<A, R>> swapArguments(Function<A, Function<B, R>> origFunc) {
    return b -> a -> origFunc.apply(a).apply(b);
  }

  public static <A,R> Function<A, Function<A, R>> reduce(Function<A, Function<A, R>> f) {
    return a -> b -> f.apply(a).apply(b);
  }

  public static void main(String[] args) {

    //swapping argument

    final BiFunction<String, String, String> biFunc = (a, b) -> a + "First - " + b + "Second";
    final Function<String, Function<String, String>> swapArguments = Main.swapArguments(currying(biFunc));

    System.out.println(swapArguments.apply("Hello").apply("World"));

    final BiFunction<String, String, String> biFunction = unCurrying(swapArguments);
    System.out.println(biFunction.apply("Hello", "World"));






    //sum -> reduce // mult -> reduce

    System.out.println(Stream.of(2, 2, 3).reduce((a, b) -> a + b).orElse(-1));
    System.out.println(Stream.of(2, 2, 3).reduce((a, b) -> a * b).orElse(-1));
    System.out.println(Stream.of(2, 2, 3).reduce((a, b) -> a - b).orElse(-1));

    final BinaryOperator<Integer> add = (a, b) -> a + b;
    final BinaryOperator<Integer> mult = (a, b) -> a * b;
    final BinaryOperator<Integer> sub = (a, b) -> a - b;

    System.out.println(Stream.of(2, 2, 3).reduce(add).orElse(-1));
    System.out.println(Stream.of(2, 2, 3).reduce(mult).orElse(-1));
    System.out.println(Stream.of(2, 2, 3).reduce(sub).orElse(-1));

//    Function<Integer, Function<Integer, Integer>> reduceFunc = a -> b ->


    final BiFunction<Integer, Integer, Integer> reduceAdd = (a, b) -> a + b;
    System.out.println(reduce(currying(reduceAdd)).apply(2).apply(2));
  }


}
