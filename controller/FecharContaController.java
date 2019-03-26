package controller;

import application.Main;
import dao.FecharContaJDBCDAO;
import model.Conta;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FecharContaController{

    @FXML private VBox vBoxFecharConta;
    @FXML private HBox hBoxFecharConta;
    @FXML private TextField txfNumeroMesa;
    @FXML private Button btnPesquisar;
    @FXML private Label lblOValorEh;
    @FXML private Label lblValor;
    @FXML private HBox hBoxButtons;
    @FXML private Button btnVoltar;
    @FXML private Button btnConfirmar;
    
    private FecharContaJDBCDAO daoFecharConta;
    
    @FXML void initialize() throws SQLException{
    	//Listener do TextField txfNumeroMesa
    	txfNumeroMesa.textProperty().addListener(new ChangeListener<String>() {
    	    @Override
    	    public void changed(ObservableValue<? extends String> observable, String oldValue, 
    	        String newValue) {
    	        if (!newValue.matches("\\d*")) {
    	            txfNumeroMesa.setText(newValue.replaceAll("[^\\d]", ""));
    	        }
    	    }
    	});
    }
    
    @FXML void btnPesquisarOnAction(ActionEvent event) throws SQLException{
    	if (validarPesquisa()){
    		daoFecharConta = new FecharContaJDBCDAO();
    		int mesaId = Integer.parseInt(txfNumeroMesa.getText());
    		List<Conta> contas = daoFecharConta.filter(mesaId);
    		lblValor.setText("R$ " + contas.get(0).getValor());
    	}
    }
    
    @FXML void btnConfirmarOnAction(ActionEvent event) throws SQLException{
    	if (validarConfirmar()){
    		daoFecharConta = new FecharContaJDBCDAO();
    		int mesaId = Integer.parseInt(txfNumeroMesa.getText());
    		List<Conta> contas = daoFecharConta.filter(mesaId);
    		daoFecharConta.excluir(contas.get(0));
    	
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Sucesso");
    		alert.setHeaderText("Conta fechada com sucesso.");
    		alert.setContentText("Mesa liberada.");
    		alert.showAndWait();
    	
    		try {
    			VBox vbox = FXMLLoader.load(getClass().getResource("/view/Inicio.fxml"));
    			BorderPane bpane = Main.getRoot();
    			bpane.setCenter(vbox);
    		} catch (IOException e){
    			e.printStackTrace();
    		}
    	}
    }

    @FXML void btnVoltarOnAction(ActionEvent event){
    	try {
    		VBox vbox = FXMLLoader.load(getClass().getResource("/view/Inicio.fxml"));
    		BorderPane bpane = Main.getRoot();
    		bpane.setCenter(vbox);
    	} catch (IOException e){
    		e.printStackTrace();
    	}
    }

    private boolean validarPesquisa() throws SQLException {
    	String errorMsg = "";
    	
    	daoFecharConta = new FecharContaJDBCDAO();
		int mesaId = Integer.parseInt(txfNumeroMesa.getText());
		List<Conta> contas = daoFecharConta.filter(mesaId);
		if (contas.isEmpty()) {
			errorMsg = "Não existe conta aberta nesa mesa. ";
		}
    	
    	if (txfNumeroMesa.getLength() > 2) {
    		errorMsg = "Número da mesa alto demais. ";
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
    
    private boolean validarConfirmar(){
    	String errorMsg = "";
    	
    	if (txfNumeroMesa.getText().isEmpty()){
    		errorMsg += "Selecione uma mesa. ";
    	}
    	if (lblValor.getText().isEmpty()){
    		errorMsg += "Pesquise uma mesa. ";
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
