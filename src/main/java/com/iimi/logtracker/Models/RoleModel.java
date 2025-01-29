package com.iimi.logtracker.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RoleModel extends BaseModel {
    @Column(nullable = false)
    private String role;
}
