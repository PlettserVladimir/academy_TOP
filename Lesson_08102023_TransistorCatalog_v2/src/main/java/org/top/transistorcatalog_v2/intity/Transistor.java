package org.top.transistorcatalog_v2.intity;

import jakarta.persistence.*;


@Entity
@Table(name = "transistor_t")
public class Transistor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title_f",nullable = false)
    private String title;
    @Column(name = "power_f", nullable = false)
    private Double power;
    @Column(name = "voltage_f",nullable = false)
    private Double voltage;
    @Column(name = "weight_f")
    private Double weight;

    public Transistor(){
        id = 0;
        title = "";
        power = 0.0;
        voltage = 0.0;
        weight = 0.0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPower() {
        return power;
    }

    public void setPower(Double power) {
        this.power = power;
    }

    public Double getVoltage() {
        return voltage;
    }

    public void setVoltage(Double voltage) {
        this.voltage = voltage;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
    @Override
    public String toString(){
        return "{Transistor} id=" + id + " title=" + title + " power=" + power + " voltage=" + voltage + " weight=" + weight;
    }
}
