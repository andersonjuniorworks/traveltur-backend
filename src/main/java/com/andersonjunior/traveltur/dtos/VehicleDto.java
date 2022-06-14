package com.andersonjunior.traveltur.dtos;

import java.io.Serializable;

import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

import com.andersonjunior.traveltur.enums.Status;
import com.andersonjunior.traveltur.enums.VehicleType;
import com.andersonjunior.traveltur.models.User;
import com.andersonjunior.traveltur.models.Vehicle;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VehicleDto implements Serializable {

    @ApiModelProperty(notes = "ID gerado automaticamente")
    private Long id;

    @ApiModelProperty(notes = "Tipo de veículo")
    private VehicleType type;

    @ApiModelProperty(notes = "Placa do veículo")
    private String licensePlate;

    @ApiModelProperty(notes = "Descrição do veículo")
    private String description;

    @ApiModelProperty(notes = "Marca do veículo")
    private String brand;

    @ApiModelProperty(notes = "Modelo do veículo")
    private String model;

    @ApiModelProperty(notes = "Ano do veículo")
    private Integer year;

    @ApiModelProperty(notes = "Situação do cadastro", required = true)
    @NotEmpty
    private Status status;

    @ApiModelProperty(notes = "Usuário que cadastrou o registro")
    @OneToOne
    private User createdBy;

    public VehicleDto() {
        super();
    }

    public VehicleDto(Vehicle vehicle) {
        super();
        this.id = vehicle.getId();
        this.type = vehicle.getType();
        this.licensePlate = vehicle.getLicensePlate();
        this.description = vehicle.getDescription();
        this.brand = vehicle.getBrand();
        this.model = vehicle.getModel();
        this.year = vehicle.getYear();
        this.createdBy = vehicle.getCreatedBy();
    }

}
