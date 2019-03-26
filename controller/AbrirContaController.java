package controller;

import model.Bebida;
import model.Conta;
import model.ContaBebida;
import model.Mesa;
import application.Main;
import dao.AbrirContaJDBCDAO;
import dao.ContaBebidaJDBCDAO;
import dao.GerenciarBebidasJDBCDAO;
import dao.GerenciarMesasJDBCDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;
import java.util.List;
import java.util.Random;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

/**
 * Classe controladora da tela "AbrirConta".
 * @author Darvyn Santos, Eduardo G. Pires, Leonardo Elnisky
 *
 */
public class AbrirContaController{

    @FXML private VBox vBoxAbrirConta;
    @FXML private Label lblNumConta;
    @FXML private Label lblQuantidadeCliente;
    @FXML private TextField txfQuantidadeCliente;
    @FXML private TextField txfBuffet;
    @FXML private TextField txfRodizio;
    @FXML private Label lblEscolhaMesa;
    @FXML private Label lblBuffet;
    @FXML private Label lblRodizio;
    @FXML private ChoiceBox<Mesa> cbEscolhaMesa;
    @FXML private BorderPane bpaneTipoComida;
    @FXML private GridPane gpaneTipoComida;
    @FXML private BorderPane bpaneBebidas;
    
    //TableView Bebidas
    @FXML private TableView<Bebida> tableBebidas;
    @FXML private TableColumn<Bebida, String> tcolumnBebida;
    @FXML private TableColumn<Bebida, Double> tcolumnValor;
    @FXML private TableColumn<Bebida, Integer> tcolumnQuantidade;
    
    //TableView Selecionadas
    @FXML private TableView<ContaBebida> tableSelecionadas;
    @FXML private TableColumn<ContaBebida, String> t2columnBebida;
    @FXML private TableColumn<ContaBebida, Double> t2columnValor;
    @FXML private TableColumn<ContaBebida, Integer> t2columnQuantidade;
    @FXML private TableColumn<ContaBebida, Double> t2columnValorTotal;
    
    //Gerenciador Bebidas
    @FXML private VBox vBoxSelecaoBebidas;
    @FXML private TextField txfInserirBebida;
    @FXML private Button btnAdicionarBebida;
    @FXML private Button btnRemoverBebida;
    
    @FXML private HBox hBoxButtons;
    @FXML private Button btnVoltar;
    @FXML private Button btnProximo;
    
    private GerenciarMesasJDBCDAO daoMesas;
    private GerenciarBebidasJDBCDAO daoBebidas;
    private ContaBebidaJDBCDAO daoBebidasSelecionadas;
    private AbrirContaJDBCDAO daoAbrirConta;
    
    private Random ran = new Random();
    private int contaId;
    private LocalDate hoje = LocalDate.now();
    
    /**
     * Método inicializador da tela AbrirConta.
     * @throws SQLException
     */
    @FXML private void initialize() throws SQLException{
    	contaId = ran.nextInt(999);
    	lblNumConta.setText("Conta nº: " + getContaId());
    	
    	daoAbrirConta = new AbrirContaJDBCDAO();
    	daoAbrirConta.gerarConta(contaId);
    	
    	inicializarChoiceBox();
    	inicializarTableView();
    	
    	//Listener do TextField QuantidadeCliente
    	txfQuantidadeCliente.textProperty().addListener(new ChangeListener<String>() {
    	    @Override
    	    public void changed(ObservableValue<? extends String> observable, String oldValue, 
    	        String newValue) {
    	        if (!newValue.matches("\\d*")) {
    	            txfQuantidadeCliente.setText(newValue.replaceAll("[^\\d]", ""));
    	        }
    	    }
    	});
    	
    	//Listener do TextField Buffet
    	txfBuffet.textProperty().addListener(new ChangeListener<String>() {
    	    @Override
    	    public void changed(ObservableValue<? extends String> observable, String oldValue, 
    	        String newValue) {
    	        if (!newValue.matches("\\d*")) {
    	            txfBuffet.setText(newValue.replaceAll("[^\\d]", ""));
    	        }
    	    }
    	});
    	
    	//Listener do TextField Rodizio
    	txfRodizio.textProperty().addListener(new ChangeListener<String>() {
    	    @Override
    	    public void changed(ObservableValue<? extends String> observable, String oldValue, 
    	        String newValue) {
    	        if (!newValue.matches("\\d*")) {
    	            txfRodizio.setText(newValue.replaceAll("[^\\d]", ""));
    	        }
    	    }
    	});
    	
    	txfInserirBebida.textProperty().addListener(new ChangeListener<String>() {
    	    @Override
    	    public void changed(ObservableValue<? extends String> observable, String oldValue, 
    	        String newValue) {
    	        if (!newValue.matches("\\d*")) {
    	            txfInserirBebida.setText(newValue.replaceAll("[^\\d]", ""));
    	        }
    	    }
    	});
    }

