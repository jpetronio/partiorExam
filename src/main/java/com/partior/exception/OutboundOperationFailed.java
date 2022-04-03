package com.partior.exception;

public class OutboundOperationFailed extends Exception {
    public OutboundOperationFailed() {
        super("The server encountered an outbound operation error.");
    }
}
