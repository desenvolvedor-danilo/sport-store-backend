package com.dkmo.integrationnextjs.dto;


import lombok.Builder;

@Builder
public record ShoppingDto(int quantity, double totalPrice) {
    
}
