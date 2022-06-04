package com.andersonjunior.traveltur.models;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@Table(name = "tb_passenger_list")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PassengerList implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "Viagem selecionada")
    @OneToOne
    private Travel travel;

    @ApiModelProperty(value = "Passageiro selecionado")
    @OneToOne
    private Passenger passenger;

    @ApiModelProperty(value = "Poltrona do passageiro")
    private Integer seat;

    @ApiModelProperty(value = "Usuário que criou o registro")
    @OneToOne
    private User createdBy;

    @ApiModelProperty(value = "Data da criação do registro")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "Data da última atualização do registro")
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
