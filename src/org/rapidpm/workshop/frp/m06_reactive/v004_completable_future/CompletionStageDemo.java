package org.rapidpm.workshop.frp.m06_reactive.v004_completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

/**
 * Created by ts40 on 05.03.14.
 */
public class CompletionStageDemo {


  public static void main(String[] args) {

    final ExecutorService fixedThreadPool = Executors
        .newFixedThreadPool(
            Runtime.getRuntime().availableProcessors());

    final Supplier<String> task = () -> "das Ergebnis";

    final CompletableFuture<String> future
        = CompletableFuture.supplyAsync(task, fixedThreadPool);

    final CompletableFuture<Void> accept = future
        .thenAccept(System.out::println);

  }
}