    /**
     * Método para adicionar uma bebida à uma conta.
     * @param event
     * @throws SQLException
     */
    @FXML void btnAdicionarBebidaOnAction(ActionEvent event) throws SQLException{
    	if (verificaAdicionarBebida()) {
    		Bebida newBebida = new Bebida(); 
    		newBebida = tableBebidas.getSelectionModel().getSelectedItem();
    		
    		//Gerar objeto
    		ContaBebida bebidaVendida = new ContaBebida();
    		bebidaVendida.setBebidaId(newBebida.getBebidaId());
    		bebidaVendida.setContaId(getContaId());
    		bebidaVendida.setNome(newBebida.getNome());
    		bebidaVendida.setQuantidade(Integer.parseInt(txfInserirBebida.getText()));
    		bebidaVendida.setValorUnitario(newBebida.getValor());
    		bebidaVendida.setValorTotal(bebidaVendida.getValorUnitario() * bebidaVendida.getQuantidade());
    		
    		//Enviar objeto para o banco
    		daoBebidasSelecionadas = new ContaBebidaJDBCDAO();
    		daoBebidasSelecionadas.incluir(bebidaVendida);
    		
    		//Carregar objetos do banco na tabela
    		inicializarTableviewSelecionadas();
    	}
    }
    
    /**
     * Método para remover uma bebida de uma conta.
     * @param event
     * @throws SQLException
     */
    @FXML void btnRemoverBebidaOnAction(ActionEvent event) throws SQLException{
    	if (verificaRemoverBebida()) {
    		//Remove objeto do banco
    		daoBebidasSelecionadas = new ContaBebidaJDBCDAO();
    		ContaBebida remBebida = tableSelecionadas.getSelectionModel().getSelectedItem();
    		daoBebidasSelecionadas.remover(remBebida);
    	
    		//Atualizar a tabela
    		tableSelecionadas.getItems().clear();
    		tableSelecionadas.refresh();
    		inicializarTableviewSelecionadas();
    	}
    }
    
