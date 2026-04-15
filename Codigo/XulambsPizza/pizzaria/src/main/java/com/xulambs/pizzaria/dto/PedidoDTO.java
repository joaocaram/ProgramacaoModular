package com.xulambs.pizzaria.dto;

import java.time.LocalDate;

public record PedidoDTO(int numero, LocalDate data, double valor) {
    
}
