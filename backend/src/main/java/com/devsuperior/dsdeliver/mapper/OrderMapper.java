package com.devsuperior.dsdeliver.mapper;

import java.time.Instant;

import org.springframework.stereotype.Component;

import com.devsuperior.dsdeliver.dto.OrderDTO;
import com.devsuperior.dsdeliver.entities.Order;

@Component
public class OrderMapper {

	public Order toEntity (OrderDTO dto) {
		Order entity = new Order();
		entity.setId(dto.getId());
		entity.setAdress(dto.getAddress());
		entity.setLatitude(dto.getLatitude());
		entity.setLongitude(dto.getLongitude());
		entity.setMoment(Instant.now());
		entity.setStatus(dto.getStatus().PENDING);
		return entity;
	}
	
	
}
