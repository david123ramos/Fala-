package br.com.falae.models;

public class User {
    private String nick;
    private String ip;

    public User(String nick, String ip) {
        this.nick = nick;
        this.ip = ip;
    }
    public User(){}

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getIp() {
        return ip;
    }

    public void setIP(String IP) {
        this.ip = IP;
    }
}
