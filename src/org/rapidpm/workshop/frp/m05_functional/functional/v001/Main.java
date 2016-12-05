package org.rapidpm.workshop.frp.m05_functional.functional.v001;

import java.util.Arrays;
import java.util.List;

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

    names
        .stream()
        .filter(s -> s.contains("i"))
        .forEach(out::println);

    out.println("names = " + names);

    names
        .stream()
        .filter(s -> s.contains("g"))
        .forEach(out::println);



  }
}
