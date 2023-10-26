package org.top.productsandordersapiapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "client_t")
public class Client {

    // поля - соответствуют столбцам (атрибутам) таблице в БД
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name_f", nullable = false)
    private String name;
    @Column(name = "email_f", nullable = false)
    private String email;

    // поля реализующие связь один-ко-многим
    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private Set<Order> clientOrders;

    // constructors

    // 1. конструктор по умолчанию
    public Client() {
        id = 0;
        name = "";
        email = "";
    }

    // getters & setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Order> getClientOrders() {
        return clientOrders;
    }

    public void setClientOrders(Set<Order> clientOrders) {
        this.clientOrders = clientOrders;
    }

    // представление объекта в виде строки

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
