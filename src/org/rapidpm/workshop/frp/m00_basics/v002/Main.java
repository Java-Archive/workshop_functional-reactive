package org.rapidpm.workshop.frp.m00_basics.v002;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 *
 */
public class Main {

  public static class LegacyService {
    public String doWork(String str, Integer integer) {
      return "legacy-" + str + " - " + integer;
    }
  }

  public static void main(String[] args) {

    final LegacyService legacyService = new LegacyService();
    final BiFunction<String, Integer, String> myFunction = legacyService::doWork;

    final Function<String, String> function = input -> "nextFunction - " + input;
    final BiFunction<String, Integer, String> andThen = myFunction.andThen(function);

    String hello = andThen.apply("Hello", 42);
    System.out.println("hello = " + hello);

  }
}

