package org.rapidpm.workshop.frp.m02_pattern.v005;

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
 * Created by RapidPM - Team on 07.12.16.
 */
public class M02V005 {

  interface TextFormatter {
    boolean filter(String text);
    String format(String text);
  }

  public static class PlainTextFormatter implements TextFormatter {

    @Override
    public boolean filter( String text ) {
      return true;

    }
    @Override
    public String format( String text ) {
      return text;
    }
  }

  public static class ErrorTextFormatter implements TextFormatter {

    @Override
    public boolean filter( String text ) {
      return text.startsWith( "ERROR" );

    }
    @Override
    public String format( String text ) {
      return text.toUpperCase();
    }
  }

  public static class ShortTextFormatter implements TextFormatter {

    @Override
    public boolean filter( String text ) {
      return text.length() < 20;

    }
    @Override
    public String format( String text ) {
      return text.toLowerCase();
    }
  }

  public static class TextEditor {
    private final TextFormatter textFormatter;

    public TextEditor(TextFormatter textFormatter) {
      this.textFormatter = textFormatter;
    }

    public void publishText(String text) {
      if (textFormatter.filter( text )) {
        System.out.println( textFormatter.format( text ) );
      }
    }
  }

  public static void main( String[] args ) {
    TextEditor textEditor = new TextEditor( new ErrorTextFormatter() );
    textEditor.publishText( "ERROR - something bad happened" );
    textEditor.publishText( "DEBUG - I'm here" );
  }




}
