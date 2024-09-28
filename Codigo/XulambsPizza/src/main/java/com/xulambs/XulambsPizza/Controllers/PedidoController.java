package com.xulambs.XulambsPizza.Controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xulambs.XulambsPizza.DTO.PedidoDTO;
import com.xulambs.XulambsPizza.Models.Pedido;
import com.xulambs.XulambsPizza.Models.Pizza;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.TypedQuery;

/** 
 * MIT License
 *
 * Copyright(c) 2022-24 João Caram <caram@pucminas.br>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

@Controller
public class PedidoController {
    
    @PersistenceUnit
    private EntityManagerFactory emf;

    @PostMapping("/pedidos")
    public @ResponseBody PedidoDTO criarPedido() {
        Pedido pedido = new Pedido();
        EntityManager manager = emf.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(pedido);
        manager.getTransaction().commit();
        return pedido.generateDTO();
    }

    @GetMapping("/pedidos")
    public @ResponseBody List<Pedido> todosOsPedidos() {
        EntityManager manager = emf.createEntityManager();
        TypedQuery<Pedido> consulta = manager.createQuery("Select P from Pedido P", Pedido.class);
        return consulta.getResultList();
    }

    @GetMapping("/pedidos/{id}")
    public @ResponseBody PedidoDTO buscarPedido(@PathVariable int id) {
        EntityManager manager = emf.createEntityManager();
        Pedido pedido = manager.find(Pedido.class, id);
        return pedido.generateDTO();
    }

    @PutMapping("/pedidos/adicionar/{id}")
    public @ResponseBody Pedido addPizza(@PathVariable int id, @RequestBody Pizza pizza) {
        EntityManager manager = emf.createEntityManager();
        Pedido ped = manager.find(Pedido.class, id);
        if(ped!=null){
            ped.adicionar(pizza);
            manager.getTransaction().begin();
            manager.persist(pizza);
            manager.persist(ped);
            manager.getTransaction().commit();
        }
        return ped;
    }

    @PutMapping("/pedidos/fechar/{id}")
    public @ResponseBody Pedido fecharPedido(@PathVariable int id) {
        EntityManager manager = emf.createEntityManager();
        Pedido pedido =  manager.find(Pedido.class, id);
        if(pedido!=null){
            pedido.fecharPedido();
            manager.getTransaction().begin();
            manager.persist(pedido);
            manager.getTransaction().commit();
        }
        return pedido;
    }

   
}

