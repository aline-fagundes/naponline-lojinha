package program;

import model.Bebida;
import model.Comida;
import model.Marca;
import model.Produto;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Gerenciador {

    private static final Map<String, Marca> marcas = new HashMap<>();

    public void cadastrarMarca() {
        String nomeMarca = JOptionPane.showInputDialog(null, "Informe o nome da marca que deseja cadastrar");

        if (marcas.containsKey(nomeMarca)) {
            JOptionPane.showMessageDialog(null, "Já existe uma marca com esse nome!");
            return;
        }
        Marca marca = new Marca(nomeMarca);
        marcas.put(nomeMarca, marca);
        JOptionPane.showMessageDialog(null, "Marca cadastrada com sucesso!");
    }

    public void cadastrarProduto() {
        String nomeMarca = JOptionPane.showInputDialog(null, "Informe a marca do novo produto");

        if (!marcas.containsKey(nomeMarca)) {
            JOptionPane.showMessageDialog(null, "Essa marca não existe! Cadastre-a primeiro para, então, criar o produto!");
            return;
        }

        String nome = JOptionPane.showInputDialog(null, "Informe o nome do produto que deseja cadastrar");
        double preco = (Double.parseDouble(JOptionPane.showInputDialog(null, "Informe o preço do produto (apenas números)")));

        Marca marcaDoProduto = marcas.get(nomeMarca);
        if (marcaDoProduto.contemProduto(nome)) {
            JOptionPane.showMessageDialog(null, "Não é possível cadastrar produto repetido!");
            return;
        }

        String tipoProduto = JOptionPane.showInputDialog(null, "Informe se o produto é uma comida ou bebida");
        if (tipoProduto.equalsIgnoreCase("Comida")) {

            Integer peso = (Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o peso do produto em gramas (apenas números)")));

            Comida produto = new Comida(nome, nomeMarca, preco, peso);
            marcaDoProduto.registrarProduto(produto);
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");

        } else if (tipoProduto.equalsIgnoreCase("Bebida")) {

            Integer peso = (Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o peso do produto em mililitros (apenas números)")));

            Bebida produto = new Bebida(nome, nomeMarca, preco, peso);
            marcaDoProduto.registrarProduto(produto);
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");

        } else {
            JOptionPane.showMessageDialog(null, "Opção inexistente! Você deve informar se o produto é uma comida ou uma bebida!");
        }
    }

    public void atualizarMarca() {
        String nomeMarca = JOptionPane.showInputDialog(null, "Informe o nome da marca que deseja atualizar");

        if (!marcas.containsKey(nomeMarca)) {
            JOptionPane.showMessageDialog(null, "Essa marca não existe!");
            return;
        }

        String novoNomeMarca = JOptionPane.showInputDialog(null, "Informe um novo nome para essa marca");

        if (marcas.containsKey(novoNomeMarca)) {
            JOptionPane.showMessageDialog(null, "Já existe uma marca com esse nome!");
            return;
        }

        Marca marca = marcas.get(nomeMarca);
        marca.alterarNome(novoNomeMarca);
        marcas.put(novoNomeMarca, marca);
        marcas.remove(nomeMarca);

        JOptionPane.showMessageDialog(null, "Marca alterada com sucesso!");
    }

    public void atualizarProduto() {
        String nomeMarca = JOptionPane.showInputDialog(null, "Informe a marca do produto que deseja atualizar");

        if (!marcas.containsKey(nomeMarca)) {
            JOptionPane.showMessageDialog(null, "Essa marca não existe!");
            return;
        }
        Marca marcaProduto = marcas.get(nomeMarca);

        String nomeProduto = JOptionPane.showInputDialog(null, "Informe o nome do produto que deseja atualizar");

        if (!marcaProduto.contemProduto(nomeProduto)) {
            JOptionPane.showMessageDialog(null, "Esse produto não existe!");
            return;
        }

        double preco = (Double.parseDouble(JOptionPane.showInputDialog(null, "Informe o novo preço do produto (apenas números)")));

        //TODO: IMPLEMENTAR LÓGICA PARA ALTERAR O PREÇO DO PRODUTO!

        JOptionPane.showMessageDialog(null, "Preço do produto atualizado com sucesso!");
    }

    public void deletarMarca() {
        String nomeMarca = JOptionPane.showInputDialog(null, "Informe o nome da marca que deseja excluir");

        if (!marcas.containsKey(nomeMarca)) {
            JOptionPane.showMessageDialog(null, "Essa marca não existe!");
            return;
        }
        Marca marcaEncontrada = marcas.get(nomeMarca);
        if (marcaEncontrada.consultarQuantidadeProdutos() != 0) {
            JOptionPane.showMessageDialog(null, "Não é possível excluir uma marca com produtos cadastrados!");
            return;
        }

        marcas.remove(nomeMarca);
        JOptionPane.showMessageDialog(null, "Marca excluída com sucesso!");
    }

    public void deletarProduto() {
        String nomeMarca = JOptionPane.showInputDialog(null, "Informe a marca do produto que deseja excluir");

        if (!marcas.containsKey(nomeMarca)) {
            JOptionPane.showMessageDialog(null, "Essa marca não existe!");
            return;
        }

        String nomeProduto = JOptionPane.showInputDialog(null, "Informe o nome do produto que deseja excluir");

        Marca marcaDoProduto = marcas.get(nomeMarca);
        if (!marcaDoProduto.contemProduto(nomeProduto)) {
            JOptionPane.showMessageDialog(null, "Esse produto não existe!");
            return;
        }
        marcaDoProduto.removerProduto(nomeProduto);
        JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");
    }

    public void buscarMarca() {
        String nomeMarca = JOptionPane.showInputDialog(null, "Informe o nome da marca que deseja buscar");

        if (!marcas.containsKey(nomeMarca)) {
            JOptionPane.showMessageDialog(null, "Essa marca não existe!");
            return;
        }
        Marca marcaEncontrada = marcas.get(nomeMarca);
        List<Produto> produtosDaMarca = marcaEncontrada.getProdutosDaMarca();

        StringBuilder produtos = new StringBuilder();
        for (Produto produto : produtosDaMarca) {
            produtos.append(produto.exibirProduto()).append("; \n");
        }
        JOptionPane.showMessageDialog(null, produtos.toString());
    }

    public void buscarProduto() {
        String nomeProduto = JOptionPane.showInputDialog(null, "Informe o nome do produto que deseja buscar");

        List<Produto> produtosEncontrados = new ArrayList<>();
        for (Marca marca : marcas.values()) {
            List<Produto> produtosDessaMarca = marca.buscarProdutosPorNome(nomeProduto);
            produtosEncontrados.addAll(produtosDessaMarca);
        }

        StringBuilder produtos = new StringBuilder();
        for (Produto produto : produtosEncontrados) {
            produtos.append(produto.exibirProduto()).append("; \n");
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

        JOptionPane.showMessageDialog(null, produtos.toString());
    }
}
