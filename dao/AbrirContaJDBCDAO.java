package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Conta;

/**
 * Classe para o gerenciamento do Bando de Dados da tela Abrir Conta.
 * @author Darvyn Santos, Eduardo G. Pires, Leonardo Elnisky
 *
 */
public class AbrirContaJDBCDAO implements AbrirContaDAO, IConst{

	private Connection conexao;
	private PreparedStatement stmt;
	private PreparedStatement stmt2;
	
	/**
	 * Método para abrir a conexão com o banco.
	 */
	public void open() throws SQLException{
		conexao = Conexao.getConexao(Conexao.stringDeConexao, Conexao.usuario, Conexao.senha);
	}
	
	/**
	 * Método para fechar a conexão com o banco.
	 */
	public void close() throws SQLException{
		conexao.close();
	}
	
	/**
	 * Método para gerar uma conta com o ID recebido no banco.
	 * @param contaId número da conta
	 */
	public void gerarConta(int contaId) throws SQLException{
		open();
		
		String sql = "INSERT INTO Conta (contaId) VALUES (?)";
		stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, contaId);
		
		stmt.executeUpdate();
		close();
	}
	
	/**
	 * Método para incluir todos os dados referentes à conta no ID correto.
	 * @param conta recebe a conta a qual deve ser adicionado os dados.
	 */
	public void incluir(Conta conta) throws SQLException{
		//Inserir a Conta no Banco
		open();
		String sql = "UPDATE Conta SET mesaId = ?, dataAbertura = ?, valor = ? WHERE contaId = ?";
		stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, conta.getMesaId());
		stmt.setDate(2, conta.getDataAbertura());
		stmt.setDouble(3, conta.getValor());
		stmt.setInt(4, conta.getContaId());
		stmt.executeUpdate();
		close();
		
		//Setar a mesa como ocupada
		open();
		String sql2 = "UPDATE Mesa SET ocupada = true WHERE mesaId = ?";
		stmt2 = conexao.prepareStatement(sql2);
		stmt2.setInt(1, conta.getMesaId());
		stmt2.executeUpdate();
		close();
	}
	
	/**
	 * Método para excluir uma conta do banco de dados.
	 * @param contaId recebe um ID de conta para remover do banco.
	 */
	public void excluir(int contaId) throws SQLException{
		open();
		String sql = "DELETE FROM Conta WHERE contaId = ?";
		stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, contaId);
		stmt.executeUpdate();
		close();
	}
	
}
