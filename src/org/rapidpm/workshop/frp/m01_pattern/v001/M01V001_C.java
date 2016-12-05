package org.rapidpm.workshop.frp.m01_pattern.v001;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

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
 * Created by RapidPM - Team on 04.12.16.
 */
public class M01V001_C {

  public interface Service {
    String doWork(String value);
  }

  public static class DefaultService implements Service {
    public DefaultService() {
      System.out.println(DefaultService.class.getSimpleName() + " created");
    }
    @Override
    public String doWork(String value) {
      return Optional.ofNullable(value).orElse("").toUpperCase();
    }
  }


  public static Supplier<Service> factoryDefaulService = DefaultService::new;

  public static class StrategyNotThreadSafe<T> implements Function<Supplier<T>,T> {
    private T delegator;
    @Override
    public T apply(final Supplier<T> supplier) {
      System.out.println("factory = " + supplier.getClass().getSimpleName());
      if(delegator == null) {
        System.out.println("StrategyNotThreadSafe - create Delegator ");
        delegator = supplier.get();
      }
      return delegator;
    }
  }


  public static class StrategyMethodScoped<T> implements Function<Supplier<T>,T> {
    @Override
    public T apply(final Supplier<T> supplier) {
      System.out.println("StrategyMethodScoped - create Delegator ");
      return supplier.get();
    }
  }

  //or shorter
  public static final Function<Supplier<Service>,Service> methodScoped = Supplier::get;

  //  // combine
  public static class VirtualProxy implements Service {

    private Supplier<Service> factory;
    private Function<Supplier<Service>,Service> strategy;

    public VirtualProxy(final Supplier<Service> supplier ,
                        final Function<Supplier<Service>,Service> function) {
      this.factory = supplier;
      this.strategy = function;
    }

    @Override
    public String doWork(final String value) {
      return strategy.apply(factory).doWork(value);
    }
  }

  public static void main(String[] args) {
    final Service virtualproxy = new VirtualProxy(factoryDefaulService,
                                                  new StrategyNotThreadSafe<>());
    virtualproxy.doWork("Hello");
  }
}
