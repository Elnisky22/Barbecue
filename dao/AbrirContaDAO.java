package dao;

import model.Conta;

import java.sql.SQLException;

/**
 * Interface da classe Abrir Conta.
 * @author Darvyn Santos, Eduardo G. Pires, Leonardo Elnisky
 *
 */
public interface AbrirContaDAO {

	public void open() throws SQLException;
	
	public void close() throws SQLException;
	
	public void gerarConta(int contaId) throws SQLException;
	
	public void incluir(Conta conta) throws SQLException;
	
	public void excluir(int contaId) throws SQLException;
}
