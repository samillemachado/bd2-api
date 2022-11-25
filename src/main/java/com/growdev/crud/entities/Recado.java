package com.growdev.crud.entities;

import com.growdev.crud.utils.StatusEnum;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;



@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "recados")

public class Recado implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_recado")
    private Long recadoId;

    private String title;

    private String description;

    @Column(name="status_rec")
    private StatusEnum statusRec;

    @Column(name="is_arquivado")
    private Boolean isArquivado;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant created;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant updated;

    @PrePersist
    public void prepersist(){
        created = Instant.now();
    }

    @PreUpdate
    public void preupdate(){
        updated = Instant.now();
    }

    @ManyToOne
    @JoinColumn(name = "id_user_fk")
    private User user;
}
