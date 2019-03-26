package dao;

import model.Bebida;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe para gerenciar o banco de dados da tela Remover Bebida.
 * @author leona
 *
 */
public class RemoverBebidaJDBCDAO implements RemoverBebidaDAO, IConst{
	
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
	 * Método para buscar bebidas no banco, usando o filtro de nome.
	 * @param nome recebe uma String com o nome da bebida a ser buscado no banco.
	 * @return a lista das bebidas encontradas com o nome.
	 */
	public List<Bebida> filter(String nome) throws SQLException{
		open();
		String sql = "SELECT * FROM Bebida WHERE nome = ?";
		stmt = conexao.prepareStatement(sql);
		stmt.setString(1, nome);
		
		try (ResultSet rs = stmt.executeQuery()) {
			List<Bebida> listBebidas = new ArrayList<>();
			while (rs.next()) {
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
	
	/**
	 * Método para deletar uma bebida do banco.
	 * @param text recebe uma String com o nome da bebida a ser removida.
	 */
	public void delete(String text) throws SQLException{
		open();
		String sql = "DELETE FROM Bebida WHERE nome = ?";
		stmt = conexao.prepareStatement(sql);
		stmt.setString(1, text);
		stmt.executeUpdate();
		close();
	}
}
