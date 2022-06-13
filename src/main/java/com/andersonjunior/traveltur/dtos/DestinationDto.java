package com.andersonjunior.traveltur.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.OneToOne;

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
    private String name;

    @ApiModelProperty(notes = "Usuário que criou o registro", required = true)
    @OneToOne
    private User createdBy;

    @ApiModelProperty(notes = "Data de criação do registro", required = false)
    private LocalDateTime createdAt;

    @ApiModelProperty(notes = "Data da última atualização do registro", required = false)
    private LocalDateTime updateAt;

    public DestinationDto() {
        super();
    }

    public DestinationDto(Destination destination) {
        super();
        this.id = destination.getId();
        this.name = destination.getName();
        this.createdBy = destination.getCreatedBy();
        this.createdAt = destination.getCreatedAt();
        this.updateAt = destination.getUpdateAt();
    }

}
