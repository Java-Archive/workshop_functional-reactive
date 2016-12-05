package org.rapidpm.workshop.frp.m06_reactive.v001_observable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

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
public class M06V001 {

  public static class Observable<KEY, VALUE> {

    private final Map<KEY, Consumer<VALUE>> listeners = new ConcurrentHashMap<>();

    public void register(KEY key, Consumer<VALUE> listener) {
      listeners.put(key, listener);
    }

    public void unregister(KEY key) {
      listeners.remove(key);
    }

    public void sendEvent(VALUE event) {
      listeners.values().forEach(listener -> listener.accept(event));
    }

  }

  public static void main(String[] args) {
    final Observable<String, String> observable = new Observable<>();
    observable.register("key1", System.out::println);
    observable.register("key2", System.out::println);

    observable.sendEvent("Hello World!");

    observable.unregister("key1");
    observable.sendEvent("Hello World!");
  }
}
