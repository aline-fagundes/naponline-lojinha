package gerenciador;

import modelo.Bebida;
import modelo.Comida;
import modelo.Marca;
import modelo.Produto;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static gerenciador.GerenciadorDeMarcas.marcas;

public class GerenciadorDeProdutos {

    public Validacao validador = new Validacao();

    public void cadastrarProduto() {
        String nomeMarca = JOptionPane.showInputDialog(null, "Informe a marca do novo produto");

        if (!validador.contemTexto(nomeMarca)) {
            return;
        }

        if (!marcas.containsKey(nomeMarca)) {
            JOptionPane.showMessageDialog(null, "Essa marca não existe! Cadastre-a primeiro para, então, criar o produto!");
            return;
        }

        String nome = JOptionPane.showInputDialog(null, "Informe o nome do produto que deseja cadastrar");

        if (!validador.contemTexto(nome)) {
            return;
        }

        double preco;
        try {
            preco = Double.parseDouble(JOptionPane.showInputDialog(null, "Informe o preço do produto (apenas números)"));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Por favor, digite um número!");
            return;
        }

        Marca marcaDoProduto = marcas.get(nomeMarca);
        if (marcaDoProduto.contemProduto(nome)) {
            JOptionPane.showMessageDialog(null, "Não é possível cadastrar produto repetido!");
            return;
        }

        String tipoProduto = JOptionPane.showInputDialog(null, "Informe se o produto é uma comida ou bebida");

        if (!validador.contemTexto(tipoProduto)) {
            return;
        }

        if (tipoProduto.equalsIgnoreCase("Comida")) {

            int peso;
            try {
                peso = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o peso do produto em gramas (apenas números)"));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Por favor, digite um número!");
                return;
            }

            Comida produto = new Comida(nome, nomeMarca, preco, peso);
            marcaDoProduto.registrarProduto(produto);
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");

        } else if (tipoProduto.equalsIgnoreCase("Bebida")) {

            int peso;
            try {
                peso = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o peso do produto em mililitros (apenas números)"));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Por favor, digite um número!");
                return;
            }

            Bebida produto = new Bebida(nome, nomeMarca, preco, peso);
            marcaDoProduto.registrarProduto(produto);
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");

        } else {
            JOptionPane.showMessageDialog(null, "Opção inexistente! Você deve informar se o produto é uma comida ou uma bebida!");
        }
    }

    public void atualizarProduto() {
        String nomeMarca = JOptionPane.showInputDialog(null, "Informe a marca do produto que deseja atualizar");

        if (!validador.contemTexto(nomeMarca)) {
            return;
        }

        if (!marcas.containsKey(nomeMarca)) {
            JOptionPane.showMessageDialog(null, "Essa marca não existe!");
            return;
        }
        Marca marcaProduto = marcas.get(nomeMarca);

        String nomeProduto = JOptionPane.showInputDialog(null, "Informe o nome do produto que deseja atualizar");

        if (!validador.contemTexto(nomeProduto)) {
            return;
        }

        if (!marcaProduto.contemProduto(nomeProduto)) {
            JOptionPane.showMessageDialog(null, "Esse produto não existe!");
            return;
        }

        List<Produto> produtosDaMarca = marcaProduto.getProdutosDaMarca();

        for (Produto produto : produtosDaMarca) {
            if (produto.getNome().equalsIgnoreCase(nomeProduto)) {

                double preco;
                try {
                    preco = Double.parseDouble(JOptionPane.showInputDialog(null, "Informe o novo preço do produto (apenas números)"));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, digite um número!");
                    return;
                }

                produto.setPreco(preco);

                JOptionPane.showMessageDialog(null, "Preço do produto atualizado com sucesso!");

                return;
            }
        }
    }

    public void deletarProduto() {
        String nomeMarca = JOptionPane.showInputDialog(null, "Informe a marca do produto que deseja excluir");

        if (!validador.contemTexto(nomeMarca)) {
            return;
        }

        if (!marcas.containsKey(nomeMarca)) {
            JOptionPane.showMessageDialog(null, "Essa marca não existe!");
            return;
        }

        String nomeProduto = JOptionPane.showInputDialog(null, "Informe o nome do produto que deseja excluir");

        if (!validador.contemTexto(nomeProduto)) {
            return;
        }

        Marca marcaDoProduto = marcas.get(nomeMarca);
        if (!marcaDoProduto.contemProduto(nomeProduto)) {
            JOptionPane.showMessageDialog(null, "Esse produto não existe!");
            return;
        }
        marcaDoProduto.removerProduto(nomeProduto);
        JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");
    }

    public void buscarProduto() {
        String nomeProduto = JOptionPane.showInputDialog(null, "Informe o nome do produto que deseja buscar");

        if (!validador.contemTexto(nomeProduto)) {
            return;
        }

        List<Produto> produtosEncontrados = new ArrayList<>();
        for (Marca marca : marcas.values()) {
            List<Produto> produtosDessaMarca = marca.buscarProdutosPorNome(nomeProduto);
            produtosEncontrados.addAll(produtosDessaMarca);
        }

        StringBuilder produtos = new StringBuilder();
        for (Produto produto : produtosEncontrados) {
            produtos.append(produto.exibirProduto()).append("; \n");
        }

        if (produtos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Produto não encontrado!");
            return;
        }

        JOptionPane.showMessageDialog(null, produtos.toString());
    }

    public void consultarTodosOsProdutos() {
        List<Produto> todosOsProdutos = new ArrayList<>();

        for (Marca marca : marcas.values()) {
            List<Produto> produtosDessaMarca = marca.getProdutosDaMarca();
            todosOsProdutos.addAll(produtosDessaMarca);
        }

        StringBuilder produtos = new StringBuilder();
        for (Produto produto : todosOsProdutos) {
            produtos.append(produto.exibirProduto()).append("; \n");
        }

        if (produtos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há produtos cadastrados!");
            return;
        }

        JOptionPane.showMessageDialog(null, produtos.toString());
    }
}
