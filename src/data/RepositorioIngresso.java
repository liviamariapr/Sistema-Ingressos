package data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.IIngresso;
import model.Ingresso;

public class RepositorioIngresso implements IIngresso {

    private final String ARQUIVO_INGRESSOS = "Ingresso.ser";
    private final String ARQUIVO_ID_INGRESSOS = "idIngresso.dat";

    public RepositorioIngresso() {
        ArrayList<Ingresso> listaVaziaDeIngressos = new ArrayList<>();
        File arquivoIngressos = new File(ARQUIVO_INGRESSOS);

        // Se o arquivo "Ingresso.ser" ainda não existir cria um novo e escreve uma
        // lista vazia
        if (!arquivoIngressos.exists()) {
            try {

                arquivoIngressos.createNewFile();

                try (ObjectOutputStream escritorDeDados = new ObjectOutputStream(
                        new FileOutputStream(ARQUIVO_INGRESSOS))) {

                    escritorDeDados.writeObject(listaVaziaDeIngressos);
                }

            } catch (IOException e) {
                System.err.println("Erro ao criar arquivo de Ingressos!" + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Override
    public ArrayList<Ingresso> getAllIngressos() {
        ArrayList<Ingresso> IngressosCadastradas = new ArrayList<>();

        // Lê e retorna a lista de Ingressos armazenada no arquivo
        try (ObjectInputStream leitorDeObjetos = new ObjectInputStream(new FileInputStream(ARQUIVO_INGRESSOS))) {

            IngressosCadastradas = (ArrayList<Ingresso>) leitorDeObjetos.readObject();

        } catch (Exception e) {
            System.err.println("Erro no metodo getAllIngressos: " + e.getMessage());
            e.printStackTrace();
        }

        return IngressosCadastradas;
    }

    private int gerarNovoIdParaIngresso() {
        File arquivoDeIds = new File(ARQUIVO_ID_INGRESSOS);
        int idInicial = 1;
        int novoId = 0;
        int ultimoIdCadastrado = 0;

        try {
            // Se o arquivo nao existir, cria um novo, grava e retorna o id inicial
            if (!arquivoDeIds.exists()) {
                arquivoDeIds.createNewFile();
                gravarNovoId(idInicial, arquivoDeIds);
                return idInicial;
            }

            // Se o arquivo existir, le o ultimo id, incrementa, grava e retorna seu valor
            ultimoIdCadastrado = lerUltimoIdCadastrado(arquivoDeIds);
            novoId = ultimoIdCadastrado + 1;

            gravarNovoId(novoId, arquivoDeIds);

        } catch (Exception e) {
            System.err.println("Erro no metodo gerarNovoIdParaIngresso: " + e.getMessage());
            e.printStackTrace();
        }
        return novoId;
    }

    private int lerUltimoIdCadastrado(File arquivoDeIds) throws IOException {
        int ultimoIdCadastrado;

        try (DataInputStream leitorDeDados = new DataInputStream(new FileInputStream(arquivoDeIds))) {
            ultimoIdCadastrado = leitorDeDados.readInt();
        }

        return ultimoIdCadastrado;
    }

    private void gravarNovoId(int novoId, File arquivoDeIds) throws FileNotFoundException, IOException {

        try (DataOutputStream escritorDeDados = new DataOutputStream(new FileOutputStream(arquivoDeIds))) {

            escritorDeDados.writeInt(novoId);
        }
    }

    private void atualizarArquivoIngresso(ArrayList<Ingresso> IngressosCadastradas) {

        try (ObjectOutputStream escritorDeObjetos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_INGRESSOS))) {

            escritorDeObjetos.writeObject(IngressosCadastradas);

        } catch (Exception e) {
            System.err.println("Erro no método atualizarArquivoIngresso: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void createIngresso(Ingresso Ingresso) {
        Ingresso.setId(gerarNovoIdParaIngresso());

        ArrayList<Ingresso> IngressosCadastradas = getAllIngressos();
        IngressosCadastradas.add(Ingresso);

        atualizarArquivoIngresso(IngressosCadastradas);
    }

    @Override
    public Ingresso readIngresso(int id) {
        ArrayList<Ingresso> IngressosCadastradas = getAllIngressos();

        for (Ingresso d : IngressosCadastradas) {
            if (id == d.getId()) {
                return d;
            }
        }

        return null;
    }

    // Metodo auxiliar para descobrir indice(posicao) de um objeto na lista
    private int getIndiceDaIngresso(int id) {
        ArrayList<Ingresso> IngressosCadastradas = getAllIngressos();

        for (int i = 0; i < IngressosCadastradas.size(); i++) {
            if (IngressosCadastradas.get(i).getId() == id) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public void updateIngresso(Ingresso Ingresso) {
        ArrayList<Ingresso> IngressosCadastradas = getAllIngressos();
        int indiceDaIngresso = getIndiceDaIngresso(Ingresso.getId());

        if (indiceDaIngresso == -1) {
            throw new RuntimeException("Ingresso não encontrada.");
        }

        IngressosCadastradas.set(indiceDaIngresso, Ingresso);
        atualizarArquivoIngresso(IngressosCadastradas);
    }

    @Override
    public void deleteIngresso(Ingresso Ingresso) {
        ArrayList<Ingresso> IngressosCadastradas = getAllIngressos();
        int indiceDaIngresso = this.getIndiceDaIngresso(Ingresso.getId());

        if (indiceDaIngresso == -1) {
            throw new RuntimeException("Ingresso não encontrada.");
        }

        IngressosCadastradas.remove(indiceDaIngresso);
        atualizarArquivoIngresso(IngressosCadastradas);
    }
}