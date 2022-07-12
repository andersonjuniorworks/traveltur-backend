package com.andersonjunior.traveltur.dtos;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.andersonjunior.traveltur.enums.Profile;
import com.andersonjunior.traveltur.enums.Status;
import com.andersonjunior.traveltur.models.User;

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

    @ApiModelProperty(notes = "Perfil do usuário", required = true)
    private Profile profile;

    @ApiModelProperty(notes = "Situação do registro", required = true)
    private Status status;

    public UserDto() {
        super();
    }

    public UserDto(User user) {
        super();
        this.id = user.getId();
        this.fullname = user.getFullname();
        this.email = user.getEmail();
        this.profile = user.getProfile();
        this.status = user.getStatus();
    }

}
