package com.growdev.crud.dto;

import com.growdev.crud.entities.Recado;
import com.growdev.crud.utils.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class RecadoInDto implements Serializable {
    private Long recadoId;
    private String title;
    private String description;
    private Instant created;
    private Instant updated;

    private StatusEnum statusRec;
    private Boolean isArquivado;
    private Long userId;

    public RecadoInDto(Recado recado){
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
