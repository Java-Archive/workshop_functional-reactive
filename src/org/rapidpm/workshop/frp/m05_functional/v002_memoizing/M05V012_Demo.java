package org.rapidpm.workshop.frp.m05_functional.v002_memoizing;

import org.rapidpm.workshop.frp.core.model.Memoizer;

import java.util.Objects;
import java.util.function.BiFunction;

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
 * Created by Sven Ruppert - RapidPM - Team on 07.02.17.
 */
public class M05V012_Demo {

  public static void main(String[] args) {

    final MyLegacyClass myLegacyClass = new MyLegacyClass();
    final BiFunction<Integer, Integer, Value> doWork = myLegacyClass::doWorkA;
    final BiFunction<Integer, Integer, Value> memoize = Memoizer.memoize(doWork);

    print(memoize);
    print(Memoizer.memoize(myLegacyClass::doWorkA));
    print(myLegacyClass::doWorkB);

    final Value valueA = myLegacyClass.doWorkA(2, 3);
    final Value valueB = myLegacyClass.doWorkA(2, 3);
    System.out.println("orig memoizing On = " + valueA.equals(valueB));

  }

  private static void print(final BiFunction<Integer, Integer, Value> biFunction) {
    final Value applyA = biFunction.apply(2, 3);
    final Value applyB = biFunction.apply(2, 3);
    System.out.println("memoizing On  = " + applyA.equals(applyB));
  }

  public static class Value {
    private long timestamp = System.nanoTime();

    @Override
    public String toString() {
      return "Value{" +
          "timestamp=" + timestamp +
          '}';
    }

    @Override
    public boolean equals(final Object o) {
      if (this == o) return true;
      if (!(o instanceof Value)) return false;
      final Value value = (Value) o;
      return timestamp == value.timestamp;
    }

    @Override
    public int hashCode() {
      return Objects.hash(timestamp);
    }
  }

  public static class MyLegacyClass {
    public Value doWorkA(int a, int b){
      return new Value();
    }
    public Value doWorkB(int a, int b){
      return new Value();
    }
  }

}
