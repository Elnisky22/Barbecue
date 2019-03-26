package controller;

import model.Mesa;
import dao.GerenciarMesasJDBCDAO;
import application.Main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * Classe controladora da tela Gerenciar Mesas.
 * @author Darvyn Santos, Eduardo G. Pires, Leonardo Elnisky
 *
 */
public class GerenciarMesasController{

    @FXML private VBox vBoxGerenciarMesas;
    @FXML private BorderPane bpGerenciarMesas;
    @FXML private TableView<Mesa> tbGerenciarMesas;
    @FXML private TableColumn<Mesa, Integer> tcolumnMesaId;
    @FXML private TableColumn<Mesa, Integer> tcolmunQuantidade;
    @FXML private TableColumn<Mesa, Boolean> tcolumnOcupada;
    @FXML private Button btnVoltar;
    
    private GerenciarMesasJDBCDAO daoMesas;
    
    /**
     * Método para inicializar a tela de Gerenciar Mesas.
     * @throws SQLException
     */
    @FXML private void initialize() throws SQLException{
    	inicializarTableView();
    }
    
    /**
     * Método para retornar a tela principal.
     * @param event
     */
    @FXML void btnVoltarOnAction(ActionEvent event){
    	try {
    		VBox vbox = FXMLLoader.load(getClass().getResource("/view/Inicio.fxml"));
    		BorderPane bpane = Main.getRoot();
    		bpane.setCenter(vbox);
    	} catch (IOException e){
    		e.printStackTrace();
    	}
    }

    /**
     * Método para atualizar a TableView GerenciarMesas.
     * @throws SQLException
     */
    private void inicializarTableView() throws SQLException{
    	daoMesas = new GerenciarMesasJDBCDAO();
    	
    	List<Mesa> mesas = daoMesas.filter(null);
    	ObservableList<Mesa> listMesas = FXCollections.observableArrayList(mesas);
    	
    	tbGerenciarMesas.setItems(listMesas);
    	tcolumnMesaId.setCellValueFactory(new PropertyValueFactory<Mesa, Integer>("mesaId"));
    	tcolmunQuantidade.setCellValueFactory(new PropertyValueFactory<Mesa, Integer>("quantidadeCliente"));
    	tcolumnOcupada.setCellValueFactory(new PropertyValueFactory<Mesa, Boolean>("ocupada"));
    }
    
}
