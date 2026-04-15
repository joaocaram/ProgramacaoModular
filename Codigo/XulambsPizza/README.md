# Xulambs Pizza

Xulambs Pizza é uma pizzaria que será inaugurada em breve, com grandes expectativas. Inicialmente, o negócio precisa automatizar o cálculo do preço de venda das **pizzas**. O modelo de vendas segue uma lógica simplificada:

1. Não existem pizzas de sabores pré-definidos.
1. A pizza tem preço inicial de R$29.
1. A pizza pode ter até 8 ingredientes adicionais.
1. Os adicionais têm custo fixo: cada um custa R$5.

O **Sistema Xulambs Pizza** precisa permitir registrar vendas de pizzas isoladas e emitir um cupom de venda (relatório) para cada uma, contendo sua descrição e valores a serem pagos.

As pizzas devem ser incluídas em pedidos. Um **pedido** pode receber novas pizzas até que seja fechado. Ele deve ser identificado por um número único e gerar um cupom de venda que inclui sua data e o valor total a ser pago pelo cliente. 