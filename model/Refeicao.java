package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Classe com o modelo do objeto do tipo Refeicao.
 * @author Darvyn Santos, Eduardo G. Pires, Leonardo Elnisky
 *
 */
public class Refeicao{
	private IntegerProperty refeicaoId = new SimpleIntegerProperty();
	private StringProperty nome = new SimpleStringProperty();
	private DoubleProperty valor = new SimpleDoubleProperty();
	
	public void setRefeicaoId(int refeicaoId){
		this.refeicaoId.set(refeicaoId);
	}
	
	public int getRefeicaoId(){
		return refeicaoId.get();
	}
	
	public void setNome(String nome){
		this.nome.set(nome);
	}
	
	public String getNome(){
		return nome.get();
	}
	
	public void setValor(double valor){
		this.valor.set(valor);
	}
	
	public double getValor(){
		return valor.get();
	}
}
