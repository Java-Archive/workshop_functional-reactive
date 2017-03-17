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
public class MainPreJava8 {


  public static void main(String[] args) {


    final Car car01 = new Car();
    car01.setPrice(10_000);
    car01.setWeight(5_000);
    car01.setColour("colour01");

    final List cars = Arrays.asList(
        createCar(10_000, 5_000, "colour01"),
        createCar(5_000, 5_000, "colour02")
    );


  }

  private static Car createCar(int price, int weight, String colour){
    final Car car = new Car();
    car.setPrice(price);
    car.setWeight(weight);
    car.setColour(colour);
    return car;
  }



  public static class Car {

    private int price = 0;
    private int weight = 0;
    private String colour;

    public int getPrice() {
      return price;
    }

    public void setPrice(final int price) {
      this.price = price;
    }

    public int getWeight() {
      return weight;
    }

    public void setWeight(final int weight) {
      this.weight = weight;
    }

    public String getColour() {
      return colour;
    }

    public void setColour(final String colour) {
      this.colour = colour;
    }
  }


}