    /**
     * Método para coletar os dados, gerar uma conta e adicionar ao banco de dados.
     * @param event
     * @throws SQLException
     */
    @FXML void btnProximoOnAction(ActionEvent event) throws SQLException{
    	if (verificaAdicionarConta()){
    		Mesa mesaSelecionada = cbEscolhaMesa.getSelectionModel().getSelectedItem();
    	
    		//Pegar o valor do total das bebidas
    		ObservableList<ContaBebida> listBebidas = tableSelecionadas.getItems();
    		double totalBebidas = 0.00;
    		for (int i=0 ; i<listBebidas.size(); i++) {
    			totalBebidas += listBebidas.get(i).getValorTotal();
    		}
    	
    		//Pegar o valor total das refeições
    		double totalRefeicao = 0.00;
    		totalRefeicao = ((35 * (Double.parseDouble(txfBuffet.getText()))) + (60 * (Double.parseDouble(txfRodizio.getText()))));
    		
    		//Preço total da conta
    		double totalConta = totalBebidas + totalRefeicao;
    		
    		//Gerar o objeto Conta
    		Conta newConta = new Conta();
    		newConta.setContaId(getContaId());
    		newConta.setMesaId(mesaSelecionada.getMesaId());
    		newConta.setDataAbertura(Date.valueOf(hoje));
    		newConta.setValor(totalConta);
    		
    		//Enviar Conta para o banco
    		daoAbrirConta = new AbrirContaJDBCDAO();
    		daoAbrirConta.incluir(newConta);
    		
    		//Criar alerta para confirmar a inserção
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Sucesso");
    		alert.setHeaderText(null);
    		alert.setContentText("Conta criada com sucesso!");
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

    /**
     * Método para retornar ao menu principal.
     * @param event
     * @throws SQLException
     */
    @FXML void btnVoltarOnAction(ActionEvent event) throws SQLException{
    	daoAbrirConta = new AbrirContaJDBCDAO();
    	daoAbrirConta.excluir(contaId);
    	
    	try {
    		VBox vbox = FXMLLoader.load(getClass().getResource("/view/Inicio.fxml"));
    		BorderPane bpane = Main.getRoot();
    		bpane.setCenter(vbox);
    	} catch (IOException e){
    		e.printStackTrace();
    	}
    }
    
    /**
     * Método para atualizar a ChoiceBox.
     * @throws SQLException
     */
    private void inicializarChoiceBox() throws SQLException{
    	daoMesas = new GerenciarMesasJDBCDAO();
    	List<Mesa> mesas = daoMesas.filter("false");
    	ObservableList<Mesa> listaMesas = FXCollections.observableArrayList(mesas);
    	cbEscolhaMesa.setItems(listaMesas);
    }
    
    /**
     * Método para atualizar a TableView Bebidas.
     * @throws SQLException
     */
    private void inicializarTableView() throws SQLException{
    	daoBebidas = new GerenciarBebidasJDBCDAO();
    	List<Bebida> bebidas = daoBebidas.load();
    	ObservableList<Bebida> listaBebidas = FXCollections.observableArrayList(bebidas);
    	
    	tableBebidas.setItems(listaBebidas);
    	tcolumnBebida.setCellValueFactory(new PropertyValueFactory<Bebida, String>("nome"));
    	tcolumnValor.setCellValueFactory(new PropertyValueFactory<Bebida, Double>("valor"));
    	tcolumnQuantidade.setCellValueFactory(new PropertyValueFactory<Bebida, Integer>("quantidadeBebida"));
    }
    
    /**
     * Método para atualizar a TableView BebidasSelecionadas.
     * @throws SQLException
     */
    private void inicializarTableviewSelecionadas() throws SQLException{
    	daoBebidasSelecionadas = new ContaBebidaJDBCDAO();
    	List<ContaBebida> bebidasSelecionadas = daoBebidasSelecionadas.load(getContaId());
    	ObservableList<ContaBebida> listBebidasSelecionadas = FXCollections.observableArrayList(bebidasSelecionadas);
    	
    	tableSelecionadas.setItems(listBebidasSelecionadas);
    	t2columnBebida.setCellValueFactory(new PropertyValueFactory<ContaBebida, String>("nome"));
    	t2columnValor.setCellValueFactory(new PropertyValueFactory<ContaBebida, Double>("valorUnitario"));
    	t2columnQuantidade.setCellValueFactory(new PropertyValueFactory<ContaBebida, Integer>("quantidade"));
    	t2columnValorTotal.setCellValueFactory(new PropertyValueFactory<ContaBebida, Double>("valorTotal"));
    }
    
    /**
     * Método para coletar o Id da conta.
     * @return o ID da conta.
     */
    public int getContaId(){
    	return contaId;
    }
    
    /**
     * Método para verificar se os dados da bebida estão de acordo para inserir no banco de dados. Caso não estejam, avisa qual é o problema ao usuário.
     * @return true se estiver ok, false se estiver com algum problema.
     */
    private boolean verificaAdicionarBebida(){
    	String errorMsg = "";
    	
    	if (tableBebidas.getSelectionModel().getSelectedItem() == null) {
    		errorMsg += "Selecione uma bebida para inserir. ";
    	}
    	if (txfInserirBebida.getText().isEmpty()) {
    		errorMsg += "Insira a quantidade da bebida. ";
    	}
    	
    	if (txfInserirBebida.getLength() > 2) {
    		errorMsg += "Quantidade elevada da bebida. ";
    	}
    	
    	Bebida bebidaSelecionada = tableBebidas.getSelectionModel().getSelectedItem();
    	int qtdEstoque = bebidaSelecionada.getQuantidadeBebida();
    	int qtdSelecionada = 0;
    	if (!txfInserirBebida.getText().isEmpty()) {
    		qtdSelecionada = Integer.parseInt(txfInserirBebida.getText());
    	}
    	if (qtdSelecionada > qtdEstoque) {
    		errorMsg = "Quantidade selecionada excede o estoque. ";
    	}
    	
    	if (errorMsg == "") {
    		return true;
    	} else {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Erro");
    		alert.setHeaderText("Ocorreu um erro:");
    		alert.setContentText(errorMsg);
    		alert.showAndWait();
    		return false;
    	}
    }
    
    /**
     * Método para verificar se as condições para remover uma bebida pré-selecionada estão de acordo. Avisa ao usuário qual é o problema.
     * @return true se estiver de acordo, false se houver algum problema.
     */
    private boolean verificaRemoverBebida(){
    	String errorMsg = "";
    	
    	if (tableSelecionadas.getSelectionModel().getSelectedItem() == null) {
    		errorMsg += "Selecione uma bebida para remover. ";
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
    
    /**
     * Método para verificar se todos os dados para gerar uma conta estão de acordo com os requisitos. Caso haja algum problema, informa o usuário qual é.
     * @return true se estiver ok, false se houver problema.
     */
    private boolean verificaAdicionarConta(){
    	String errorMsg = "";
    	
    	if (txfQuantidadeCliente.getText().isEmpty()) {
    		errorMsg += "Selecione a quantidade de clientes. ";
    	}
    	if (cbEscolhaMesa.getSelectionModel().getSelectedItem() == null) {
    		errorMsg += "Mesa não selecionada. ";
    	}
    	if (txfBuffet.getText().isEmpty()) {
    		errorMsg += "Insira a quantidade de Buffets. ";
    	}
    	if (txfRodizio.getText().isEmpty()) {
    		errorMsg += "Insira a quantidade de Rodízios. ";
    	}
    	
    	int qtdCliente = 0;
    	if (!txfQuantidadeCliente.getText().isEmpty()) {
    		qtdCliente = Integer.parseInt(txfQuantidadeCliente.getText());
    	}
    	int qtdBuffet = 0;
    	if (!txfBuffet.getText().isEmpty()) {
    		qtdBuffet = Integer.parseInt(txfBuffet.getText());;
    	}
    	int qtdRodizio = 0;
    	if (!txfRodizio.getText().isEmpty()){
    		qtdRodizio = Integer.parseInt(txfRodizio.getText());
    	}
    	int qtdRefeicao = qtdBuffet + qtdRodizio;
    	if (qtdRefeicao > qtdCliente){
    		errorMsg += "Quantidade elevada de refeições. ";
    	}
    	
    	if (txfQuantidadeCliente.getLength() > 2) {
    		errorMsg += "Quantidade elevada de clientes. ";
    	}
    	if (txfBuffet.getLength() > 2) {
    		errorMsg += "Quantidade elevada de buffets. ";
    	}
    	if (txfRodizio.getLength() > 2) {
    		errorMsg += "Quantidade elevada de rodízios. ";
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
