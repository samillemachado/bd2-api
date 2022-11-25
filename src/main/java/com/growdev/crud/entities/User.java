package com.growdev.crud.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;



@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long userId;
    private String name;
    private String email;
    private String pass;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant created;

    //Função que vai ser executada assim que inserir a informação, devido a anotação;
    @PrePersist
    public void prepersist(){
        created = Instant.now();
    }

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Recado> listaRecados;

    public List<Recado> getListaRecados() {
        return listaRecados == null ? new ArrayList<>() : listaRecados;
    }
}




