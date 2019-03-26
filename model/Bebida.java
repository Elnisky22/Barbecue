package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Classe com o modelo de um objeto do tipo Bebida.
 * @author Darvyn Santos, Eduardo G. Pires, Leonardo Elnisky
 *
 */
public class Bebida{
	private IntegerProperty bebidaId = new SimpleIntegerProperty();
	private StringProperty nome = new SimpleStringProperty();
	private IntegerProperty quantidadeEstoque = new SimpleIntegerProperty();
	private DoubleProperty valor = new SimpleDoubleProperty();
	
	public void setBebidaId(int bebidaId){
		this.bebidaId.set(bebidaId);
	}
	
	public int getBebidaId(){
		return bebidaId.get();
	}
	
	public void setNome(String nome){
		this.nome.set(nome);
	}
	
	public String getNome(){
		return nome.get();
	}
	
	public void setQuantidadeBebida(int quantia){
		quantidadeEstoque.set(quantia);
	}
	
	public int getQuantidadeBebida(){
		return quantidadeEstoque.get();
	}
	
	public void setValor(double valor){
		this.valor.set(valor);
	}
	
	public double getValor(){
		return valor.get();
	}
}