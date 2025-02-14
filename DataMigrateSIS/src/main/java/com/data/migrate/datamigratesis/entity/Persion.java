package com.data.migrate.datamigratesis.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "persion")
@Data
public class Persion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Address")
    private String address;

    @Column(name = "age")
    private int age;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "mail")
    private String mail;

    // toString Method
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
