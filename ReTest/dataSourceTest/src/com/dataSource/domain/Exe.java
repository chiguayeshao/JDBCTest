package com.dataSource.domain;

/**
 * @author yeshao
 * @date 2019/6/23 - 17:34
 */
public class Exe {

    private int id;
    private String name;
    private int baklance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBaklance() {
        return baklance;
    }

    public void setBaklance(int baklance) {
        this.baklance = baklance;
    }


    @Override
    public String toString() {
        return "Exe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", baklance=" + baklance +
                '}';
    }
}