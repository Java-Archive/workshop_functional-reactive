package org.rapidpm.workshop.frp.m05_functional.functional.v001;

import java.util.function.Function;

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
 * Created by Sven Ruppert - RapidPM - Team on 08.02.17.
 */
public class Main {

  static final Function<Integer, Integer> func = (a) -> a + 1;

  public static void main(String[] args) {
    System.out.println(func.apply(1));

    //first +10 than func
    //not working System.out.println(func.compose(i -> i + 10).apply(1));
    final Function<Integer, Integer> functionPlusTen = integer -> integer + 10;
    System.out.println(func.compose(functionPlusTen).apply(1));

    // first func than -10
    System.out.println(func.andThen(i -> i - 10).apply(1));



  }


}
