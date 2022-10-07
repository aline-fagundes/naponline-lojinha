package gerenciador;

import modelo.Marca;
import modelo.Produto;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GerenciadorDeMarcas {

    public static final Map<String, Marca> marcas = new HashMap<>();
    public Validacao validador = new Validacao();

    public void cadastrarMarca() {
        String nomeMarca = JOptionPane.showInputDialog(null, "Informe o nome da marca que deseja cadastrar");

        if(!validador.contemTexto(nomeMarca)) {
            return;
        }

        if (marcas.containsKey(nomeMarca)) {
            JOptionPane.showMessageDialog(null, "Já existe uma marca com esse nome!");
            return;
        }

        Marca marca = new Marca(nomeMarca);
        marcas.put(nomeMarca, marca);
        JOptionPane.showMessageDialog(null, "Marca cadastrada com sucesso!");
    }


    public void atualizarMarca() {
        String nomeMarca = JOptionPane.showInputDialog(null, "Informe o nome da marca que deseja atualizar");

        if(!validador.contemTexto(nomeMarca)) {
            return;
        }

        if (!marcas.containsKey(nomeMarca)) {
            JOptionPane.showMessageDialog(null, "Essa marca não existe!");
            return;
        }

        String novoNomeMarca = JOptionPane.showInputDialog(null, "Informe um novo nome para essa marca");

        if(!validador.contemTexto(novoNomeMarca)) {
            return;
        }

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

    public void deletarMarca() {
        String nomeMarca = JOptionPane.showInputDialog(null, "Informe o nome da marca que deseja excluir");

        if(!validador.contemTexto(nomeMarca)) {
            return;
        }

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

    public void buscarMarca() {
        String nomeMarca = JOptionPane.showInputDialog(null, "Informe o nome da marca que deseja buscar");

        if(!validador.contemTexto(nomeMarca)) {
            return;
        }

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

        if (produtos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Marca: " + nomeMarca + "\n Itens: Não há produtos dessa marca cadastrados!");
            return;
        }

        JOptionPane.showMessageDialog(null, "Marca: " + nomeMarca + "\n Itens: \n" + produtos.toString());
    }
}
