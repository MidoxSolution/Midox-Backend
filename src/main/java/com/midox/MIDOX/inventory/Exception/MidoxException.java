package com.midox.MIDOX.inventory.Exception;

public class MidoxException extends Exception {

    public static String EXCEPTION_ENTITY_DOES_NOT_EXIST = "Entity doesn't exist in system";

    public MidoxException(String message) {
        super(message);
    }

}
