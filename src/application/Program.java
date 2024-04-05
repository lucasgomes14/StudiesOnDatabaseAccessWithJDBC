package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;
import db.DbException;
import db.DbIntegrityException;

public class Program {

	public static void main(String[] args) {
		
		// conectando com o banco
		/*
		 * Connection conn = DB.getConnection();
		 * conn.closeConnection();
		 * */
		
		// recuperar dados!
		/*
		 * Connection conn = null;
		 * Statement st = null;
		 * ResultSet rs = null;
		 * 
		 * try {
		 * 	conn = DB.getConnection(); // conecta ao banco
		 * 	
		 * 	st = conn.createStatement(); // instaciando o statement(comando sql)
		 * 	
		 * 	rs = st.executeQuery("select * from department"); // executando o comando sql e atribui ao ResultSet que vai ficar os valores numa tabela
		 * 	
		 * 	while(rs.next()) { // enquanto rs tiver o proxima linha(next() returna false quando tiver no ultima linha)
		 * 		System.out.println(rs.getInt("Id") + ", " + rs.getString("Name")); // imprimir o inteiro do campo id e a string do campo Name
		 * 	}
		 * } catch(SQLException e) {
		 * 	e.printStackTrace(); // mostrar o erro no console
		 * } finally {
		 * 	DB.closeResultSet(rs);
		 * 	DB.closeStatement(st);
		 * 	DB.closeConnection();
		 * }
		 */
		
		// inserir dados!
		/*
		 * 
		 * 
		 * SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // formatação da data
		 * 
		 * Connection conn = null;
		 * 
		 * PreparedStatement st = null;
		 * 
		 * try {
		 * 	conn = DB.getConnection(); // conecta com o banco de dados
		 * 	
		 *  st = conn.prepareStatement( // instanciei para inserir os dados
		 * 			"INSERT INTO seller "
		 * 			+ "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
		 * 			+ "VALUES "
		 * 			+ "(?, ?, ?, ?, ?)"); // modo de inserção normal
		 * 	
		 * 	st = conn.prepareStatement( // instaciei para a inserção de dados
		 * 			"INSERT INTO seller "
		 * 			+ "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
		 * 			+ "VALUES "
		 * 			+ "(?, ?, ?, ?, ?)", 
		 * 			Statement.RETURN_GENERATED_KEYS); // modo de inserção retornando o id dele
		 * 			
		 * 	
		 * 	st.setString(1, "Carl Purple"); // trocando os interrogações por dados reais
		 * 	st.setString(2, "carl@gmail.com");
		 * 	st.setDate(3, new java.sql.Date(sdf.parse("22/04/1995").getTime())); // usar o date do sql
		 * 	st.setDouble(4, 3000.00);
		 * 	st.setInt(5, 4);
		 * 	
		 * 	 int rowsAffected = st.executeUpdate(); // executeUpdate para quando for alterar dados, que retorna um inteiro de quantos linhas foram alteradas
		 * 	 
		 * 	 if(rowsAffected > 0) {
		 * 		 ResultSet rs = st.getGeneratedKeys(); // função que retorna um tipo ResultSet com o/s valor/es do id do novo dado
		 * 		 
		 * 		 while(rs.next()) {
		 * 			 int id = rs.getInt(1); // retorna o valor da primeira coluna
		 * 			 
		 * 			 System.out.println("Done! Id = " + id);
		 * 		 }
		 * 	 } else {
		 * 		 System.out.println("Done! Rows affected: " + rowsAffected);
		 * 	 }
		 * 	
		 * } catch (SQLException e) {
		 * 	e.printStackTrace(); // mostrar no console qual o erro
	     * } catch (ParseException e) {
		 * 	e.printStackTrace();
		 * } finally {
		 * 	DB.closeStatement(st);
		 * 	DB.closeConnection();
		 * }
	*/
		
		// atualização de dados
		/*
		 * 
		 * Connection conn = null;
		 * 
		 * PreparedStatement st = null;
		 * 
		 * try {
		 * 	conn = DB.getConnection(); // conectando ao banco de dados
		 * 	
		 * 	st = conn.prepareStatement( // instanciando para atualizar dados
		 * 			"UPDATE seller "
		 * 			+ "SET BaseSalary = BaseSalary + ? "
		 * 			+ "WHERE "
		 * 			+ "(DepartmentId = ?)");
		 * 	
		 * 	st.setDouble(1, 200.);
		 * 	st.setInt(2, 2);
		 * 	
		 * 	int rowsAffected = st.executeUpdate(); // atualizar dados e retorna quantas linhas afetadas
		 * 	
		 * 	System.out.println("Done! Rows affected: " + rowsAffected);
		 * } catch (SQLException e) {
		 * 	e.printStackTrace();
		 * } finally {
		 * 	DB.closeStatement(st);
		 * 	DB.closeConnection();
		 * }
		*/
		
		// deletar dados
		Connection conn = null;
		
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement(
					"DELETE FROM department "
					+ "WHERE "
					+ "Id = ?");
			
			st.setInt(1, 2);
			
			int rowsAffected = st.executeUpdate();
			
			System.out.println("Done! Rows affected: " + rowsAffected);
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
