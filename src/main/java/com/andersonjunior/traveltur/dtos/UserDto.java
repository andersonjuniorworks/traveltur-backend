package com.andersonjunior.traveltur.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.andersonjunior.traveltur.enums.Profile;
import com.andersonjunior.traveltur.enums.Status;
import com.andersonjunior.traveltur.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "ID gerado automaticamente")
    private Long id;

    @ApiModelProperty(notes = "Nome completo do usuário", required = true)
    @NotEmpty
    private String fullname;

    @ApiModelProperty(notes = "Email do usuário", required = true)
    @NotEmpty
    @Email(message = "Email inválido, preencha o campo corretamente!")
    private String email;

    @ApiModelProperty(notes = "Senha do usuário", required = true)
    @NotEmpty
    private String password;

    @ApiModelProperty(notes = "Perfil do usuário", required = true)
    private Profile profile;

    @ApiModelProperty(notes = "Situação do registro", required = true)
    private Status status;

    @ApiModelProperty(notes = "Data de criação do registro", required = false)
    private LocalDateTime createdAt;

    @ApiModelProperty(notes = "Data da última atualização do registro", required = false)
    private LocalDateTime updateAt;

    public UserDto() {
        super();
    }

    public UserDto(User user) {
        super();
        this.id = user.getId();
        this.fullname = user.getFullname();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.profile = user.getProfile();
        this.status = user.getStatus();
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateAt = LocalDateTime.now();
    }

}
