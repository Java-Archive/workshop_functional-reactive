package org.rapidpm.workshop.frp.m02_pattern.v001;

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
public class M02V001_D {

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

// remember
//  public static final Supplier<Service> factoryDefaulService = DefaultService::new;
//  public static final Function<Supplier<Service>,Service> methodScoped = Supplier::get;


  public static abstract class ProxyBuilder<T> {

    protected Supplier<T> serviceSupplier;
    protected Function<Supplier<T>, T> serviceFunction;

    public ProxyBuilder<T> withSupplier(final Supplier<T> serviceSupplier) {
      this.serviceSupplier = serviceSupplier;
      return this;
    }

    public ProxyBuilder<T> withFunction(final Function<Supplier<T>, T> serviceFunction) {
      this.serviceFunction = serviceFunction;
      return this;
    }

    public abstract T build();

  }

  public static class ServiceProxyBuilder extends ProxyBuilder<Service> {
    @Override
    public Service build() {
//      return new Service() {
//        @Override
//        public String doWork(final String value) {
//          return serviceFunction.apply(serviceSupplier).doWork(value);
//        }
//      };
      return value -> serviceFunction.apply(serviceSupplier).doWork(value);
    }
  }


  public static void main(String[] args) {

    //method Scoped
    final Service service = new ServiceProxyBuilder()
        .withSupplier(DefaultService::new)
        .withFunction(Supplier::get)
        .build();

    service.doWork("Hello");
    service.doWork("Hello");
    service.doWork("Hello");


  }


}
