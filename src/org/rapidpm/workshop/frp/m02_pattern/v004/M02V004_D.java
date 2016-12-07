package org.rapidpm.workshop.frp.m02_pattern.v004;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.IntBinaryOperator;
import java.util.stream.StreamSupport;

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
public class M02V004_D {

  private static final BiFunction<Stack<Integer>, IntBinaryOperator, Integer> EXECUTE = (stack, operator) -> {
    int right = stack.pop();
    int left = stack.pop();
    return operator.applyAsInt(left, right);
  };

  private static final Map<String, Optional<IntBinaryOperator>> OPERATOR_MAP = new HashMap<>();

  static {
    OPERATOR_MAP.put("+", Optional.of((a, b) -> a + b));
    OPERATOR_MAP.put("*", Optional.of((a, b) -> a * b));
    OPERATOR_MAP.put("-", Optional.of((a, b) -> a - b));
  }

  public static int evaluate(List<String> elements) {
    final Stack<Integer> stack = new Stack<>();

    elements
        .forEach(element -> {
          final Optional<IntBinaryOperator> op = OPERATOR_MAP.getOrDefault(element, Optional.empty());
          op.ifPresentOrElse(
              operator -> stack.push(EXECUTE.apply(stack, operator)),
              ()       -> stack.push(Integer.parseInt(element)));
        });
    return stack.pop();
  }

  public static void main( String[] args ) {
    final String expression = "7 3 - 2 1 + *";
    final List<String> elements = Arrays.asList(expression.split(" "));
    System.out.println( evaluate( elements ) );
  }

}
