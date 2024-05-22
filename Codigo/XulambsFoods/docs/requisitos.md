# Documentação de requisitos do Xulambs Foods 🍔🍕

1. No momento, o cardápio tem apenas duas comidas:
    - Sanduíche;
    - Pizza.

1.  As comidas são vendidas em uma versão padrão e podem ser personalizadas com adicionais.

1. É cobrado um valor fixo por adicional.
    - Sanduíche:
      - Preço inicial de R$15.
      - Até 5 ingredientes adicionais.
      - Cada adicional custa R$2.

    - Pizza:
      - Preço inicial de R$29.
      - Até 8 ingredientes adicionais.
      - Cada adicional custa R$4.
    
1. As vendas devem ser agrupadas em pedidos.
    - Um pedido deve ter um identificador único e sua data armazenada.
    - Um pedido pode conter até 10 comidas. 
    - O relatório de um pedido deve mostrar a descrição de cada uma das comidas, detalhadamente, e o valor total do pedido.

1. As pizzas passarão a ser vendidas com a opção de borda recheada. Uma pizza com borda recheada sofre acréscimo de R$5,50.

1. Procurando incentivar a adição de ingredientes na Pizza, a partir do 6º ingredientes será concedido um desconto de 50% no seu valor.

1. Cada pedido deverá ser registrado para um cliente.

1. Um relatório de cliente inclui seu identificador e todos os seus pedidos.

1. O restaurante implementará um **programa de fidelidade** para seus clientes.

    1. Clientes sem fidelidade são Xulambs Júnior. Quando atingem determinadas metas de consumo, os clientes podem obter dois tipos de vantagem: Xulambs Pleno e Xulambs Sênior.
  
    1. _Xulambs Pleno_ são aqueles que atingem pelo menos `10` pedidos feitos no restaurante e mantém valor médio de pelo menos `R$26`. Ganham um desconto fixo de `10%` em cada pedido feito enquanto mantiverem este status.
  
    1. _Xulambs Sênior_ são os clientes que já fizeram pelo menos `20` pedidos no restaurante, mantendo um valor médio de pelo menos `R$35`. Ganham um desconto fixo de `15%` em qualquer pedido e um desconto adicional de `R$10` a cada `5` pedidos no mês.

1. A categoria do cliente é definida no primeiro dia útil de cada mês.





