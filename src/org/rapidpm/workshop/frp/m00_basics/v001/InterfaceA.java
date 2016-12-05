package org.rapidpm.workshop.frp.m00_basics.v001;

/**
 * Created by Sven Ruppert on 06.08.2014.
 */
public interface InterfaceA {

  static void doSomethingStaticA() {
    System.out.println("doSomethingStaticA");
  }

  static void doSomethingStaticB() {
    System.out.println("doSomethingStaticB");
  }

  void doSomethingA();

  void doSomethingB();

  default void doStdMethod() {
    System.out.println("doStdMethod");
  }

}
