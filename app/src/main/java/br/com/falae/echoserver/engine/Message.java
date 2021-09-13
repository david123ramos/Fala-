package br.com.falae.echoserver.engine;

import java.io.Serializable;

public class Message  {
    private String nick;
    private String hour;
    private String msg;

    public Message(String nick, String hour, String msg) {
        this.nick = nick;
        this.hour = hour;
        this.msg = msg;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
