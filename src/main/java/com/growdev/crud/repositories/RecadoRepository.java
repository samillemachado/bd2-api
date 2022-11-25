package com.growdev.crud.repositories;

import com.growdev.crud.entities.Recado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecadoRepository extends JpaRepository <Recado, Long>{
}
