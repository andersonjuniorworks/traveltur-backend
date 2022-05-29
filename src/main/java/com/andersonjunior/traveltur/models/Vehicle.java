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

import com.andersonjunior.traveltur.enums.VehicleType;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_vehicles")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Vehicle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "MOTONETA, MOTOCICLETA, TRICICLO, QUADRICICLO, AUTOMOVEL, MICROONIBUS, ONIBUS, BONDE, REBOQUE, CHARRETE")
    private VehicleType type;

    @ApiModelProperty(value = "Placa não pode ser duplicada")
    @Column(unique = true)
    private String licensePlate;

    private String description;
    private String brand;
    private String model;
    private Integer year;

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
