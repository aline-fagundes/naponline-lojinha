package model;

import java.util.ArrayList;
import java.util.List;

public class Marca {

    private String nome;

    private List<Produto> produtosDaMarca = new ArrayList<>();

    public Marca() {

    }

    public Marca(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void alterarNome(String novoNome) {
        this.nome = novoNome;

        for(Produto produto : produtosDaMarca) {
            produto.setMarca(novoNome);
        }
    }

    public List<Produto> getProdutosDaMarca() {
        return produtosDaMarca;
    }

    public boolean registrarProduto(Produto produto) {
        if (contemProduto(produto.getNome())) {
            return false;
        }
        produtosDaMarca.add(produto);
        return true;
    }

    public boolean removerProduto(String nomeProduto) {
        for (int i = 0; i < produtosDaMarca.size(); i++) {
            if (produtosDaMarca.get(i).getNome().equalsIgnoreCase(nomeProduto)) {
                produtosDaMarca.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean contemProduto(String nomeProduto) {
        for (Produto produtoCadastrado : produtosDaMarca) {
            if (produtoCadastrado.getNome().equalsIgnoreCase(nomeProduto)) {
                return true;
            }
        }
        return false;
    }

    public int consultarQuantidadeProdutos() {
        return produtosDaMarca.size();
    }

    public List<Produto> buscarProdutosPorNome(String nome) {
        nome = nome.toLowerCase();
        List<Produto> produtosEncontrados = new ArrayList<>();

        for (Produto produto : produtosDaMarca) {
            String nomeProduto = produto.getNome().toLowerCase();
            if (nomeProduto.contains(nome)) {
                produtosEncontrados.add(produto);
            }
        }
        return produtosEncontrados;
    }
}
