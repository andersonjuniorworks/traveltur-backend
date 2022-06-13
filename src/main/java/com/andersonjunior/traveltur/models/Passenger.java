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

import com.andersonjunior.traveltur.enums.DocumentType;
import com.andersonjunior.traveltur.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_passengers")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Passenger implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private DocumentType documentType;

    @Column(unique = true)
    private String documentNumber;

    private String fullname;

    private LocalDateTime birthDate;

    private Status status;

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
