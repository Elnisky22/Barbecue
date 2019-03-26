package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 * Classe controladora da tela MenuBar.
 * @author Darvyn Santos, Eduardo G. Pires, Leonardo Elnisky
 *
 */
public class MenubarController{

	    @FXML private MenuBar menubar;
	    @FXML private Menu menuArquivo;
	    @FXML private Menu menuAjuda;
	    @FXML private MenuItem miSair;
	    @FXML private MenuItem miSobre;

	    /**
	     * Método para sair da aplicação.
	     * @param event
	     */
	    @FXML void sair(ActionEvent event) {
	    	Platform.exit();
	    }

	    /**
	     * Método que exite a tela Sobre da aplicação.
	     * @param event
	     */
	    @FXML void miSobreOnAction(ActionEvent event) {
	    	Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Sobre");
	    	alert.setHeaderText("Barbecue - Sistema de Gerenciamento de Venda e Estoque");
	    	alert.setContentText("Sistema desenvolvido para o gerenciamento de venda e estoques de uma churrascaria. © 2017, Pinguim tem Joelho");
	    	alert.showAndWait();
	    }
	    
	}
