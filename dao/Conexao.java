package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe para gerenciar a conexão com o banco de dados
 * @author Darvyn Santos, Eduardo G. Pires, Leonardo Elnisky
 *
 */
public class Conexao implements IConst{
   public static Connection getConexao(String stringDeConexao, String usuario, String senha){
    System.out.println(stringDeConexao);
	   
	   try{
	  return DriverManager.getConnection(stringDeConexao, usuario, senha);
    } catch (SQLException e){		
        throw new RuntimeException(e);
      }
  }
}