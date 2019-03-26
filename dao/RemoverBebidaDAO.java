package dao;

import model.Bebida;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface utilizada pela classe Remover Bebida.
 * @author Darvyn Santos, Eduardo G. Pires, Leonardo Elnisky
 *
 */
public interface RemoverBebidaDAO {

	public void open() throws SQLException;
	
	public void close() throws SQLException;
	
	public List<Bebida> filter(String nome) throws SQLException;
	
	public void delete(String text) throws SQLException;
}
