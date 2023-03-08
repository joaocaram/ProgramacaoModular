// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.Test;

// public class ProdutoTest {

//     static Produto produto;
//     static Produto produtoInvalido;
//     static Data dataValidade;
//     static Data dataForaDaValidade;
    
//     @BeforeAll
//     static public void prepare(){
//         dataValidade = new Data().acrescentaDias(5);
//         dataForaDaValidade = new Data("01/03/2023");
//         produto = new Produto("Produto teste", 10, 10, 5, dataValidade);
//         produtoInvalido = new Produto("Produto invalido", 10, 1, 5, dataForaDaValidade);
//     }
    
//     @Test
//     public void produtoEstahAcimaDoEstoqueMinimo(){
//         assertFalse(produto.abaixoDoEstoqueMinimo());
//     }

//     @Test
//     public void produtoEstahAbaixoDoEstoqueMinimo(){
//         assertTrue(produtoInvalido.abaixoDoEstoqueMinimo());
//     }

//     @Test
//     public void produtoEstahNoEstoque(){
//         assertTrue(produtoInvalido.temEstoque());
//     }

//     @Test
//     public void produtoEstahNaValidade(){
//         assertTrue(produto.estahNaValidade());
//     }

//     @Test
//     public void produtoForaDaValidade(){
//         assertFalse(produtoInvalido.estahNaValidade());
//     }
// }
