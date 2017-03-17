package org.rapidpm.workshop.frp.m05_functional.functional.v008;

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
 * Created by Sven Ruppert - RapidPM - Team on 17.03.17.
 */
public class Main002 {

  public static void main(String[] args) {
    final Integer add = add(1_000_000, 1_000_000);
    System.out.println("add = " + add);
  }

  public static Integer add(int x, int y) {
    return addRecursiveTailCall(x,y).eval();
  }

  // Recursive Version with tail call elimination
  public static TailCall<Integer> addRecursiveTailCall(int x, int y) {
    return y == 0 ?
        new TailCall.Return<>(x) :
        new TailCall.Suspend<>(() -> addRecursiveTailCall(x + 1, y - 1));
  }

  public static abstract class TailCall<T> {

    public abstract TailCall<T> resume();

    public abstract T eval();

    public abstract boolean isSuspend();

    private static class Return<T> extends TailCall<T> {
      private final T value;

      private Return(final T value) {
        this.value = value;
      }

      @Override
      public TailCall<T> resume() {
        throw new RuntimeException("do not use this ;-)");
      }

      @Override
      public T eval() {
        return value;
      }

      @Override
      public boolean isSuspend() {
        return false;
      }
    }


    private static class Suspend<T> extends TailCall<T> {
      private final Supplier<TailCall<T>> resume;

      private Suspend(final Supplier<TailCall<T>> resume) {
        this.resume = resume;
      }

      @Override
      public TailCall<T> resume() {
        return resume.get();
      }

      @Override
      public T eval() {
        TailCall<T> tailCall = this;
        while (tailCall.isSuspend()) {
          tailCall = tailCall.resume();
        }
        return tailCall.eval();

      }

      @Override
      public boolean isSuspend() {
        return true;
      }
    }
  }
}
