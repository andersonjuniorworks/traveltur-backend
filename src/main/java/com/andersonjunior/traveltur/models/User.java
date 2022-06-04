package com.andersonjunior.traveltur.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.andersonjunior.traveltur.enums.Profile;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullname;

    @ApiModelProperty(value = "Email não pode ser duplicado")
    @Column(unique = true)
    private String email;

    private String password;

    @ApiModelProperty(value = "ADMINISTRADOR, GERENTE, OPERADOR")
    private Profile profile;

}
