package dao;

import model.Bebida;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Classe para o gerenciamento do Banco de Dados da tela Adicionar Bebida.
 * @author Darvyn Santos, Eduardo G. Pires, Leonardo Elnisky
 *
 */
public class AdicionarBebidaJDBCDAO implements AdicionarBebidaDAO, IConst{

	private Connection conexao;
	private PreparedStatement stmt;
	
	/**
	 * M�todo para abrir a conex�o com o banco.
	 */
	public void open() throws SQLException{
		conexao = Conexao.getConexao(Conexao.stringDeConexao, Conexao.usuario, Conexao.senha);
	}
	
	/**
	 * M�todo para fechar a conex�o com o banco.
	 */
	public void close() throws SQLException{
		conexao.close();
	}
	
	/**
	 * M�todo para incluir uma nova bebida no banco de dados.
	 * @param bebida recebe os dados necess�rios para incluir uma bebida no banco.
	 */
	public void incluir(Bebida bebida) throws SQLException{
		open();
		
		String sql = "INSERT INTO Bebida (bebidaId, nome, quantidade, valor) VALUES (DEFAULT, ?, ?, ?)";
		stmt = conexao.prepareStatement(sql);
		
		stmt.setString(1, bebida.getNome());
		stmt.setInt(2, bebida.getQuantidadeBebida());
		stmt.setDouble(3, bebida.getValor());
		
		stmt.executeUpdate();
		close();
	}
}
