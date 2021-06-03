package com.devsuperior.dsdeliver.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdeliver.dto.OrderDTO;
import com.devsuperior.dsdeliver.dto.ProductDTO;
import com.devsuperior.dsdeliver.entities.Order;
import com.devsuperior.dsdeliver.entities.Product;
import com.devsuperior.dsdeliver.entities.enums.OrderStatus;
import com.devsuperior.dsdeliver.mapper.OrderMapper;
import com.devsuperior.dsdeliver.repositories.OrderRepository;
import com.devsuperior.dsdeliver.repositories.ProductRepository;

@Service
public class OrderService {

	
		@Autowired
		private OrderRepository repository;
		
		@Autowired
		private ProductRepository productRepository;
		
		@Autowired
		private OrderMapper mapper;
		
		@Transactional
		public OrderDTO insert(OrderDTO dto) {
			Order order = mapper.toEntity(dto);
			for(ProductDTO p : dto.getProducts()) {
				Product product = productRepository.getOne(p.getId());
				order.getProducts().add(product);
			}
			repository.save(order);
			return new OrderDTO(order);
		}
		
		@Transactional
		public OrderDTO setDelivered(Long id) {
			Order order = repository.getOne(id);
			order.setStatus(OrderStatus.DELIVERED);
			repository.save(order);
			return new OrderDTO(order);
		}
		
		@Transactional(readOnly = true)
		public List<OrderDTO> findAll(){
			return repository.findOrdersWithProducts().stream().map(x-> new OrderDTO(x)).collect(Collectors.toList());
		}
	
}
