package dao;

import java.sql.SQLException;
import java.util.List;

import model.ContaBebida;

/**
 * interface usada pela classe Conta Bebida.
 * @author Darvyn Santos, Eduardo G. Pires, Leonardo Elnisky
 *
 */
public interface ContaBebidaDAO {
	public void open() throws SQLException;
	
	public void close() throws SQLException;
	
	public List<ContaBebida> load(int contaId) throws SQLException;
	
	public void incluir(ContaBebida bebida) throws SQLException;
	
	public void remover(ContaBebida bebida) throws SQLException;
}
