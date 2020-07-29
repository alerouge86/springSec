package com.alerouge.kyivent.service.event;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alerouge.kyivent.model.table.CategoriaEventoEntity;
import com.alerouge.kyivent.repository.event.CategoriaEventoRepository;

@Service
public class CategoriaEventoService implements ICategoriaEventoService {

	@Autowired
	private CategoriaEventoRepository categoriaEventoRepository;

	@Override
	public List<CategoriaEventoEntity> getAllCategorie() {
		List<CategoriaEventoEntity> result = (List<CategoriaEventoEntity>) categoriaEventoRepository.findAll();
		if(!result.isEmpty()) {
			return result;
		} else {
			return new ArrayList<>();
		}
	}

}
