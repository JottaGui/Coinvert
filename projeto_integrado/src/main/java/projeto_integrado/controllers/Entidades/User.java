package projeto_integrado.controllers.Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class User {
@NotBlank
public	String nome;
@NotBlank
public String senha;
@NotBlank
public String email;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
	public User() {
	super();
}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public User(String nome, String email, String senha, int id){
		this.nome = nome;
	    this.email = email;
	    this.senha = senha;
	    this.id = id;		
		
	}
	
	public User(String nome, String senha, String email) {
		super();
		this.nome = nome;
		this.senha = senha;
		this.email = email;
	}
	public User(String email, String senha) {
		super();
		this.senha = senha;
		this.email = email;
	}
	

}
