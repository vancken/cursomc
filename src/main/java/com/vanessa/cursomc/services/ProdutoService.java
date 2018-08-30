package com.vanessa.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.vanessa.cursomc.repositories.ProdutoRepository;

public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
}
