package com.bruce.list;

/**
 * Author: bruce
 * Date:2020/10/21
 * Version:1.0.0
 */
public class OutOfMaxSizeException extends RuntimeException {
    public OutOfMaxSizeException() {
    }

    public OutOfMaxSizeException(String message) {
        super(message);
    }
}
