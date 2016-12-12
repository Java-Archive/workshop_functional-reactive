# Functional Reactive Core Java
Here we are with the FRP - Workshop.
This here is the TOC of the workshop. Feel free to use this one.
The workshop is for Community - Activities and to learn for yourself.

The workshop is still under heavy development right now!
There is the website [http://www.functional-reactive.org/](http://www.functional-reactive.org/)
that have a way more text as this readme.

If you have any questions, ask me ;-) 
You could reach me via
 - [Twitter](https://twitter.com/SvenRuppert)
 - [LinkedIn](https://www.linkedin.com/in/svenruppert)
 - [Xing](https://www.xing.com/profile/Sven_Ruppert2)
 - [email](mailto:sven.ruppert@gmail.com)

## Java Core Classes
### Tupel , Tripel, Quad and Quint

## Java Basics - m00_basics
### Interfaces
#### Inheritance and default implementations
#### public static methods
#### public static attributes
#### Java9 JEP213 - private methods in interfaces
#### Difference between class and Lambda
### Optional<T>

### Lambdas - m01_lambdas
### Basic Functional Interfaces
### Functional Interfaces from the JDK
#### Consumer
#### Supplier
#### Predicate
#### BiPredicate
#### Function
#### BiFunction

### Mixins

### Exercises : Refactoring UserDAO
The Solution you could find under [https://github.com/svenruppert/JDBC-DAO](https://github.com/svenruppert/JDBC-DAO)

#### practical refactoring
#### explore the solutions
#### show the result
#### show QueryOneValue with explicit typing

## Pattern - m02_pattern
### v001 - Proxy with Functional Interfaces
#### by hand
#### with FunctionalInterfaces
#### with FunctionalInterfaces from JDK
#### With ProxyBuilder<T>
### v003 - Decorator
### v004 - Interpreter
### v005 - Strategy

## Functional with Lambdas - m05
### higher order functions
### memorizing functions
### how to create Functions 
```java
Function<String, String> func = new Closure().func()
```
### recursion
#### ForkAndJoin - RecursiveAction
#### TailRecursion Optimization


## Reactive 
### Observable
### CompletableFuture
### Flow Java9 - Pub/Sub

## FRP - combining both in Java

