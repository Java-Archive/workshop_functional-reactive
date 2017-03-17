package org.rapidpm.workshop.frp.m05_functional.functional.v007;

import java.util.Arrays;
import java.util.List;

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
 * Created by Sven Ruppert - RapidPM - Team on 17.03.17.
 */
public class Main001 {

  //idea of ValueTypes
  public static void main(String[] args) {

    final Car car01 = new Car(new Price(10_000), new Weight(5_000), new Colour("colour01"));

    final List<Car> cars = Arrays.asList(
        new Car(new Price(10_000), new Weight(5_000), new Colour("colour01")),
            new Car(new Price(5_000), new Weight(5_000), new Colour("colour02"))
            );

  }

//  private static Car createCar(int price, int weight, String colour){
//    final Car car = new Car();
//    car.setPrice(price);
//    car.setWeight(weight);
//    car.setColour(colour);
//    return car;
//  }

  public static class Price {
    private final int amount;
//    private final enum currency;

    public Price(final int amount) {
      this.amount = amount;
    }

    public int getAmount() {
      return amount;
    }
  }

  public static class Weight {
    private final int amount;
//    private final enum unit;

    public Weight(final int amount) {
      this.amount = amount;
    }

    public int getAmount() {
      return amount;
    }
  }

  public static class Colour {
    private final String name;

//    private final enum unit;

    public Colour(final String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }
  }


  public static class Car {

    public Car(final Price price, final Weight weight, final Colour colour) {
      this.price = price;
      this.weight = weight;
      this.colour = colour;
    }

    private final Price price;
    private final Weight weight;
    private final Colour colour;

    public Price getPrice() {
      return price;
    }

    public Weight getWeight() {
      return weight;
    }

    public Colour getColour() {
      return colour;
    }
  }

}
