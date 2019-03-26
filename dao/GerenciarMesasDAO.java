package dao;

import java.sql.SQLException;
import java.util.List;

import model.Mesa;

/**
 * Interface utilizada pela classe Gerenciar Mesas.
 * @author Darvyn Santos, Eduardo G. Pires, Leonardo Elnisky
 *
 */
public interface GerenciarMesasDAO {

	public void open() throws SQLException;
	
	public void close() throws SQLException;
	
	public List<Mesa> filter(String text) throws SQLException;
}
