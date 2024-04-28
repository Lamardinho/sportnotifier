package com.lamardinho.sportnotifier.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Entity
@Table(name = "user_permissions")
@EqualsAndHashCode(of = {"id"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserPermission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;
}
