package org.rapidpm.workshop.frp.m00_basics.v000;

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
 * Created by RapidPM - Team on 05.12.16.
 */
public class Main001 {

  public static final MySubClass MY_SUB_CLASS_STATIC_001 = new MySubClass();
  public final MySubClass mySubClass = new MySubClass();

  static {
    System.out.println("static 01");
  }

  public static final MySubClass MY_SUB_CLASS_STATIC_002 = new MySubClass();

  static {
    System.out.println("static 02");
  }

  {
    System.out.println("non- static 01");

  }
  {
    System.out.println("non- static 02");

  }

  public static void main(String[] args) {

    final MySubClass mySubClass = new MySubClass();
    final Main001 myClass = new Main001();
  }

  public static class MySubClass {
    public MySubClass() {
      System.out.println(" MySubClass ");
    }
  }
}
