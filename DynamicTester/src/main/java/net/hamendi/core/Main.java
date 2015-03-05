package net.hamendi.core;

import net.hamendi.messaging.MessageCommunicator;
import net.hamendi.security.Authenticator;

public class Main {
    public static void main(String[] args) {
        MessageCommunicator messageCommunicator = new MessageCommunicator();
        messageCommunicator.deliver("Wanna learn AspectJ?");
        messageCommunicator.deliver("Harry", "having fun?");
        //CAVEAT: the method does not exist in msgcomm, it is declared through an aspect!
        System.out.println(">>>>>>>>>>"+messageCommunicator.getLastAccessedTime());
        //displays a warning in the "Problems" tab (a compiler warning)
        new Authenticator().authenticate();
    }
}
