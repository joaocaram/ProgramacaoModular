# Xulambs Pizza

## Versão 0.1 - Vendendo Pizzas

Xulambs Pizza é uma pizzaria que será inaugurada em breve, com grandes expectativas. Inicialmente, o negócio precisa automatizar o cálculo do preço de venda das pizzas. O modelo de vendas segue uma lógica simplificada:

1. Não existem pizzas de sabores pré-definidos.
1. A pizza tem preço inicial de R$29.
1. A pizza pode ter até 8 ingredientes adicionais.
1. Os adicionais têm custo fixo: cada um custa R$5.

O **Sistema Xulambs Pizza** precisa permitir registrar vendas de pizzas isoladas e emitir um cupom de venda (relatório) para cada uma, contendo sua descrição e valores a serem pagos.

## Versão 0.2 - A pizzaria e seus pedidos

A pizzaria percebeu que é melhor agrupar as vendas de pizzas em **pedidos**. Foram levantados os requisitos:

1. Um pedido deve ter um identificador único.
1. Um pedido deve ter sua data armazenada.
1. Um pedido aceitará novos itens até que seja fechado.
1. O relatório de um pedido deve mostrar a descrição de cada uma das pizzas, detalhadamente, e o valor total do pedido.

## Versão 0.3 - Borda recheada

Para atender um desejo de muitos de seus clientes, a Xulambs Pizza incluiu uma opção no cardápio: **bordas recheadas**. Uma pizza poderá ter os seguintes tipos de bordas:

1. Borda tradicional: sem acréscimo.
1. Borda de cheddar: acréscimo de R$ 10.
1. Borda de chocolate: acréscimo de R$ 8.
1. Borda de requeijão: acréscimo de R$ 7.