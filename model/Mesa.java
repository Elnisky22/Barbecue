package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Classe com o modelo do objeto do tipo Mesa.
 * @author Darvyn Santos, Eduardo G. Pires, Leonardo Elnisky
 *
 */
public class Mesa{
	private IntegerProperty mesaId = new SimpleIntegerProperty();
	private IntegerProperty quantidadeCliente = new SimpleIntegerProperty();
	private BooleanProperty ocupada = new SimpleBooleanProperty();
	
	public Mesa(){
		
	}
	
	public void setMesaId(int mesaId){
		this.mesaId.set(mesaId);
	}
	
	public int getMesaId(){
		return mesaId.get();
	}
	
	public void setQuantidadeCliente(int quantia){
		quantidadeCliente.set(quantia);
	}
	
	public int getQuantidadeCliente(){
		return quantidadeCliente.get();
	}
	
	public void setOcupada(boolean ocupada){
		this.ocupada.set(ocupada);;
	}
	
	public boolean getOcupada(){
		return ocupada.get();
	}
	
	@Override
	public String toString(){
		return "Mesa: " + mesaId.get() + " | Capacidade: " + quantidadeCliente.get();
	}
}
