package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Bebida;

/**
 * Classe para o gerenciamento do banco de dados da tela Gerenciar Bebidas.
 * @author Darvyn Santos, Eduardo G. Pires, Leonardo Elnisky
 *
 */
public class GerenciarBebidasJDBCDAO implements GerenciarBebidasDAO, IConst{

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
	 * Método para buscar todas as bebidas no banco.
	 */
	public List<Bebida> load() throws SQLException{
		open();
		String sql = "SELECT * FROM Bebida ORDER BY 1";
		
		try (PreparedStatement stmt = conexao.prepareStatement(sql)){
			try (ResultSet rs = stmt.executeQuery()){
				List<Bebida> listBebidas = new ArrayList<>();
				while (rs.next()){
					Bebida bebida = new Bebida();
					bebida.setBebidaId(rs.getInt("bebidaId"));
					bebida.setNome(rs.getString("nome"));
					bebida.setQuantidadeBebida(rs.getInt("quantidade"));
					bebida.setValor(rs.getDouble("valor"));
					listBebidas.add(bebida);
				}
			close();
			return listBebidas;
			}
		}
	}
	
	/**
	 * Método para reabastecer o estoque de uma bebida.
	 * @param bebida recebe uma bebida para fazer o reabastecimento.
	 */
	public void repor(Bebida bebida) throws SQLException{
		open();
		String sql = "UPDATE Bebida SET quantidade = ? WHERE bebidaId = ?";
		stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, bebida.getQuantidadeBebida());
		stmt.setInt(2, bebida.getBebidaId());
		stmt.executeUpdate();
		close();
	}
	
}
