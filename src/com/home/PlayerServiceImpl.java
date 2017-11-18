package com.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerDAO playerDAO;

    @Override
    @Transactional
    public List<Player> getDBPlayers(){
        return playerDAO.getDBPlayers();
    }

    @Override
    @Transactional
    public void savePlayer(Player thePlayer){playerDAO.savePlayer(thePlayer);}

    @Override
    @Transactional
    public void deletePlayer(int theId){}

    @Override
    @Transactional
    public void clearPlayers(){}

    @Override
    @Transactional
    public void updatePlayer(Player thePlayer){}
}
