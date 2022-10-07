package modelo;

public abstract class Produto {

    private String nome;
    private String marca;
    private double preco;

    public Produto() {

    }

    public Produto(String nome, String nomeMarca, double preco) {
        this.nome = nome;
        this.preco = preco;
        this.marca = nomeMarca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String nomeMarca) {
        this.marca = nomeMarca;
    }

    public String exibirProduto() {
        return "Produto: " + nome + ", Marca: " + marca + ", Preço: R$" + String.format("%.2f", preco);
    }
}
