package com.xulambs.pizzaria.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xulambs.pizzaria.model.Pedido;
import com.xulambs.pizzaria.model.Pizza;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    
    @PersistenceUnit
    EntityManagerFactory emf;

    @PostMapping("/criar")
    public Pedido criarPedido(){
        Pedido ped = new Pedido();
       
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(ped);
        manager.getTransaction().commit();

        return ped;
    }

    @GetMapping("/{idPedido}")
    public Pedido localizarPedido(@PathVariable int idPedido){
        
        EntityManager manager = emf.createEntityManager();
        Pedido ped = manager.find(Pedido.class, idPedido);
        
        return ped;
    }

    @PutMapping("/incluirPizza/{idPedido}")
    public Pedido incluirPizza(@PathVariable int idPedido, @RequestBody Pizza pizza){
        Pedido ped = localizarPedido(idPedido);
        ped.adicionar(pizza);
       
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(pizza);
        manager.merge(ped);
        manager.getTransaction().commit();
        
        return ped;
    }


}
