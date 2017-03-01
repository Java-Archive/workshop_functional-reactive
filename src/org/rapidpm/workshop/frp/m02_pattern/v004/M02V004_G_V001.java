package org.rapidpm.workshop.frp.m02_pattern.v004;

import java.util.*;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;

import static java.util.Objects.*;
import static java.util.Optional.empty;
import static java.util.Optional.of;

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
public class M02V004_G_V001 {
  private static final Function<String, Optional<IntBinaryOperator>> str2OpFNC = (str) ->
      (isNull(str))     ? empty() :
      (str.equals("+")) ? of((a, b) -> a + b) :
      (str.equals("*")) ? of((a, b) -> a * b) :
      (str.equals("-")) ? of((a, b) -> a - b) :
                          empty();

  private static final Function<String, Integer> evaluate = (expression) -> {
    final Stack<Integer> stack = new Stack<>();
    for (final String s : expression.split(" ")) {
      str2OpFNC.apply(s)
          .ifPresentOrElse(op -> {
                int right = stack.pop(); // not nice - state outside !?!
                int left = stack.pop();
                stack.push(op.applyAsInt(left, right));
              },
              () -> stack.push(Integer.parseInt(s))
          );
    }
    return stack.pop();
  };


  public static void main(String[] args) {
    System.out.println(evaluate.apply("7 3 - 2 1 + *"));
  }


}
