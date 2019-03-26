package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Classe com o modelo do objeto do tipo ContaRefeicao.
 * @author Darvyn Santos, Eduardo G. Pires, Leonardo Elnisky
 *
 */
public class ContaRefeicao{
	private IntegerProperty refeicaoId = new SimpleIntegerProperty();
	private IntegerProperty contaId = new SimpleIntegerProperty();
	private StringProperty nome = new SimpleStringProperty();
	private IntegerProperty quantidade = new SimpleIntegerProperty();
	private DoubleProperty valorUnitario = new SimpleDoubleProperty();
	private DoubleProperty valorTotal = new SimpleDoubleProperty();
	
	public void setRefeicaoId(int refeicaoId){
		this.refeicaoId.set(refeicaoId);
	}
	
	public int getRefeicaoId(){
		return refeicaoId.get();
	}
	
	public void setContaId(int contaId){
		this.contaId.set(contaId);
	}
	
	public int getContaId(){
		return contaId.get();
	}
	
	public void setNome(String nome){
		this.nome.set(nome);
	}
	
	public String getNome(){
		return nome.get();
	}
	
	public void setQuantidade(int quantidade){
		this.quantidade.set(quantidade);
	}
	
	public int getQuantidade(){
		return quantidade.get();
	}
	
	public void setValorUnitario(double valor){
		this.valorUnitario.set(valor);
	}
	
	public double getValorUnitario(){
		return valorUnitario.get();
	}
	
	public void setValorTotal(double valor) {
		this.valorTotal.set(valor);
	}
	
	public double getValorTotal(){
		return valorTotal.get();
	}
}
