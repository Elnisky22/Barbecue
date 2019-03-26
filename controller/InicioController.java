package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Classe controladora da tela Início.
 * @author Darvyn Santos, Eduardo G. Pires, Leonardo Elnisky
 *
 */
public class InicioController implements Initializable{

    @FXML private VBox vBoxInicio;
    @FXML private ImageView imgLogo;
    @FXML private Button btnAbrirConta;
    @FXML private Button btnGerenciarMesas;
    @FXML private Button btnFecharConta;
    @FXML private Button btnGerenciarBebidas;
    @FXML private HBox hBoxVbox;
    @FXML private Button btnSair;
    @FXML private Label lblCopyright;
    
    /**
     * Método para iniciar a tela início.
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	/**
	 * Método para iniciar a tela Abrir Conta.
	 * @param event
	 */
    @FXML void btnAbrirContaOnAction(ActionEvent event){
    	try {
    		VBox vbox = FXMLLoader.load(getClass().getResource("/view/AbrirConta.fxml"));
    		BorderPane bpane = Main.getRoot();
    		bpane.setCenter(vbox);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }

    /**
     * Método para iniciar a tela Gerenciar Mesas.
     * @param event
     */
    @FXML void btnGerenciarMesasOnAction(ActionEvent event){
    	try {
    		VBox vbox = FXMLLoader.load(getClass().getResource("/view/GerenciarMesas.fxml"));
    		BorderPane bpane = Main.getRoot();
    		bpane.setCenter(vbox);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * Método para iniciar a tela Fechar Conta.
     * @param event
     */
    @FXML void btnFecharContaOnAction(ActionEvent event){
    	try {
    		VBox vbox = FXMLLoader.load(getClass().getResource("/view/FecharConta.fxml"));
    		BorderPane bpane = Main.getRoot();
    		bpane.setCenter(vbox);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * Método para iniciar a tela Gerenciar Bebidas.
     * @param event
     */
    @FXML void btnGerenciarBebidasOnAction(ActionEvent event){
       	try {
    		VBox vbox = FXMLLoader.load(getClass().getResource("/view/GerenciarBebidas.fxml"));
    		BorderPane bpane = Main.getRoot();
    		bpane.setCenter(vbox);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    @FXML void btnSairOnAction(ActionEvent event){
    	Platform.exit();
    }
}
