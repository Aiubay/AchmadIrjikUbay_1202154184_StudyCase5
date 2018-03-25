package com.example.ubay.achmadirjikubay_1202154184_modul5;

/**
 * Created by black on 3/25/2018.
 */

public class List {
    //public dbhelper dbhelper
    private int ID;
    private String name;
    private String desc;
    private String priority;

    //cursor c

    public List(int ID, String name, String desc, String priority){
        this.ID = ID;
        this.name = name;
        this.desc = desc;
        this.priority = priority;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

}
