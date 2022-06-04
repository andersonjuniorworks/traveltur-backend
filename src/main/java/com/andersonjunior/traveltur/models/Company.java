package com.andersonjunior.traveltur.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_companies")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Company implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "Documento não pode ser duplicado")
    @Column(unique = true)
    private String cpfCnpj;

    private String businessName;
    private String zipcode;
    private String address;
    private String number;
    private String complement;
    private String neighborhood;
    private String state;
    private String city;
    private String phoneNumber;
    private String email;

    @ApiModelProperty(value = "Usuário que criou o registro")
    @OneToOne
    private User createdBy;

    private LocalDateTime createdAt;

    private LocalDateTime updateAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateAt = LocalDateTime.now();
    }

}
