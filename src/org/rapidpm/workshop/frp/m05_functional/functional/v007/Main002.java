package org.rapidpm.workshop.frp.m05_functional.functional.v007;

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
public class Main002 {

  public static void main(String[] args) {

  }

  public static class Price {

    public static Price ZERO = new Price(0);

    private final int amount;
//    private final enum currency;

    public Price(final int amount) {
      this.amount = amount;
    }

    public int getAmount() {
      return amount;
    }

    //add special methods..
    public Price add(Price toAdd){
      return new Price(this.amount + toAdd.amount);
    }


  }
}
