package application;
	
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

/**
 * Classe inicializadora do programa
 * @author Darvyn Santos, Eduardo G. Pires, Leonardo Elnisky
 *
 */
public class Main extends Application{
	
	private static BorderPane root = new BorderPane();
	
	/**
	 * Método para iniciar o stage na tela
	 */
	@Override
	public void start(Stage stage) throws IOException{
		//Gerar a cena
		Scene scene = new Scene(root, 800, 600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		MenuBar menubar = FXMLLoader.load(getClass().getResource("/view/MenuBar.fxml"));
		root.setTop(menubar);
		
		VBox vbox = FXMLLoader.load(getClass().getResource("/view/Inicio.fxml"));
		root.setCenter(vbox);
		
		//Exibir a cena
		stage.getIcons().add(new Image("/view/Icon.png"));
		stage.setTitle("Barbecue");
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * Método para pegar o painel principal do programa
	 * @return o painel
	 */
	public static BorderPane getRoot(){
		return root;
	}
	
	/**
	 * Método para iniciar o programa
	 * @param args
	 */
	public static void main(String[] args){
		launch(args);
	}
}
