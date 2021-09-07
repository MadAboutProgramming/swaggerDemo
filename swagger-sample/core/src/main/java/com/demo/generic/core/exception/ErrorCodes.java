package com.demo.generic.core.exception;

/**
 * This is generalized exception code for reporting system.
 *
 * @author avinash
 */
public class ErrorCodes {

    private ErrorCodes() {

    }

    public enum Exception_Codes {

        // site message
        INVALID_SITE_NUMBER("S1001", 400),
        SITE_NOT_FOUND("S1004", 404), INVALID_SITE("S1005", 400),
        INVALID_INPUT("S1006", 404), SITE_ALREADY_EXIST("S1007", 400),

        ;

        private final String messageId;
        private final int errorCode;

        Exception_Codes(String messageId, int errorCode) {
            this.messageId = messageId;
            this.errorCode = errorCode;
        }

        public String getMessageId() {
            return this.messageId;
        }

        public int getErrorCode() {
            return this.errorCode;
        }
    }
}
