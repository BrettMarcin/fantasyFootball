package com.home;

import java.util.logging.Logger;

public class TeamModel {
    private String teamName;
    private String userName;
    private final static Logger log = Logger.getLogger(TeamModel.class.getName());
    public TeamModel(){
        log.info("inside team model");
    }

    public TeamModel(String teamName, String userName){
        this.teamName = teamName;
        this.userName = userName;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getUserName() {
        return userName;
    }
}
