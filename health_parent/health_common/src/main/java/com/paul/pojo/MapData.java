package com.paul.pojo;

import java.io.Serializable;

public class MapData implements Serializable {

    private String name;

    private int value;

    public MapData(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "MapData{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
