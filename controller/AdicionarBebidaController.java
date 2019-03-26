package controller;

import model.Bebida;
import dao.AdicionarBebidaJDBCDAO;

import java.sql.SQLException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

/**
 * Classe controladora da tela Adicionarbebida.
 * @author Darvyn Santos, Eduardo G. Pires, Leonardo Elnisky
 *
 */
public class AdicionarBebidaController{

    @FXML private VBox vBoxAdicionarBebida;
    @FXML private Label lblNome;
    @FXML private TextField txfNome;
    @FXML private Label lblQuantidade;
    @FXML private TextField txfQuantidadeEstoque;
    @FXML private Label lblValor;
    @FXML private TextField txfValor;
    @FXML private HBox hBoxAdicionarBebida;
    @FXML private Button btnCancelar;
    @FXML private Button btnConfirmar;
    
    private Bebida novaBebida;
    private AdicionarBebidaJDBCDAO dao;
    
    /**
     * Método para inicializar a tela de Adicionar Bebida.
     * @throws SQLException
     */
    @FXML private void initialize() throws SQLException{
    	//Listener do TextField QuantidadeEstoque
    	txfQuantidadeEstoque.textProperty().addListener(new ChangeListener<String>() {
    	    @Override
    	    public void changed(ObservableValue<? extends String> observable, String oldValue, 
    	        String newValue) {
    	        if (!newValue.matches("\\d*")) {
    	            txfQuantidadeEstoque.setText(newValue.replaceAll("[^\\d]", ""));
    	        }
    	    }
    	});
    }
    
    /**
     * Método para cancelar a adição e retornar a tela anterior.
     * @param event
     */
    @FXML void btnCancelarOnAction(ActionEvent event){
    	closeWindow();
    }

    /**
     * Método para gerar um objeto Bebida e enviar ao banco de dados.
     * @param event
     * @throws SQLException
     */
    @FXML void btnConfirmarOnAction(ActionEvent event) throws SQLException{
    	if (verificaConfirmar()){
    		novaBebida = new Bebida();
    		dao = new AdicionarBebidaJDBCDAO();
    	
    		novaBebida.setNome(txfNome.getText());
    		novaBebida.setQuantidadeBebida(Integer.parseInt(txfQuantidadeEstoque.getText()));
    		novaBebida.setValor(Double.parseDouble(txfValor.getText()));
    		dao.incluir(novaBebida);
    		
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Sucesso");
    		alert.setHeaderText("Bebida adicionada com sucesso");
    		alert.showAndWait();
    	
    		closeWindow();
    	}
    }

    /**
     * Método para fechar a tela de Adicionar Bebida.
     */
    private void closeWindow(){
       	Stage stage = (Stage) vBoxAdicionarBebida.getScene().getWindow();
    	stage.close();
    }
    
    /**
     * Método para verificar se todos os dados estão corretos para adicionar uma bebida ao banco. Caso não esteja, avisa ao usuário qual o problema.
     * @return true se tudo ok, false se algum problema.
     */
    private boolean verificaConfirmar(){
    	String errorMsg = "";
    	
    	if (txfNome.getText().isEmpty()) {
    		errorMsg += "Informe um nome. ";
    	}
    	if (txfNome.getLength() > 30){
    		errorMsg += "Nome muito grande. ";
    	}
    	
    	if (txfQuantidadeEstoque.getText().isEmpty()) {
    		errorMsg += "Informe a quantia em estoque. ";
    	}
    	if (txfQuantidadeEstoque.getLength() > 3){
    		errorMsg += "Quantidade de estoque muito alta. ";
    	}
    	
    	if (txfValor.getText().isEmpty()) {
    		errorMsg += "Informe um valor. ";
    	}
    	
    	if (errorMsg == "") {
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
