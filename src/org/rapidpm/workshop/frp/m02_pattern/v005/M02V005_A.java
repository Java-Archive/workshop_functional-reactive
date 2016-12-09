package org.rapidpm.workshop.frp.m02_pattern.v005;

import java.util.function.Predicate;
import java.util.function.UnaryOperator;

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
 * Created by RapidPM - Team on 07.12.16.
 */
public class M02V005_A {

  private static final UnaryOperator<String> TO_UPPER_CASE = String::toUpperCase;
  private static final UnaryOperator<String> TO_LOWER_CASE = String::toLowerCase;

  public static void publishText(String text,
                                 Predicate<String> filter,
                                 UnaryOperator<String> format) {
    if (filter.test( text )) {
      System.out.println( format.apply( text ) );
    }
  }


  public static void main( String[] args ) {
    publishText( "ERROR - something bad happened", s -> true, TO_UPPER_CASE);
    publishText( "DEBUG - I'm here", s -> s.length() < 20, TO_LOWER_CASE);
  }

}
