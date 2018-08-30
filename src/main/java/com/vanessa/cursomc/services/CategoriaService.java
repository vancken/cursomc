package com.vanessa.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vanessa.cursomc.domain.Categoria;
import com.vanessa.cursomc.repositories.CategoriaRepository;
import com.vanessa.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria buscar(Integer id) {
		Categoria obj = categoriaRepository.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado. Id: " + id + ", Tipo: " + Categoria.class.getName());
		}
		return obj;
	}

}
