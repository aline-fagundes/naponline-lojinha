package model;

public class Comida extends Produto{

    private Integer gramas;

    public Comida() {

    }

    public Comida(String nome, String marca, double preco, Integer gramas) {
        super(nome, marca, preco);
        this.setGramas(gramas);
    }

    public Integer getGramas() {
        return gramas;
    }

    public void setGramas(Integer gramas) {
        this.gramas = gramas;
    }

    @Override
    public String exibirProduto() {
        return super.exibirProduto() + ", Gramas: " + gramas;
    }
}
