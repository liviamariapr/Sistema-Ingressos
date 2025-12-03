
package controller;

import java.io.IOException;

import data.RepositorioCadastro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Administrador;
import model.Cadastro;
import model.Cliente;

public class TelaLoginController {
    
    RepositorioCadastro repositorioCadastro = new RepositorioCadastro();


    @FXML
    private Button buttonAcessar;

    @FXML
    private TextField textFieldEmail;

    @FXML
    private TextField textFieldSenha;
    @FXML
    private Label labelAcessoInvalido;

    @FXML
    void handleAcessarApp(ActionEvent event) {
            
        String email = textFieldEmail.getText();
        String senha = textFieldSenha.getText();

        if (email.isEmpty() || senha.isEmpty()) {
            labelAcessoInvalido.setText("Preencha todos os campos");
            textFieldEmail.requestFocus();
            return;
        }

        Cadastro cadastro = repositorioCadastro.readCadastro(email, senha);

        if (cadastro == null) {
            labelAcessoInvalido.setText("Email ou senha incorretos");
            return;
        }
        else {
            try {
            if (cadastro instanceof Administrador) {
                abrirTela("/View/TelaAdministrador.fxml");
            } else if (cadastro instanceof Cliente) {
                abrirTela("/View/TelaCliente.fxml");
            }

        } catch (IOException ex) {
            System.err.println("Erro ao fazer login: " + ex.getMessage());
            ex.printStackTrace();
        }
        }

    }

    @FXML
    void handleDigitarEmail(ActionEvent event) {

    }

    @FXML
    void handleDigitarSenha(ActionEvent event) {

    }



    private void abrirTela(String caminhoFXML) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(caminhoFXML));
        AnchorPane page = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Sistema");
        stage.setScene(new Scene(page));
        Image imagemIconeDaJanela = new Image("/icons/icone_logo.png");
        stage.getIcons().add(imagemIconeDaJanela);
        stage.show();

        Stage atual = (Stage) buttonAcessar.getScene().getWindow();
        atual.close();
    }

}
