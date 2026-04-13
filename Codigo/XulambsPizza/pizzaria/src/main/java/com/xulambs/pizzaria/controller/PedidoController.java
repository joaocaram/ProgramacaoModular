package com.xulambs.pizzaria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.xulambs.pizzaria.model.Pedido;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

@Controller
public class PedidoController {
    
    @PostMapping("/pedidos/criar")
    public Pedido criarPedido(){
        Pedido ped = new Pedido();
        EntityManager manager = EntityManagerFactory.createEntityManager();
        
        manager.getTransaction().begin();
        manager.persist(ped);
        manager.getTransaction().commit();

        return ped;

    }
}
