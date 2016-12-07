package org.rapidpm.workshop.frp.m02_pattern.v001;

import java.util.Optional;

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
 * Created by RapidPM - Team on 04.12.16.
 */
public class M02V001_A {

  public interface Service {
    String doWork(String value);
  }

  public static class DefaultService implements Service {
    @Override
    public String doWork(String value) {
      return Optional.ofNullable(value)
          .orElse("")
          .toUpperCase();
    }
  }


  public static class VirtualNotThreadSafeProxyService implements Service {

    private Service delegator;

    @Override
    public String doWork(final String value) {
      if(delegator == null) delegator = new DefaultService();
      return delegator.doWork(value);
    }
  }




}
