
package controller;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TelaClienteController {

    @FXML
    private ImageView imageDeletarFesta;

    @FXML
    private Button buttomSair;

    @FXML
    private ImageView imageInformacoesIngressos;

    @FXML
    private ImageView imageVerIngressosAVenda;

    @FXML
    private TableColumn<?, ?> tableColunmnIDFesta;

    @FXML
    private TableColumn<?, ?> tableColunmnNomeFesta;

    @FXML
    private TableView<?> tableFesta;

    @FXML
    public void initialize() {
        // Inicialização da tela de cliente
    }


    @FXML
    void handleVerIngressosAVenda(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaIngressosDisponiveis.fxml"));
        AnchorPane page = loader.load();
        
        Stage stage = new Stage();
        stage.setTitle("Ingressos Disponíveis");
        stage.setScene(new Scene(page));
        stage.show();
        
        // Fecha a tela de cliente
        Stage atual = (Stage) imageVerIngressosAVenda.getScene().getWindow();
        atual.close();
    }

    @FXML
    void handleimageDeletarFesta(MouseEvent event) {

    }

    @FXML
    void handleimageInformacoesIngressos(MouseEvent event) {

    }

    @FXML
    void handleSair(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/TelaInicialSGVI.fxml"));
        Parent root = loader.load();
        
        Stage stage = new Stage();
        stage.setTitle("Sistema de Ingressos");
        stage.setScene(new Scene(root));
        stage.show();
        
        // Fecha a tela de administrador
        Stage atual = (Stage) buttomSair.getScene().getWindow();
        atual.close();
    }

}