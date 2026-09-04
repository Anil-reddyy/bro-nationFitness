package com.bronationfitness.model;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    // duration in months
    private int durationMonths;
    private BigDecimal price;

    // constructors, getters, setters

    public Plan() {}

    public Plan(String name, int durationMonths, BigDecimal price) {
        this.name = name;
        this.durationMonths = durationMonths;
        this.price = price;
    }

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getDurationMonths() { return durationMonths; }
    public void setDurationMonths(int durationMonths) { this.durationMonths = durationMonths; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
}

