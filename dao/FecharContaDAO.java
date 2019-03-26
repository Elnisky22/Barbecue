package dao;

import model.Conta;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface usada pela classe Fechar Conta.
 * @author Darvyn Santos, Eduardo G. Pires, Leonardo Elnisky
 *
 */
public interface FecharContaDAO {

	public void open() throws SQLException;
	
	public void close() throws SQLException;
	
	public List<Conta> filter(int mesaId) throws SQLException;
	
	public void excluir(Conta conta) throws SQLException;
}
