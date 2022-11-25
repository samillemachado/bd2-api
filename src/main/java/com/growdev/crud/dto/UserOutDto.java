package com.growdev.crud.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.growdev.crud.entities.Recado;
import com.growdev.crud.entities.User;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class UserOutDto implements Serializable {
    private Long userId;

    private String name;
    private String email;
    private Instant created;
    private List<RecadoOutDto> recados;

    public UserOutDto(User user){
        this.userId = user.getUserId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.created = user.getCreated();
        this.recados = user.getListaRecados().stream()
                .map(RecadoOutDto::new)
                .collect(Collectors.toList());
    }
}
