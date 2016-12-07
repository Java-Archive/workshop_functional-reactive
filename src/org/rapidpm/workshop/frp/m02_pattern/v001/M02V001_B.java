package org.rapidpm.workshop.frp.m02_pattern.v001;

import java.util.Optional;

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
public class M02V001_B {

  public interface Service {
    String doWork(String value);
  }

  public static class DefaultService implements Service {
    public DefaultService() {
      System.out.println(DefaultService.class.getSimpleName() + " created");
    }

    @Override
    public String doWork(String value) {
      return Optional.ofNullable(value)
          .orElse("")
          .toUpperCase();
    }
  }


  @FunctionalInterface
  public interface Factory<T> {
    T createInstance();
  }


  public static class ServiceFactory implements Factory<Service> {
    @Override
    public Service createInstance() {
      return new DefaultService();
    }
  }

  @FunctionalInterface
  public interface Strategy<T> {
    T realSubject(Factory<T> factory);

  }

  public static class StrategyNotThreadSafe<T> implements Strategy<T>{
    private T delegator;
    @Override
    public T realSubject(final Factory<T> factory) {
      System.out.println("factory = " + factory.getClass().getSimpleName());
      if(delegator == null) {
        System.out.println("StrategyNotThreadSafe - create Delegator ");
        delegator = factory.createInstance();
      }
      return delegator;
    }
  }

  public static class StrategyMethodScoped<T> implements Strategy<T>{
    @Override
    public T realSubject(final Factory<T> factory) {
      System.out.println("StrategyMethodScoped - create Delegator ");
      return factory.createInstance();
    }
  }


  // combine
  public static class VirtualProxy implements Service {

    private Factory<Service> factory;
    private Strategy<Service> strategy;

    public VirtualProxy(final Factory<Service> factory, final Strategy<Service> strategy) {
      this.factory = factory;
      this.strategy = strategy;
    }

    @Override
    public String doWork(final String value) {
      return strategy.realSubject(factory).doWork(value);
    }
  }

  public static void main(String[] args) {

    final Service virtualproxy = new VirtualProxy(
        new ServiceFactory(),
        new StrategyNotThreadSafe<>());

    virtualproxy.doWork("Hello");

  }





}
