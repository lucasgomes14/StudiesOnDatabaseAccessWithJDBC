package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Program {

	public static void main(String[] args) {
		
		// conectando com o banco
		/*
		 * Connection conn = DB.getConnection();
		 * conn.closeConnection();
		 * */
		
		// recuperar dados!
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			conn = DB.getConnection(); // conecta ao banco
			
			st = conn.createStatement(); // instaciando o statement(comando sql)
			
			rs = st.executeQuery("select * from department"); // executando o comando sql e atribui ao ResultSet que vai ficar os valores numa tabela
			
			while(rs.next()) { // enquanto rs tiver o proxima linha(next() returna false quando tiver no ultima linha)
				System.out.println(rs.getInt("Id") + ", " + rs.getString("Name")); // imprimir o inteiro do campo id e a string do campo Name
			}
		} catch(SQLException e) {
			e.printStackTrace(); // mostrar o erro no console
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
