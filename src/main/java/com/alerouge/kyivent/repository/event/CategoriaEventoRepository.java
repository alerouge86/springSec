package com.alerouge.kyivent.repository.event;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alerouge.kyivent.model.table.CategoriaEventoEntity;


@Repository
public interface CategoriaEventoRepository extends CrudRepository<CategoriaEventoEntity, Long> {

}
