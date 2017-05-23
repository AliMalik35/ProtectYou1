package com.example.muhammad.protectyou1.Models;

/**
 * Ashley Menhennett <ashleymenhennett@gmail.com>
 */

/**
 * Emergency POJO (DTO)
 */
public class EmergencyContact {

    private int id;
    private String name;
    private String relation;
    private String phone;

    public EmergencyContact(int id, String name, String relation, String phone){
        this.id = id;
        this.name = name;
        this.relation = relation;
        this.phone = phone;
    }

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

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return name + '\n' +
                relation + '\n' +
                phone;
    }
}
