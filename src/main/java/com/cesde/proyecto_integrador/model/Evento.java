package com.cesde.proyecto_integrador.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "eventos")
@Data
public class Evento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name = "";

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone")
    private String phone = "";

    @Column(name = "address")
    private String address = "";

    @Column(name = "url_photo")
    private String urlPhoto = "";

    @JsonBackReference(value = "user-profile")
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
