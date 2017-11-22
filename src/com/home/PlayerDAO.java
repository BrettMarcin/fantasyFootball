package com.home;

import java.util.List;

public interface PlayerDAO {
    public List<Player> getDBPlayers();
    public void savePlayer(Player thePlayer);
    public void deletePlayer(int theId);
    public void clearPlayers();
    public void updatePlayer(Player thePlayer);
}