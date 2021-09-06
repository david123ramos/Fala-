package br.com.falae.echoserver.engine;

import java.io.Serializable;

public class Message implements Serializable {
    private String event;
    private Object data;

    public Message(String event, Object data) {
        this.event = event;
        this.data = data;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
