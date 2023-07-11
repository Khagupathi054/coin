package com.coin.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Coin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double denomination;
    private int count;

    public Coin() {
    }

    public Coin(double denomination, int count) {
        this.denomination = denomination;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public double getDenomination() {
        return denomination;
    }

    public void setDenomination(double denomination) {
        this.denomination = denomination;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

