package net.hamendi.security;

import net.hamendi.messaging.MessageCommunicator;

/*
C:\000_training\NETBEANS_WORKSPACE\TestingAspectJ>
ajc -d classes -source 7 src\main\java\net\hamendi\main\Main.java src\main\java\net\hamendi\messaging\MessageCommunicator.java src\main\java\net\hamendi\security\SecurityAdapter.aj src\main\java\net\hamendi\security\*.java

C:\000_training\NETBEANS_WORKSPACE\TestingAspectJ>
java -cp %CLASSPATH%;classes net.hamendi.main.Main

If not running from command line but inside Netbeans, then dont use the "Run" icon, use the ant build.xml "run" target
*/
//Aspect declaration
//This file is an aspect > An aspect is a "unit of modularization" in AOP (like a class is a "unit of modularization" in OOP)
//@Aspect
public aspect SecurityAspect {


  private Authenticator authenticator = new Authenticator();

    //Pointcut declaration (selecting join points)
  //@Pointcut("execution(* net.hamendi.messaging.MessageCommunicator.deliver(..))")
  //public void secureAccess() {} <-- empty method to give the pointcut a label
  pointcut secureAccess()
            : execution(* MessageCommunicator.deliver(..));

    //Advice (code to execute upon reaching join points) - DYNAMIC CROSSCUTTING
  //@Before("secureAccess()")
  //public void secure() { <-- advice logic
  //	System.out.println("Checking authenticity");
  //	authenticator.authenticate();
  //}
  before() : secureAccess() {
    System.out.println("Checking authenticity");
    authenticator.authenticate();
  }

  //warn about direct calls to the Authenticator.authenticate() method. The following declaration will cause the compiler to issue a warning if any part of the system calls the prohibited method—except, of course, SecurityAspect
  declare warning
    : call(void Authenticator.authenticate()) && !within(SecurityAspect)
    : "Authentication should be performed only by SecurityAspect";
}
