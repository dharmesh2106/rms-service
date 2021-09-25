package com.rms.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundException extends Exception {
    private List<String> messages;
    private Integer code = 404;

    public NotFoundException(){
        super();
        this.messages = new ArrayList<String>();
    }

    public NotFoundException(String message){
        super(message);
        this.messages = new ArrayList<String>();
        if(StringUtils.isNotBlank(message)) {
            this.messages.add(message);
        }

    }

    public NotFoundException(List<String> messages){
        super(messages.toString());
        this.messages = messages;
    }

    public NotFoundException(String message, Integer code){
        super(message);
        this.messages = new ArrayList<String>();
        if(StringUtils.isNotBlank(message)) {
            this.messages.add(message);
        }

        this.code = code;
    }

    public NotFoundException(List<String> messages, Integer code){
        super(messages.toString());
        this.messages = messages;
        this.code = code;
    }

}
