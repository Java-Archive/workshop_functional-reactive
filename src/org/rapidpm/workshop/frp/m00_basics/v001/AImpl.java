package org.rapidpm.workshop.frp.m00_basics.v001;

/**
 * Created by Sven Ruppert on 06.08.2014.
 */
public class AImpl implements InterfaceA {
  @Override
  public void doSomethingA() {
    System.out.println("doSomethingA - AImpl");
  }

  @Override
  public void doSomethingB() {
    System.out.println("doSomethingB - AImpl");
  }

}
