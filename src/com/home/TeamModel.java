package com.home;

import javax.xml.bind.annotation.XmlElement;

public class TeamModel {
    @XmlElement private String teamName;
    @XmlElement private String userName;

    public TeamModel(){

    }
    public TeamModel(String teamName, String userName){
        this.teamName = teamName;
        this.userName = userName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
