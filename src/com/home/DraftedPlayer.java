package com.home;

public class DraftedPlayer {
    private Player player;
    private String cookieValue;

    public DraftedPlayer(){
        super();
    }
    public DraftedPlayer(Player player, String cookieValue){
        this.player = player;
        this.cookieValue = cookieValue;
    }
    public Player getPlayer() {
        return player;
    }

    public String getCookieValue() {
        return cookieValue;
    }
}
