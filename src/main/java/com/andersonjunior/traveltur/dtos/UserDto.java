package com.andersonjunior.traveltur.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.andersonjunior.traveltur.enums.Profile;
import com.andersonjunior.traveltur.enums.Status;
import com.andersonjunior.traveltur.models.User;

import lombok.Data;

@Data
public class UserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String fullname;
    private String email;
    private String password;
    private Profile profile;
    private Status status;

    private LocalDateTime createdAt;

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
