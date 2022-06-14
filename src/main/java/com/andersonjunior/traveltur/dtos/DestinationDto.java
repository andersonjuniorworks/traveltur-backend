package com.andersonjunior.traveltur.dtos;

import java.io.Serializable;

import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

import com.andersonjunior.traveltur.models.Destination;
import com.andersonjunior.traveltur.models.User;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DestinationDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "ID gerado automaticamente")
    private Long id;

    @ApiModelProperty(notes = "Nome do destino", required = true)
    @NotEmpty
    private String name;

    @ApiModelProperty(notes = "Sigla do estado", required = true)
    @NotEmpty
    private String state;

    @ApiModelProperty(notes = "Nome da cidade", required = true)
    @NotEmpty
    private String city;

    @ApiModelProperty(notes = "Usuário que criou o registro", required = true)
    @OneToOne
    private User createdBy;

    public DestinationDto() {
        super();
    }

    public DestinationDto(Destination destination) {
        super();
        this.id = destination.getId();
        this.name = destination.getName();
        this.state = destination.getState();
        this.city = destination.getCity();
        this.createdBy = destination.getCreatedBy();
    }

}
