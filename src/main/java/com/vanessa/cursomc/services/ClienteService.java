package com.vanessa.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vanessa.cursomc.domain.Cliente;
import com.vanessa.cursomc.repositories.ClienteRepository;
import com.vanessa.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente find(Integer id){
		Cliente obj = clienteRepository.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado. Id: " + id);
		}
		return obj;
	}

}
