package projeto_integrado.controllers.controllers_de_login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import projeto_integrado.Infra.UserDAO;
import projeto_integrado.controllers.Entidades.User;
import projeto_integrado.controllers.Entidades.Repositories.RepositorioUser;

	@Controller	
	@RequestMapping("/login")
	public class login{
		@GetMapping
			public String mostrarlogin() {
				return "Login";
			}
		@PostMapping
		public String verificaLogin(@RequestParam String email, @RequestParam String senha,HttpSession session, Model model){
			User usuario = new User(email, senha);
		  UserDAO userdao = new UserDAO();	
		String loginStatus = userdao.loginUsuario(usuario);
		if("ok".equalsIgnoreCase(loginStatus)) {
			session.setAttribute("usuariologado", usuario);
			session.setMaxInactiveInterval(900);
			return "/logado";
		} 
		else {return "erro";}
		}
		
		@Autowired
		private RepositorioUser  userRepository;
		
		@GetMapping("/recuperar-senha")
		public String mostrarFormularioRecuperarSenha() {
		    return "recuperar-senha"; 
		}
		
		@PostMapping("/recuperar-senha")
		public String recuperarSenha(@RequestParam String email, Model model) {
		    User usuario = userRepository.findByEmail(email);

		    if (usuario != null) {
		        model.addAttribute("mensagem", "Sua senha é: " + usuario.getSenha());
		    } else {
		        model.addAttribute("mensagem", "E-mail não encontrado!");
		    }

		    return "recuperar-Senha";
		}
		
	}
