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

  public static class DemoClass {
    static {
      System.out.println(" static 001");
    }

    static {
      System.out.println(" static 002");
    }

    {
      System.out.println(" non- static 001");
    }

    {
      System.out.println(" non- static 002");
    }

    public DemoClass() {
      System.out.println(" constructor ");
    }
  }

  public static void main(String[] args) {
    new DemoClass();
  }

}
