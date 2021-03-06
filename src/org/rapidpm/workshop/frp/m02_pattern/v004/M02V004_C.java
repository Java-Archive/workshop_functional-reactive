package org.rapidpm.workshop.frp.m02_pattern.v004;

import java.util.*;
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
public class M02V004_C {
  private static final Map<String, Optional<IntBinaryOperator>> OPERATOR_MAP = new HashMap<>();

  static {
    OPERATOR_MAP.put("+", Optional.of((a, b) -> a + b));
    OPERATOR_MAP.put("*", Optional.of((a, b) -> a * b));
    OPERATOR_MAP.put("-", Optional.of((a, b) -> a - b));
  }

  public static int evaluate(String expression) {
    Stack<Integer> stack = new Stack<>();

    final String[] split = expression.split(" ");
    StreamSupport
        .<String>stream(Spliterators.spliterator(split, Spliterator.ORDERED), false)
        .forEach(s -> {
          final Optional<IntBinaryOperator> op = OPERATOR_MAP.getOrDefault(s, Optional.empty());
          op.ifPresentOrElse(
              intBinaryOperator -> {
                int right = stack.pop();
                int left = stack.pop();
                final int item = intBinaryOperator.applyAsInt(left, right);
                stack.push(item);
              },
              () -> stack.push(Integer.parseInt(s)));
        });
    return stack.pop();
  }

  public static void main( String[] args ) {
    String expression = "7 3 - 2 1 + *";
    System.out.println( evaluate( expression ) );
  }


}
