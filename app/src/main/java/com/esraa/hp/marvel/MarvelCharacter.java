package com.esraa.hp.marvel;

/**
 * Created by HP on 2018-11-26.
 */

public class MarvelCharacter {
    private String imgUrl;
    private String name;
    private String team;
    private String bio;

    public MarvelCharacter(String imgUrl,String name,String team,String bio){
        this.imgUrl=imgUrl;
        this.name=name;
        this.team=team;
        this.bio=bio;
    }

    public String getName() {
        return name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getBio() {
        return bio;
    }

    public String getTeam() {
        return team;
    }
}
