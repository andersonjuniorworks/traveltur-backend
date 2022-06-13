package com.andersonjunior.traveltur.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.andersonjunior.traveltur.enums.DocumentType;
import com.andersonjunior.traveltur.enums.Status;
import com.andersonjunior.traveltur.models.Passenger;
import com.andersonjunior.traveltur.models.User;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PassengerDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "ID gerado automaticamente")
    private Long id;

    @ApiModelProperty(notes = "Tipo do documento", required = true)
    private DocumentType documentType;

    @ApiModelProperty(notes = "Nº do documento", required = true)
    private String documentNumber;

    @ApiModelProperty(notes = "Nome completo", required = true)
    private String fullname;

    @ApiModelProperty(notes = "Data de nascimento", required = false)
    private LocalDateTime birthDate;

    @ApiModelProperty(notes = "Situação do registro", required = true)
    private Status status;

    @ApiModelProperty(notes = "Usuário que criou o registro", required = true)
    @OneToOne
    private User createdBy;

    @ApiModelProperty(notes = "Data de criação do registro", required = false)
    private LocalDateTime createdAt;

    @ApiModelProperty(notes = "Data da última atualização do registro", required = false)
    private LocalDateTime updateAt;

    public PassengerDto() {
        super();
    }

    public PassengerDto(Passenger passenger) {
        super();
        this.id = passenger.getId();
        this.documentType = passenger.getDocumentType();
        this.documentNumber = passenger.getDocumentNumber();
        this.fullname = passenger.getFullname();
        this.status = passenger.getStatus();
        this.createdBy = passenger.getCreatedBy();
        this.createdAt = passenger.getCreatedAt();
        this.updateAt = passenger.getUpdateAt();
    }

    @ApiModelProperty(notes = "Data de criação do registro", required = false)
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @ApiModelProperty(notes = "Data da última atualização do registro", required = false)
    @PreUpdate
    protected void onUpdate() {
        this.updateAt = LocalDateTime.now();
    }

}
