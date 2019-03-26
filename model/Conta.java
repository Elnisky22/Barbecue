package model;

import java.sql.Date;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Classe com o modelo do objeto do tipo Conta.
 * @author Darvyn Santos, Eduardo G. Pires, Leonardo Elnisky
 *
 */
public class Conta{
	private IntegerProperty contaId = new SimpleIntegerProperty();
	private IntegerProperty mesaId = new SimpleIntegerProperty();
	private Date dataAbertura;
	private Date dataFechamento;
	private DoubleProperty valor = new SimpleDoubleProperty();
	
	public void setContaId(int contaId) {
		this.contaId.set(contaId);
	}
	
	public int getContaId(){
		return contaId.get();
	}
	
	public void setMesaId(int mesaId) {
		this.mesaId.set(mesaId);
	}
	
	public int getMesaId() {
		return mesaId.get();
	}
	
	public void setDataAbertura(Date data){
		dataAbertura = data;
	}
	
	public Date getDataAbertura(){
		return dataAbertura;
	}
	
	public void setDataFechamento(Date data){
		dataFechamento = data;
	}
	
	public Date getDataFechamento(){
		return dataFechamento;
	}
	
	public void setValor(double valor){
		this.valor.set(valor);
	}
	
	public double getValor(){
		return valor.get();
	}
}
