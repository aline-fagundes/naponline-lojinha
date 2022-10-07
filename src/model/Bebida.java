package model;

public class Bebida extends Produto{

    private Integer mililitros;

    public Bebida() {

    }

    public Bebida(String nome, String marca, double preco, Integer mililitros) {
        super(nome, marca, preco);
        this.setMililitros(mililitros);
    }

    public Integer getMililitros() {
        return mililitros;
    }

    public void setMililitros(Integer mililitros) {
        this.mililitros = mililitros;
    }

    @Override
    public String exibirProduto() {
        return super.exibirProduto() + ", Mililitros: " + mililitros;
    }
}
