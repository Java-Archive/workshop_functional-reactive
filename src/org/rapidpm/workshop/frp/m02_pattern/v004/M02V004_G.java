package org.rapidpm.workshop.frp.m02_pattern.v004;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;

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
 * Created by RapidPM - Team on 07.12.16.
 */
public class M02V004_G {

  private static final Function<String, Optional<IntBinaryOperator>> str2OpFNC = (str) ->
         (Objects.isNull(str)) ? Optional.empty() :
         (str.equals("+"))     ? Optional.of((a, b) -> a + b) :
         (str.equals("*"))     ? Optional.of((a, b) -> a * b) :
         (str.equals("-"))     ? Optional.of((a, b) -> a - b) :
                                 Optional.empty();


  private static final BiFunction<Tuple<Stack<Integer>, String>,
                                  Tuple<Stack<Integer>, String>,
                                  Tuple<Stack<Integer>, String>> calculateFNC = (left, right) -> {

    final Optional<IntBinaryOperator> operator = str2OpFNC.apply(right.second);

    if (operator.isPresent()) {
      final Integer r = left.first.pop();
      final Integer l = left.first.pop();
      final int resultTmp = operator.get().applyAsInt(l,r);
      final Stack<Integer> newStack = new Stack<>();
      newStack.addAll(left.first);
      newStack.add(resultTmp);
      final Tuple<Stack<Integer>, String> result = new Tuple<>(newStack, null);
      return result;
    } else {
      final Stack<Integer> newStack = new Stack<>();
      newStack.addAll(left.first);
      newStack.addAll(right.first);
      if (left.second != null) newStack.add(Integer.parseInt(left.second)) ;
      newStack.add(Integer.parseInt(right.second));
      final Tuple<Stack<Integer>, String> result = new Tuple<>(newStack, null);
      return result;
    }
  };


    public static int evaluate(final List<String> elements) {
    final Tuple<Stack<Integer>, String> result = elements
        .stream()
        .map(e -> new Tuple<Stack<Integer>, String>(new Stack<>(), e))
        .reduce((left, right) -> calculateFNC.apply(left, right))
        .get();
    System.out.println("result = " + result);
    return result.first.pop();
  }

  public static void main(String[] args) {
    final String expression = "7 3 - 2 1 + *";
    final List<String> elements = Arrays.asList(expression.split(" "));
    System.out.println(evaluate(elements));
  }


  private static class Tuple<A, B> {
    private A first;
    private B second;

    public Tuple(final A first, final B second) {
      this.first = first;
      this.second = second;
    }

    public A getFirst() {
      return first;
    }

    public B getSecond() {
      return second;
    }

    @Override
    public String toString() {
      return "Triple{" +
          "first=" + first +
          ", second=" + second +
          '}';
    }
  }


}
