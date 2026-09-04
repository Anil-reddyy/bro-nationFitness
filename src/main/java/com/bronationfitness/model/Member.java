package com.bronationfitness.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;
    private String email;

    private LocalDate startDate;
    private LocalDate expiryDate;

    @ManyToOne
    private Plan plan;

    @Column(length = 1000)
    private String notes;

    public Member() {}

    // convenience constructor
    public Member(String name, String phone, String email, LocalDate startDate, LocalDate expiryDate, Plan plan, String notes) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.startDate = startDate;
        this.expiryDate = expiryDate;
        this.plan = plan;
        this.notes = notes;
    }

    // getters / setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }

    public Plan getPlan() { return plan; }
    public void setPlan(Plan plan) { this.plan = plan; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
