package mvc.controller;

import java.sql.SQLException;
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
	
//	@RequestMapping("/cadastro")
//	public String direciona() {
//		return "adicionaUsuario";
//	}
//	
//
//	@RequestMapping(value = "/cria", method = RequestMethod.POST)
//	public String cria(@RequestParam(value = "primeiro_nome") String nome, @RequestParam(value = "sobrenome") String sobrenome,
//		@RequestParam(value = "tipo") String tipo, @RequestParam(value = "email") String email, @RequestParam(value = "usuario") String usuario1,@RequestParam(value = "senha") String senha) throws SQLException {
//		
//		DAO dao = null;
//		try {
//			dao = new DAO();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Usuario usuario = new Usuario();
//		
//		usuario.setPrimeiro_nome(nome);
//		usuario.setSobrenome(sobrenome);
//		usuario.setEmail(email);
//		usuario.setTipo(tipo);
//		usuario.setUsuario(usuario1);
//		usuario.setSenha(senha);
//		
//		try {
//			dao.adicionau(usuario);
//			System.out.println("entrou");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		dao.close();
//		return "notas";
// }
//	
//	
//	@RequestMapping("/Login")
//	public String mandar(){
//	
//	return "login";
//	}
//	
//	
//	
//	@RequestMapping(value = "/loga")
//	public String apaga(@ModelAttribute Usuario usuariot, @RequestParam(value = "usuario") String usuario1, @RequestParam(value = "senha") String senha,ModelMap model) throws SQLException {
//	System.out.println("entreeei");
//		
//	DAO dao = null;
//	
//	try {
//		dao = new DAO();
//	} catch (SQLException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	
//	
//	
//	Usuario usuario = new Usuario();
//	
//	try {
//		//System.out.println("no try");
//		//System.out.println(dao.getListau());
//		
//		boolean check = false;
//		for (Usuario usuariol:dao.getListau()) {
//
//			 if (usuario1.equals( usuariol.getUsuario()) && senha.equals( usuariol.getSenha())) {
//				 //System.out.println("ENTROU NO IF");
//				 usuario.setUsuario(usuario1);
//				 model.addAttribute("usuario", usuario.getUsuario());
//				 //usuario.setSenha(request.getParameter("password"));
////				 request.setAttribute("usuario", usuario.getUsuario());
////				 request.getRequestDispatcher("notes.jsp").forward(request, response);
//				 
//				 //System.out.println("entrou");
//				 
//				 
//				
//				
//				 check = true;
//			 }
//			 	 
//		 }
//	if (check == false){
//		System.out.println("Enao entrou no if else");
//		
//		model.addAttribute("invalid", true);
//	    
////		 response.sendRedirect("login.jsp?invalid=true");
////		 request.setAttribute("invalid", true);
////		 request.getRequestDispatcher("login.jsp").forward(request, response);
//		 dao.close();
//			
//	}	
//	 
//	} catch (SQLException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	
//	dao.close();
//	return "notas";
//	}
}
