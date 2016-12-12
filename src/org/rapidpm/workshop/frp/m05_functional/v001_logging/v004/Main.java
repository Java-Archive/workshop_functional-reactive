package org.rapidpm.workshop.frp.m05_functional.v001_logging.v004;

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
 * Created by RapidPM - Team on 25.10.16.
 */
public class Main {


  public void log(String msg) {
    System.out.println("msg = " + msg);
  }

  public void logDebug(String msg) {
    System.out.println("msg = " + msg);
  }

  public boolean isDebugEnabled() {
    return true;
  }


  public static final Supplier<String> logMessage = () -> {
    return "Hallo " + "Welt";
  };

  public void log_001(Supplier<String> msg) {
    if (isDebugEnabled())
      System.out.println("msg = " + msg.get());
  }


  public static final Supplier<String> logMessageHello = () -> {
    return "Hallo ";
  };
  public static final Supplier<String> logMessageWorld = () -> {
    return "Welt";
  };

  public static final Supplier<String> logMessageHelloWorld = () -> {
    return logMessageHello.get() + logMessageWorld.get();
  };

  public static final Supplier<Supplier<String>> logMessageHelloWorldSupplier
      = () -> () -> logMessageHello.get() + logMessageWorld.get();

  public void log_002(Supplier<Supplier<String>> msg) {
    if (isDebugEnabled())
      System.out.println("msg = " + msg.get());
  }


  public static void main(String[] args) {
    final Main main = new Main();

    main.log("Hallo " + "Welt");
    main.logDebug("Hallo " + "Welt");
    if (main.isDebugEnabled()) {
      main.logDebug("Hallo " + "Welt");
    }
    main.log_001(logMessage);
    main.log_001(() -> "All bad " + "pppffff");

    main.log_002(logMessageHelloWorldSupplier);

    int a = 0;
//   String value = null;
//    if(a == 1) {
//      //a value = hoppel()
//    } else if(a == 2) {
//      //b value = "y"
//    } else if(a == 3) {
//      //c value = "y"
//    } else{}
//
//    final String value =
//        (a == 1) ? hoppel():
//        (a == 2) ? hoppel() :
//        (a == 3) ? hoppel() : alternative();

  }

//  private static String alternative() {
//
//  }
//
//  private static String hoppel() {
//
//  }


}
