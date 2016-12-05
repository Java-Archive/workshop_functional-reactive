package org.rapidpm.workshop.frp.m05_functional.functional.v002;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static java.lang.System.out;

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
 * Created by RapidPM - Team on 18.10.16.
 */
public class Main {

  public static void main(String[] args) {

    final List<String> names = Arrays.asList(
        "Hugo",
        "Willy",
        "Simon",
        "Erwin",
        "Sigfried"
    );

    final Consumer<String> println = out::println;

    final String v = "i";

    final Predicate<String> predicateI = s -> s.contains(v);
    names
        .stream()
        .filter(predicateI)
        .forEach(println);

    out.println("names = " + names);

    final Predicate<String> predicateG = s -> s.contains("g");
    names
        .stream()
        .filter(predicateG)
        .forEach(println);
  }
}
