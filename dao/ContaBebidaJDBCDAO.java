package dao;

import model.ContaBebida;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe para gerenciar o banco de dados da tela Conta Bebida.
 * @author Darvyn Santos, Eduardo G. Pires, Leonardo Elnisky
 *
 */
public class ContaBebidaJDBCDAO implements ContaBebidaDAO, IConst{
	
	private Connection conexao;
	private PreparedStatement stmt;
	
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
	 * Método para carregar todas as bebidas cadastradas no ID da conta.
	 * @param contaId o ID da conta a ser usado na busca.
	 */
	public List<ContaBebida> load(int contaId) throws SQLException{
		open();
		String sql = "SELECT * FROM ContaBebida";
		
		if (contaId > 0) {
			sql += " WHERE contaId = ?";
		}
		
		try (PreparedStatement stmt = conexao.prepareStatement(sql)){
			stmt.setInt(1, contaId);
			try (ResultSet rs = stmt.executeQuery()){
				List<ContaBebida> listBebidas = new ArrayList<>();
				while (rs.next()){
					ContaBebida bebida = new ContaBebida();
					bebida.setBebidaId(rs.getInt("bebidaId"));
					bebida.setContaId(rs.getInt("contaId"));
					bebida.setNome(rs.getString("nome"));
					bebida.setQuantidade(rs.getInt("quantidade"));
					bebida.setValorUnitario(rs.getDouble("valorunitario"));
					bebida.setValorTotal(rs.getDouble("valortotal"));
					listBebidas.add(bebida);
				}
			close();
			return listBebidas;
			}
		}
	}
	
	/**
	 * Método para incluir uma nova bebida em uma conta existente.
	 * @param bebida recebe um objeto bebida com os dados para inserir no banco.
	 */
	public void incluir(ContaBebida bebida) throws SQLException{
		open();
		String sql = "INSERT INTO ContaBebida (bebidaId, contaId, quantidade, nome, valorunitario, valortotal) VALUES (?, ?, ?, ?, ?, ?)";
		stmt = conexao.prepareStatement(sql);
		
		stmt.setInt(1, bebida.getBebidaId());
		stmt.setInt(2, bebida.getContaId());
		stmt.setInt(3, bebida.getQuantidade());
		stmt.setString(4, bebida.getNome());
		stmt.setDouble(5, bebida.getValorUnitario());
		stmt.setDouble(6, bebida.getValorTotal());
		
		stmt.executeUpdate();
		close();
	}
	
	/**
	 * Método para remover uma bebida de uma determinada conta.
	 * @param recebe uma bebida para remover da conta cujo ID está no parâmetro.
	 */
	public void remover(ContaBebida bebida) throws SQLException{
		open();
		String sql = "DELETE FROM ContaBebida WHERE bebidaId = ?";
		stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, bebida.getBebidaId());
		stmt.executeUpdate();
		close();
	}
}
