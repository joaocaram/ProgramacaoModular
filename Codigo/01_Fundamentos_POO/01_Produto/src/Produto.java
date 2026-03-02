public class Produto {
    String descricao;
    double precoCusto;
    double margemLucro;

    /**
     * Cria um produto com seu preço de custo e a margem de lucro.
     * A margem de lucro deve estar entre 10 e 50%, sendo recebida
     * como um número nesta faixa de valores. O preço de custo deve
     * ser superior a 0.
     * @param desc  "Nome" do produto. Sem validação.
     * @param precoCusto Valor de custo do produto. Deve ser maior que 0.
     * @param margemLucro Margem de lucro, entre 10 e 50%, nesta faixa (p.ex, 16,5).
     */
    public Produto(String desc, double precoCusto, double margemLucro){
        descricao = desc;
        this.precoCusto = 0.01;
        this.margemLucro = 0.10;
        if(precoCusto > 0.01)
            this.precoCusto = precoCusto;
        if(margemLucro > 10 && margemLucro <= 50)
            this.margemLucro = margemLucro/100;
    }

    /**
     * Calcula o valor de venda do produto, que é baseado em seu preço
     * de custo e sua margem de lucro.
     * @return Double positivo com o valor de venda.
     */
    public double valorVenda(){
        return precoCusto * (1 + margemLucro);
    }

    /**
     * Retorna uma string com o nome do produto e seu valor de venda com
     * 2 casas decimais.
     * @return uma string com o nome do produto e seu valor de venda com
     * 2 casas decimais.
     */
    public String toString(){
        return String.format("%s: R$ %.2f",descricao, valorVenda());
    }
    

}
