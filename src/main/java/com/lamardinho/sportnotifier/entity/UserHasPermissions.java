package com.lamardinho.sportnotifier.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "user_has_permissions")
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserHasPermissions {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "permission_id")
    private Integer permissionId;
}
