package org.top.productsandordersapiapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "order_t")
public class Order {

    // поля - соответствуют столбцам (атрибутам) таблице в БД
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description_f", nullable = false)
    private String description;

    // поля, реализующие связь "один-ко-многим"
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    // поля, реализующие связь "один-ко-многим"
    @OneToMany(mappedBy = "order")
    @JsonIgnore
    private Set<ProductOrder> productOrders;

    @Column(name = "sum_f")
    //NEW. Поле для хранения суммы Чека
    private Double sumOrder;

    // constructors

    // 1. конструктор по умолчанию
    public Order() {
        id = 0;
        description = "";
        sumOrder = 0.0;
    }

    // getters & setters


    public Double getSumOrder() {
        return sumOrder;
    }

    public void setSumOrder(Double sumOrder) {
        this.sumOrder = sumOrder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<ProductOrder> getProductOrders() {
        return productOrders;
    }

    public void setProductOrders(Set<ProductOrder> productOrders) {
        this.productOrders = productOrders;
    }
}
