package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Mesa;

/**
 * Classe para o gerenciamento do banco de dados da tela Gerenciar Mesas.
 * @author Darvyn Santos, Eduardo G. Pires, Leonardo Elnisky
 *
 */
public class GerenciarMesasJDBCDAO implements GerenciarMesasDAO, IConst{

	private Connection conexao;
	
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
	 * Método para buscar as mesas com filtro por ocupação. Pode retornar todas as mesas ou apenas as mesas livres/ocupadas.
	 * @param text recebe uma String (true ou false) para fazer a verificação de ocupação das mesas.
	 */
	public List<Mesa> filter(String text) throws SQLException{
		open();
		String sql = "SELECT * FROM Mesa";
		
		if (text == null) sql += " ORDER BY 1";
		
		if (text != null){
			sql += " WHERE ocupada = ? ORDER BY 1";
		}
		
		try (PreparedStatement stmt = conexao.prepareStatement(sql)){
			
			if (text != null) {
				if (text == "false"){
					stmt.setBoolean(1, false);
				}
				else if (text == "true"){
					stmt.setBoolean(1, true);
				}
				else {
					stmt.setString(1, text);
				}
			}
			
			try (ResultSet rs = stmt.executeQuery()) {
				List<Mesa> listMesas = new ArrayList<>();
				while (rs.next()) {
					Mesa mesa = new Mesa();
		            mesa.setMesaId(rs.getInt("mesaId"));
		            mesa.setQuantidadeCliente(rs.getInt("quantidadeCliente"));
		            mesa.setOcupada(rs.getBoolean("ocupada"));
					listMesas.add(mesa);
				}
				close();
				return listMesas;
			}
		}
	}
}
