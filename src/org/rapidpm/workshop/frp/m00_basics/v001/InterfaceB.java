package org.rapidpm.workshop.frp.m00_basics.v001;

/**
 * Created by Sven Ruppert on 06.08.2014.
 */
public interface InterfaceB extends InterfaceA {

  default void doStdMethod(){
    System.out.println("doStdMethod - InterfaceB");
  }

}
