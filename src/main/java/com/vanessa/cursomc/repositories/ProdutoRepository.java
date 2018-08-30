package com.vanessa.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vanessa.cursomc.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
