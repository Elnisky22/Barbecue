package controller;

import java.sql.SQLException;
import java.util.List;

import dao.RemoverBebidaJDBCDAO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Bebida;

/**
 * Classe controladora da tela Remover Bebida.
 * @author Darvyn Santos, Eduardo G. Pires, Leonardo Elnisky
 *
 */
public class RemoverBebidaController {

    @FXML private VBox vBoxRemoverBebida;
    @FXML private Label lblNome;
    @FXML private TextField txfNome;
    @FXML private HBox hBoxRemoverBebida;
    @FXML private Button btnCancelar;
    @FXML private Button btnConfirmar;
    
    private RemoverBebidaJDBCDAO daoRemoverBebida;

    /**
     * Método para retornar a tela anterior.
     * @param event
     */
    @FXML void btnCancelarOnAction(ActionEvent event){
    	closeWindow();
    }

    /**
     * Método para remover uma bebida do banco de dados.
     * @param event
     * @throws SQLException
     */
    @FXML void btnConfirmarOnAction(ActionEvent event) throws SQLException{
    	if (validarConfirmar()) {
    		daoRemoverBebida = new RemoverBebidaJDBCDAO();
    		daoRemoverBebida.delete(txfNome.getText());
    		
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Sucesso");
    		alert.setHeaderText("Bebida removida com sucesso");
    		alert.showAndWait();
    	
    		closeWindow();
    	}
    }

    /**
     * Método para fechar a tela atual.
     */
    private void closeWindow(){
    	Stage stage = (Stage) vBoxRemoverBebida.getScene().getWindow();
    	stage.close();
    }
    
    /**
     * Método para validar a bebida a ser removida. Caso exista algum problema, o usuário é informado.
     * @return true se tudo ok, false se algum problema.
     * @throws SQLException 
     */
    private boolean validarConfirmar() throws SQLException{
    	daoRemoverBebida = new RemoverBebidaJDBCDAO();
    	String errorMsg = "";
    	
    	if (txfNome.getText().isEmpty()) {
    		errorMsg = "Insira um nome para pesquisar. ";
    	}
    	
    	String nome = "";
    	if (!txfNome.getText().isEmpty()) {
    		nome = txfNome.getText();
    		
    		List<Bebida> remBebida = daoRemoverBebida.filter(nome);
    		if (remBebida.isEmpty()) {
    			errorMsg = "Não existe uma bebida com esse nome. ";
    		}
    	}
    	
    	
    	if (txfNome.getLength() > 30){
    		errorMsg = "Nome da bebida grande demais. ";
    	}
    	
    	if (errorMsg == ""){
    		return true;
    	}
    	else {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Erro");
    		alert.setHeaderText("Ocorreu um erro:");
    		alert.setContentText(errorMsg);
    		alert.showAndWait();
    		return false;
    	}
    }
}
