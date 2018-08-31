package com.vanessa.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vanessa.cursomc.domain.Categoria;
import com.vanessa.cursomc.domain.Cidade;
import com.vanessa.cursomc.domain.Cliente;
import com.vanessa.cursomc.domain.Endereco;
import com.vanessa.cursomc.domain.Estado;
import com.vanessa.cursomc.domain.Pagamento;
import com.vanessa.cursomc.domain.PagamentoComBoleto;
import com.vanessa.cursomc.domain.PagamentoComCartao;
import com.vanessa.cursomc.domain.Pedido;
import com.vanessa.cursomc.domain.Produto;
import com.vanessa.cursomc.domain.enums.EstadoPagamento;
import com.vanessa.cursomc.domain.enums.TipoCliente;
import com.vanessa.cursomc.repositories.CategoriaRepository;
import com.vanessa.cursomc.repositories.CidadeRepository;
import com.vanessa.cursomc.repositories.ClienteRepository;
import com.vanessa.cursomc.repositories.EnderecoRepository;
import com.vanessa.cursomc.repositories.EstadoRepository;
import com.vanessa.cursomc.repositories.PagamentoRepository;
import com.vanessa.cursomc.repositories.PedidoRepository;
import com.vanessa.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().add(p2);
		
		p1.getCategorias().add(cat1);
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().add(cat1);
		
		categoriaRepository.save(Arrays.asList(cat1, cat2));
		produtoRepository.save(Arrays.asList(p1, p2, p3));
		
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlância", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().add(c1);
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.save(Arrays.asList(est1, est2));
		cidadeRepository.save(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Vanessa Ancken", "v.ancken@gmail.com", "38766038898", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("4451-1902", "97131-7552"));
		Endereco end1 = new Endereco(null, "Rua das Rosas", "367", "-", "Vila Marina", "09176-170", cli1, c2);
		Endereco end2 = new Endereco(null, "Rua Flores", "105", "Sala 80", "Centro", "3870-180", cli1, c1);
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		
		clienteRepository.save(cli1);
		enderecoRepository.save(Arrays.asList(end1, end2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 10:32"), cli1, end2);
		
		Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pgto1);
		
		Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pgto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.save(Arrays.asList(ped1, ped2));
		pagamentoRepository.save(Arrays.asList(pgto1, pgto2));
		
		
		
		
	}
}
