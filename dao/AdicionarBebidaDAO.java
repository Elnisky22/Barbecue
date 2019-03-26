package dao;

import java.sql.SQLException;

import model.Bebida;

/**
 * Interface usada pela classe Adicionar bebida.
 * @author Darvyn Santos, Eduardo G. Pires, Leonardo Elnisky
 *
 */
public interface AdicionarBebidaDAO {

	public void open() throws SQLException;
	
	public void close() throws SQLException;
	
	public void incluir(Bebida bebida) throws SQLException;
}
