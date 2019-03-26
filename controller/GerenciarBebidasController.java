package controller;

import application.*;
import dao.GerenciarBebidasJDBCDAO;
import model.Bebida;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GerenciarBebidasController {

    @FXML private VBox vBoxGerenciarBebidas;
    @FXML private Pane paneGerenciarBebidas;
    
    @FXML private TableView<Bebida> tbGerenciarBebidas;
    @FXML private TableColumn<Bebida, Integer> tcolumnBebidaId;
    @FXML private TableColumn<Bebida, String> tcolumnNome;
    @FXML private TableColumn<Bebida, Integer> tcolumnQuantidade;
    @FXML private TableColumn<Bebida, Double> tcolumnValor;
    
    //Repor Estoque
    @FXML private HBox hBoxEstoque;
    @FXML private TextField txfEstoque;
    @FXML private Button btnReporEstoque;
    
    //Gerenciar Bebidas
    @FXML private HBox hbGerenciarBebidas;
    @FXML private Button btnVoltar;
    @FXML private Button btnAdicionar;
    @FXML private Button btnRemover;
    
    private Stage stageAdicionar;
    private Stage stageRemover;
    
    private GerenciarBebidasJDBCDAO daoBebidas;

    @FXML void initialize() throws SQLException{
    	inicializarTableViewBebidas();
    	
    	//Listener do TextField QuantidadeCliente
    	txfEstoque.textProperty().addListener(new ChangeListener<String>() {
    	    @Override
    	    public void changed(ObservableValue<? extends String> observable, String oldValue, 
    	        String newValue) {
    	        if (!newValue.matches("\\d*")) {
    	            txfEstoque.setText(newValue.replaceAll("[^\\d]", ""));
    	        }
    	    }
    	});
    }
    
    @FXML void btnAdicionarOnAction(ActionEvent event) throws IOException, SQLException{
		VBox vboxadd = FXMLLoader.load(getClass().getResource("/view/AdicionarBebida.fxml"));
		Scene sceneadd = new Scene(vboxadd, 300, 350);
			
		stageAdicionar = new Stage();
		stageAdicionar.setScene(sceneadd);
		stageAdicionar.initModality(Modality.APPLICATION_MODAL);
		stageAdicionar.getIcons().add(new Image("/view/Icon.png"));
		stageAdicionar.setTitle("Adicionar Bebida");
    	stageAdicionar.showAndWait();
    	inicializarTableViewBebidas();
    }

    @FXML void btnRemoverOnAction(ActionEvent event) throws IOException, SQLException{
		VBox vboxrem = FXMLLoader.load(getClass().getResource("/view/RemoverBebida.fxml"));
		Scene scenerem = new Scene(vboxrem, 300, 200);
		
		stageRemover = new Stage();
		stageRemover.setScene(scenerem);
		stageRemover.initModality(Modality.APPLICATION_MODAL);
		stageRemover.getIcons().add(new Image("/view/Icon.png"));
		stageRemover.setTitle("Remover Bebida");
    	stageRemover.showAndWait();
    	inicializarTableViewBebidas();
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
    
    @FXML void btnReporEstoqueOnAction(ActionEvent event) throws SQLException {
    	if (validarReposicao()) {
    		daoBebidas = new GerenciarBebidasJDBCDAO();
    		Bebida altBebida = tbGerenciarBebidas.getSelectionModel().getSelectedItem();
    		int qtdEstoque = (altBebida.getQuantidadeBebida() + Integer.parseInt(txfEstoque.getText()));
    		altBebida.setQuantidadeBebida(qtdEstoque);
    		daoBebidas.repor(altBebida);
    		
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Sucesso");
    		alert.setContentText("Reposição efetuada com sucesso.");
    		alert.showAndWait();
    	
    		inicializarTableViewBebidas();
    	}
    }

    private void inicializarTableViewBebidas() throws SQLException{
    	daoBebidas = new GerenciarBebidasJDBCDAO();
    	List<Bebida> bebidas = daoBebidas.load();
    	ObservableList<Bebida> listBebidas = FXCollections.observableArrayList(bebidas);
    	
    	tbGerenciarBebidas.setItems(listBebidas);
    	tcolumnBebidaId.setCellValueFactory(new PropertyValueFactory<Bebida, Integer>("bebidaId"));
    	tcolumnNome.setCellValueFactory(new PropertyValueFactory<Bebida, String>("nome"));
    	tcolumnQuantidade.setCellValueFactory(new PropertyValueFactory<Bebida, Integer>("quantidadeBebida"));
    	tcolumnValor.setCellValueFactory(new PropertyValueFactory<Bebida, Double>("valor"));
    }
    
    private boolean validarReposicao() {
    	String errorMsg = "";
    	
    	Bebida bbSelecionada = tbGerenciarBebidas.getSelectionModel().getSelectedItem();
    	
    	if (tbGerenciarBebidas.getSelectionModel().getSelectedItem() == null) {
    		errorMsg = "Selecione uma bebida para repor. ";
    	}
    	if (txfEstoque.getText().isEmpty()) {
    		errorMsg = "Insira uma quantia para repor. ";
    	}
    	
    	int qtdBebida = 0;
    	if (tbGerenciarBebidas.getSelectionModel().getSelectedItem() != null) {
    		qtdBebida = bbSelecionada.getQuantidadeBebida();
    	}
    	int qtdEstoque = 0;
    	if (!txfEstoque.getText().isEmpty()) {
    		qtdEstoque = Integer.parseInt(txfEstoque.getText());
    	}
    	if ((qtdBebida + qtdEstoque) > 999){
    		errorMsg = "A reposição excede o limite do estoque. ";
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
