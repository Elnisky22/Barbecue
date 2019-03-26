package dao;

import model.Bebida;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface usada pela classe Gerenciar Bebidas.
 * @author Darvyn Santos, Eduardo G. Pires, Leonardo Elnisky
 *
 */
public interface GerenciarBebidasDAO {
	
	public void open() throws SQLException;
	
	public void close() throws SQLException;
	
	public List<Bebida> load() throws SQLException;
	
	public void repor(Bebida bebida) throws SQLException;
}
