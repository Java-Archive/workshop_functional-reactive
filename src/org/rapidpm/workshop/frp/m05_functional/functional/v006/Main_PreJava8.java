package org.rapidpm.workshop.frp.m05_functional.functional.v006;

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
public class Main_PreJava8 {


  public static void main(String[] args) {

    validate("HelloWorld");


  }

  public static void validate(String input) {

    if (input == null) System.out.println("input should not null");
    else if(input.isEmpty()) System.out.println("input should not empty");
    else System.out.println(input); // consume the value

  }

}
