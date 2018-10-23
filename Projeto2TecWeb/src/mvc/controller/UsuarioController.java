package mvc.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import mvc.model.Usuario;
import mvc.model.DAO;

@Controller
public class UsuarioController {
	
	
	@RequestMapping("/cadastro")
	public String direcionaa() {
		return "adicionaUsuario";
	}
	
	
	@RequestMapping(value = "/cria", method = RequestMethod.POST)
	public String cria(@RequestParam(value = "primeiro_nome") String nome, @RequestParam(value = "sobrenome") String sobrenome,
		@RequestParam(value = "tipo") String tipo, @RequestParam(value = "email") String email, @RequestParam(value = "usuario") String usuario1,@RequestParam(value = "senha") String senha) throws SQLException {
		
		DAO dao = null;
		try {
			dao = new DAO();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Usuario usuario = new Usuario();
		
		usuario.setPrimeiro_nome(nome);
		usuario.setSobrenome(sobrenome);
		usuario.setEmail(email);
		usuario.setTipo(tipo);
		usuario.setUsuario(usuario1);
		usuario.setSenha(senha);
		
		try {
			dao.adicionau(usuario);
			System.out.println("entrou");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.close();
		return "notas";
	}
	
	
	@RequestMapping("/login")
	public String mandar(){
	
	return "login";
	}
	
	@RequestMapping(value = "/loga")
	public String apaga( @RequestParam(value = "usuario") String usuario1, @RequestParam(value = "senha") String senha,HttpSession session) throws SQLException {
	System.out.println("entreeei");
		
	DAO dao = new DAO();
	
		for (Usuario usuariol:dao.getListau()) {
	
			 if (usuario1.equals( usuariol.getUsuario()) && senha.equals( usuariol.getSenha())) {
				 
				 System.out.println("entrou no if ");
				 
				 session.setAttribute("usuarioLogado", usuario1);
				 return "notas";
			 }
		}
		return "login";
				 
		
	}
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
	session.invalidate();
	
	return "notas";
	}
	
	
}
