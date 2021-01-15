package com.devsuperior.dsdelivery.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdelivery.DTO.OrderDTO;
import com.devsuperior.dsdelivery.DTO.ProductDTO;
import com.devsuperior.dsdelivery.entities.Order;
import com.devsuperior.dsdelivery.entities.OrderStatus;
import com.devsuperior.dsdelivery.entities.Product;
import com.devsuperior.dsdelivery.repositories.OrderRepository;
import com.devsuperior.dsdelivery.repositories.ProductRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private ProductRepository productrepository;
	
	@Transactional(readOnly = true)
	public List<OrderDTO> findAll(){
		List<Order> list = repository.findOrdersWithProducts();
		return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional
	public OrderDTO insert (OrderDTO dto){
		
		/*
		 * Nélio, por algum motivo não estava aceitando o parametro null para Long id
		 * na criação do OBJ, tentei dar (long) null, mas também não foi.
		 * Na ferramenta PostMan apresentava erro 500 quanto tentava dar o input.
		 * Desta forma criei um novo método construtor sem o ID, dessa forma funcionou perfeitamente.
		*/
		
		Order order = new Order(dto.getAddress(), dto.getLatitude(), 
				dto.getLongitude(), Instant.now(), OrderStatus.PENDING);
		
		for (ProductDTO p : dto.getProducts()) {
			Product product = productrepository.getOne(p.getId());
			order.getProducts().add(product);
		}
		
		order = repository.save(order);
		
		return new OrderDTO(order);
	}
	
}
