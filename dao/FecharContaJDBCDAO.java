package dao;

import model.Conta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe para gerenciar o banco de dados da tela Fechar Conta
 * @author Darvyn Santos, Eduardo G. Pires, Leonardo Elnisky
 *
 */
public class FecharContaJDBCDAO implements FecharContaDAO, IConst{
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
	 * Método bara buscar uma conta aberda em uma determinada mesa.
	 * @param mesaId recebe um ID de mesa para buscar uma conta.
	 */
	public List<Conta> filter(int mesaId) throws SQLException{
		open();
		String sql = "SELECT * FROM Conta WHERE mesaId = ? AND datafechamento IS NULL";
		stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, mesaId);
		
		try (ResultSet rs = stmt.executeQuery()) {
			List<Conta> listContas = new ArrayList<>();
			while (rs.next()) {
				Conta conta = new Conta();
				conta.setContaId(rs.getInt("contaId"));
	            conta.setMesaId(rs.getInt("mesaId"));
	            conta.setDataAbertura(rs.getDate("dataAbertura"));
	            conta.setDataFechamento(null);
	            conta.setValor(rs.getDouble("valor"));
				listContas.add(conta);
			}
			close();
			return listContas;
		}
	}
	
	/**
	 * Método para fechar a conta e liberar a mesa para uso.
	 * @param recebe os dados da conta a ser fechada.
	 */
	public void excluir(Conta conta) throws SQLException{
		//Setar data de fechamento da conta
		open();
		String sql = "UPDATE Conta SET datafechamento = CURRENT_DATE WHERE contaId = ?";
		stmt2 = conexao.prepareStatement(sql);
		stmt2.setInt(1, conta.getContaId());
		stmt2.executeUpdate();
		close();
		
		//Liberar mesa
		open();
		String sql2 = "UPDATE Mesa SET ocupada = false WHERE mesaId = ?";
		stmt2 = conexao.prepareStatement(sql2);
		stmt2.setInt(1, conta.getMesaId());
		stmt2.executeUpdate();
		close();
	}
}
