package com.officeAuto.ssm.exception;

import com.officeAuto.ssm.model.Announce;

public class AnnounceException extends Exception {

    private String message;


    public AnnounceException(String message)
    {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
