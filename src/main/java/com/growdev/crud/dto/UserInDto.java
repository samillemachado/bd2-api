package com.growdev.crud.dto;

import com.growdev.crud.entities.User;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class UserInDto implements Serializable {
    private Long id_user;
    private String name;
    private String email;
    private String pass;
    private Instant created;

    public UserInDto(User user){
        this.id_user = user.getUserId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.pass = user.getPass();
        this.created = user.getCreated();
    }
}
