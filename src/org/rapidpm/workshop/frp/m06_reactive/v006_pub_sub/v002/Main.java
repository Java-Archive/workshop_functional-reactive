package org.rapidpm.workshop.frp.m06_reactive.v006_pub_sub.v002;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.IntStream;

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
 * Created by Sven Ruppert - RapidPM - Team on 01.03.17.
 */
public class Main {

  public static class SubscriberBuilder<T> {

    private Flow.Subscription onSubscribe;
    private Consumer<T> onNext;
    private Consumer<Throwable> onError;
    private Consumer<Void> onComplete;

    public static <R> SubscriberBuilder<R> next() {
      return new SubscriberBuilder<>();
    }

    public SubscriberBuilder<T> withOnSubscribe(final Flow.Subscription subscription) {
      this.onSubscribe = subscription;

      return this;
    }

    public SubscriberBuilder<T> withOnNext(Consumer<T> consumer) {
      onNext = consumer;
      return this;
    }

    public SubscriberBuilder<T> withOnError(Consumer<Throwable> consumer) {
      onError = consumer;
      return this;
    }

    public SubscriberBuilder<T> withOnComplete(Consumer<Void> consumer) {
      onComplete = consumer;
      return this;
    }

    public Flow.Subscriber<T> build() {

      return new Flow.Subscriber<T>() {
        private Flow.Subscription subscription;

        @Override
        public void onSubscribe(final Flow.Subscription subscription) {
          this.subscription = onSubscribe;
          this.subscription.request(1);
        }

        @Override
        public void onNext(final T item) {
          this.subscription.request(1);
          onNext.accept(item);
        }

        @Override
        public void onError(final Throwable throwable) {
          onError.accept(throwable);
          this.subscription.cancel();
        }

        @Override
        public void onComplete() {
          onComplete.accept(null);
          this.subscription.cancel();
        }
      };
    }

  }

  public static final BiConsumer<String, String> flushedWriteLn = (str, value) -> {
    System.out.println( str + " = " + value);
    System.out.flush();
  };

  public static void main(String[] args) {

    try (final SubmissionPublisher<String> pub = new SubmissionPublisher<>()) {

      final Flow.Subscriber<String> subscriber = SubscriberBuilder.<String>next()
          .withOnComplete((value) -> flushedWriteLn.accept("OnComplete ", "VOID"))
          .withOnError((value) -> flushedWriteLn.accept("OnError ", value.getMessage()))
          .withOnNext((value) -> flushedWriteLn.accept("OnNext", value))
          .withOnSubscribe(new Flow.Subscription() {
            long counter = 0;
            @Override
            public void request(final long n) {
              flushedWriteLn.accept("OnSubscribe", String.valueOf(n));
              counter = counter + n ;
            }

            @Override
            public void cancel() {
              System.out.println(" cancel ");
            }
          })
          .build();

      pub.subscribe(subscriber);

      //pub.offer()

      IntStream.range(1, 10)
          .mapToObj(String::valueOf)
          .forEach(pub::submit);

      Thread.sleep(5_000);
      pub.close();
    } catch (Exception e) {}


  }

}
