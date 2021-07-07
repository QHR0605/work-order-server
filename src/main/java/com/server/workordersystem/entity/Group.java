package com.server.workordersystem.entity;

import io.swagger.models.auth.In;

public class Group {

    private Integer gid;
    private String name;
    private Integer mentor;

    public Group(){

    }

    public Group gid(Integer gid){
        this.setGid(gid);
        return this;
    }

    public Group name(String name){
        this.setName(name);
        return this;
    }

    public Group mentor(Integer mentor){
        this.setMentor(mentor);
        return this;
    }


    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMentor() {
        return mentor;
    }

    public void setMentor(Integer mentor) {
        this.mentor = mentor;
    }
}
