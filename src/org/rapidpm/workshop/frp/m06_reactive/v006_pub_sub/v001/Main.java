package org.rapidpm.workshop.frp.m06_reactive.v006_pub_sub.v001;

import java.util.concurrent.*;
import java.util.stream.IntStream;

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
 * Created by Sven Ruppert - RapidPM - Team on 01.03.17.
 */
public class Main {
  public static void main(String[] args) {

    try (final SubmissionPublisher<String> pub = new SubmissionPublisher<>()) {
      CompletableFuture consume = pub.consume(System.out::println);
      IntStream.range(1, 10)
          .mapToObj(String::valueOf)
          .forEach(pub::submit);
      consume.get(10, TimeUnit.SECONDS);
    } catch (InterruptedException | ExecutionException | TimeoutException e) {
      e.printStackTrace();
    }
  }
}
