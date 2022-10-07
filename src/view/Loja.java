package view;

import program.Gerenciador;

import javax.swing.*;

public class Loja {

    private static Gerenciador gerenciador = new Gerenciador();

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

        int opcao = 0;

        do {
            opcao = Integer.parseInt(exibirMenu());
            switch (opcao) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Saindo...");
                    System.exit(0);
                    break;
                case 1:
                    gerenciador.cadastrarMarca();
                    break;
                case 2:
                    gerenciador.atualizarMarca();
                    break;
                case 3:
                    gerenciador.deletarMarca();
                    break;
                case 4:
                    gerenciador.buscarMarca();
                    break;
                case 5:
                    gerenciador.cadastrarProduto();
                    break;
                case 6:
                    gerenciador.atualizarProduto();
                    break;
                case 7:
                    gerenciador.deletarProduto();
                    break;
                case 8:
                   gerenciador.buscarProduto();
                    break;
                case 9:
                    gerenciador.consultarTodosOsProdutos();
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Por favor, escolha uma opção existente");
            }
        } while (opcao != 0);
    }
}

