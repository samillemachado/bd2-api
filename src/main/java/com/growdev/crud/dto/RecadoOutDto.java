package com.growdev.crud.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.growdev.crud.entities.Recado;
import com.growdev.crud.utils.StatusEnum;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecadoOutDto implements Serializable {

    private Long recadoId;
    private String title;
    private String description;
    private Instant created;
    private Instant updated;
    private StatusEnum statusRec;
    private Boolean isArquivado;
    private Long userId;

    public RecadoOutDto(Recado recado){
        this.recadoId = recado.getRecadoId();
        this.title = recado.getTitle();
        this.description = recado.getDescription();
        this.created = recado.getCreated();
        this.updated = recado.getUpdated();
        this.statusRec = recado.getStatusRec();
        this.isArquivado = recado.getIsArquivado();
        this.userId = recado.getUser().getUserId();
    }
}
