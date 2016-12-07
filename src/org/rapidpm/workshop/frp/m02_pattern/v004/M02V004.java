package org.rapidpm.workshop.frp.m02_pattern.v004;

import java.util.Stack;

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
public class M02V004 {

  interface Expression {
    int interpret();
  }

  public static abstract class Operator implements Expression {
    protected final Expression leftExpression;
    protected final Expression rightExpression;

    public Operator(final Expression leftExpression, final Expression rightExpression) {
      this.leftExpression = leftExpression;
      this.rightExpression = rightExpression;
    }
  }

  public static class Add extends Operator {
    public Add(final Expression leftExpression, final Expression rightExpression) {
      super(leftExpression, rightExpression);
    }

    @Override
    public int interpret() {
      return leftExpression.interpret() + rightExpression.interpret();
    }
  }

  public static class Subtract extends Operator {

    public Subtract(final Expression leftExpression, final Expression rightExpression) {
      super(leftExpression, rightExpression);
    }

    @Override
    public int interpret() {
      return leftExpression.interpret() - rightExpression.interpret();
    }
  }

  public static class Product extends Operator {

    public Product(final Expression leftExpression, final Expression rightExpression) {
      super(leftExpression, rightExpression);
    }

    @Override
    public int interpret() {
      return leftExpression.interpret() * rightExpression.interpret();
    }
  }

  public static class Number implements Expression {
    private final int n;

    public Number(int n) {
      this.n = n;
    }

    @Override
    public int interpret() {
      return n;
    }
  }

  public static boolean isOperator(String s) {
    return s.equals("+") || s.equals("-") || s.equals("*");
  }

  public static Expression getOperator(String s, Expression left, Expression right) {
    return
        (s.equals("+")) ? new Add(left, right) :
        (s.equals("-")) ? new Subtract(left, right) :
        (s.equals("*")) ? new Product(left, right) :
                          null; // not nice ;-)
  }

  public static int evaluate(String expression) {
    final Stack<Expression> stack = new Stack<>();
    for (final String s : expression.split(" ")) {
      if (isOperator(s)) {
        Expression right = stack.pop();
        Expression left = stack.pop();
        stack.push(getOperator(s, left, right));
      } else {
        Expression i = new Number(Integer.parseInt(s));
        stack.push(i);
      }
    }
    return stack.pop().interpret();
  }

  public static void main(String[] args) {
    String expression = "7 3 - 2 1 + *";
    System.out.println(evaluate(expression));
  }
}
