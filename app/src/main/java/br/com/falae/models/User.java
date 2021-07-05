package br.com.falae.models;

public class User {
    private String nick;
    private String IP;

    public User(String nick, String ip) {
        this.nick = nick;
        this.IP = ip;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }
}
