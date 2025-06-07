package projeto_integrado.Infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
    public Connection getConnection() {
	        try {
	            String url = "jdbc:mysql://localhost:3306/projeto_integrdo";
	            String usuario = "root";
	            String senha = "12345678";

	            return DriverManager.getConnection(url, usuario, senha);
	        } catch (SQLException e) {
	            throw new RuntimeException("Erro ao conectar ao banco de dados!", e);
	        }
	    }
}
