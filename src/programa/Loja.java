package programa;

import gerenciador.GerenciadorDeMarcas;
import gerenciador.GerenciadorDeProdutos;

import javax.swing.*;

public class Loja {

    private static GerenciadorDeProdutos gerenciadorProdutos = new GerenciadorDeProdutos();
    private static GerenciadorDeMarcas gerenciadorMarcas = new GerenciadorDeMarcas();

    private static String exibirMenu() {
        return JOptionPane.showInputDialog(null, "Menu da lojinha\n"
                + "1- Cadastrar nova marca\n"
                + "2- Atualizar uma marca\n"
                + "3- Deletar uma marca\n"
                + "4- Buscar marca\n"
                + "5- Cadastrar novo produto\n"
                + "6- Atualizar preço de um produto\n"
                + "7- Deletar um produto\n"
                + "8- Buscar produto\n"
                + "9- Listar todos os produtos\n"
                + "0- Sair do Sistema!");
    }

    public static void main(String[] args) {

        String opcao;

        do {
            opcao = exibirMenu();
            switch (opcao) {
                case "0":
                    JOptionPane.showMessageDialog(null, "Saindo...");
                    System.exit(0);
                    break;
                case "1":
                    gerenciadorMarcas.cadastrarMarca();
                    break;
                case "2":
                    gerenciadorMarcas.atualizarMarca();
                    break;
                case "3":
                    gerenciadorMarcas.deletarMarca();
                    break;
                case "4":
                    gerenciadorMarcas.buscarMarca();
                    break;
                case "5":
                    gerenciadorProdutos.cadastrarProduto();
                    break;
                case "6":
                    gerenciadorProdutos.atualizarProduto();
                    break;
                case "7":
                    gerenciadorProdutos.deletarProduto();
                    break;
                case "8":
                    gerenciadorProdutos.buscarProduto();
                    break;
                case "9":
                    gerenciadorProdutos.consultarTodosOsProdutos();
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Por favor, escolha uma opção existente!");
            }
        } while (!opcao.isEmpty());
    }
}

