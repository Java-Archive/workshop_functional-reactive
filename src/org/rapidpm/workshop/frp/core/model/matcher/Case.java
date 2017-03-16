package org.rapidpm.workshop.frp.core.model.matcher;

import org.rapidpm.workshop.frp.core.model.Result;
import org.rapidpm.workshop.frp.core.model.Tuple;

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
public class Case<T> extends Tuple<Supplier<Boolean>, Supplier<Result<T>>> {

  public Case(final Supplier<Boolean> booleanSupplier, final Supplier<Result<T>> resultSupplier) {
    super(booleanSupplier, resultSupplier);
  }

  public static <T> Case<T> matchCase(Supplier<Boolean> condition,
                                      Supplier<Result<T>> value) {
    return new Case<>(condition, value);
  }

  public static <T> DefaultCase<T> matchCase(Supplier<Result<T>> value) {
    return new DefaultCase<>(() -> true, value);
  }

  @SafeVarargs
  public static <T> Result<T> match(DefaultCase<T> defaultCase, Case<T>... matchers) {

    for (final Case<T> matcher : matchers) {
      if (matcher.getT1().get()) return matcher.getT2().get();
    }
    return defaultCase.getT2().get();
  }

  public static class DefaultCase<T> extends Case<T> {
    public DefaultCase(final Supplier<Boolean> booleanSupplier, final Supplier<Result<T>> resultSupplier) {
      super(booleanSupplier, resultSupplier);
    }

  }

}
