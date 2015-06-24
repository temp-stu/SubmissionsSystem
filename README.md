# SubmissionsSystem
#  AspectJ in Action

If we have a complexity problem, then modularization could be a solution (break up the system into concerns/functionalities such as data access, presentation etc.)

##  AOP
Code tangling caused by multiple simultaneous implementations of various concerns. 
1. _Core concerns_: the business logic, the core business concerns. (OOP)
2. _Crosscutting concerns_: logging, security etc. that cut across many other modules. (OOP + AOP)

AOP is a methodology that provides separation of crosscutting concerns by using an aspect where each aspect focuses on a s specific crosscutting functionality.

  > _Groovy makes it possible to implement an AOP-like functionality through its meta-object protocol (MOP) facility (see http://www.infoq.com/articles/aop-with-groovy)._

* Code generation is capable of doing anything AOP can do (and a lot more), AOP brings a level of discipline that is essential for good software engineering when it comes to dealing with crosscutting concerns. 
* AOP works in harmony with one of the most popular trends of agile programming by supporting the practice of "You aren't gonna need it" (YAGNI). Implementing a feature just because you may need it in the future often results in wasted effort because you won't actually need it. With AOP, you can practice YAGNI; and if you do need a particular kind of functionality later, you can implement it without having to make system-wide modifications.

##  Aspect weaver
composes the final sys by combining the core classes with the crosscutting aspects (the process of weaving). 
open/close principle = open for extension, closed for modifications.

  _an AOP implementation combines the classes and aspects to produce a woven executable._

**joint points**
identifiable points in the execution of the system, which can contain the following elements: 
* The _pointcut construct_ selects any join points that "_satisfy some criteria_" and may collect context at the selected points (example: method arguments).
* The _advice construct_ allows us to add behavior _before_, _after_ or _around_ the selected join points. Advice affects the execution of code and is thus considered to be _dynamic_ crosscutting.
* _static_ crosscutting affects the static structure of the system using _inter-type declarations_ and _weave-time declaration_ constructs 
* The aspect module to hold the extra behavior

AspectJ started and initially grew as a special language that extends the Java language with new keywords. It also provided a special compiler that could understand those extensions (ajc). But recently, a lot has changed in its form as a language, as well as in the weaver. First, AspectJ offers an alternative syntax based on the Java annotation facility to express crosscutting constructs. This lets you use a plain Java compiler instead of the special compiler. Second, AspectJ offers new options for weaving classes with aspects. 

We can classify the crosscutting constructs in the AOP model as:

1. common crosscutting constructs (join point, pointcut, and aspect)
2. dynamic crosscutting construct (advice)
3. static crosscutting constructs (inter-type declarations (ITD, also referred to as introduction) and weave-time declarations).
	
define a pointcut for all public methods:

```java
//public returnType declaringType.methodName(methodParameters)	
pointcut publicOperation() :			
    execution(public * *.*(..));
```

__Weaver__ needs to weave together classes and aspects so that advice gets executed, inter-type declarations affect the static structure, and weave-time declarations produce warnings and errors. AspectJ offers three weaving models:

* Source weaving: weaver is part of the compiler where input is classes + aspects -> woven java-compliant byte code.
* Binary weaving: input is in byte code form (used to weave .class files) - similar to linker in C/C++.
      ajc -inpath classes;aspects -aspectpath aspects -d woven
* Load-time weaving

__Join points categories__
1. Method + Constructor JP: 
  * Execution (JP at the called object), ctx will not include caller object.
  * Call (JP at the caller object when the method is invoked)
  * execution() and call() show the execution and call join points of a method or a constructor
2. Field access JP (instance + class/static fields, but _not local variables_):
  * Read-access
  * Write-access
  * Will not match access through the reflection API.
  * get() and set() show the read and write field access join points
3. Exception-handler JP: the handler block in handled exceptions (the catch block).
4. Class-initialization JP: initialization of class (static) variables, static blocks and the loading of a class.   
  * staticinitialization() and <clinit> part of the output indicates the class initialization
5. Object initialization JP: from the return of a parent class's constructor until the end of the first called constructor (does not include call to super, but does include call to alt constructor using this).
  * Unlike class-initialization that occurs when a class loader loads a class, object initialization occurs when an object is created.
  * preinitialization() and initialization() show object initialization
6. Object pre-initialization JP: only encompasses calls made while forming _arguments_ to the super() call in the object's constructor.
7. Advice execution JP: encompasses the execution of any advice in the system (profiling the advice itself or monitoring executions of advice).

>AspectJ's pointcut language is the same in the traditional and @AspectJ syntax. We can declare a pointcut inside an aspect, a class, or an interface. As with data and methods, you can use an access specifier to restrict access to it. Pointcuts can be either anonymous or named.
`public pointcut name():type(* Signature.*(..))`

__Pointcut syntax__: The term type collectively refers to classes, interfaces, aspects, annotations, and primitive types.
* \* denotes any number of characters except the period. In a type signature pattern, it denotes a part of the type or package name. In other patterns, it denotes a part of the name such as the method or field name.
* \.\. denotes any number of characters including any number of periods. In a type signature pattern, it denotes all direct and indirect subpackages. In method signature patterns, it denotes an arbitrary number of method arguments.
* \+ denotes any subtype of a given type. It may be used only as a suffix to a type signature pattern.
```java
Account // matches the Account type
*Account //matches any type with name ending with Accuont
java.*.Date //Date type in any subpackage of java package
java..* //any type in java package or any of its subpackages
javax..*Model+ //All the types in the javax package and its direct and indirect subpackages that have a name ending in Model, and their subtypes 
```


























