package projeto_integrado.Infra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import projeto_integrado.controllers.Entidades.User;

@Controller
public class UserDAO {

   @PostMapping 
    public String loginUsuario(User user) {
    	String query = "SELECT * FROM user WHERE email = ?;";
    	try (Connection conexao = new JDBC().getConnection();
             PreparedStatement lg = conexao.prepareStatement(query)) {

    	    lg.setString(1, user.getEmail());
    	    ResultSet rs = lg.executeQuery(); 
    	    
    	    if (rs.next()) {
    	        String senhaBanco = rs.getString("senha"); 
    	        
    	        if (senhaBanco.equals(user.getSenha())) {
    	            return "ok";
    	        } else {  
    	        	 return "Senha incorreta!";
    	        }
    	    } else {
    	    	 return "Usuário não encontrado!";
    	    }
    	} catch (SQLException e) {
    	    e.printStackTrace();
    	}
		return "Erro ao conectar com o banco de dados.";    	
    }
    }

