package com.rms.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


public class DataValidationException extends Exception {
    private List<String> messages;
    private Integer code;

    public DataValidationException() {
        super();
        this.messages = new ArrayList<String>();
    }

    public DataValidationException(String message) {
        super(message);
        this.messages = new ArrayList<String>();
        if(StringUtils.isNotBlank(message)) {
            this.messages.add(message);
        }
    }

    public DataValidationException(List<String> messages) {
        super(messages.toString());
        this.messages = messages;
    }

    public DataValidationException(String message, Integer code) {
        super(message);
        this.messages = new ArrayList<String>();
        if(StringUtils.isNotBlank(message)) {
            this.messages.add(message);
        }
        this.code = code;
    }

    public DataValidationException(List<String> messages, Integer code) {
        super(messages.toString());
        this.messages = messages;
        this.code = code;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


}
