package mvc.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import mvc.model.DAO;
import mvc.model.Notas;
import mvc.model.Usuario;

@Controller

public class NotasController {
@RequestMapping("/")
public String executa() {
return "notas";
}

@RequestMapping( value = "/adicionar")
public String encaminhar(){

return "adicionaNota";
}

@RequestMapping(value = "/posta")
public String adiciona(@RequestParam(value = "nome_doc") String nomedoc,
		@RequestParam(value = "conteudo") String conteudo,@RequestParam(value = "usuario") String usuario, @RequestParam(value = "tipo_doc") String tipodoc) throws SQLException {
DAO dao = null;
try {
	dao = new
	DAO();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
Notas nota = new Notas();
nota.setNome_doc(nomedoc);
nota.setConteudo(conteudo);
nota.setTipo_doc(conteudo);
Integer usuarioid = dao.pegarId(usuario);
nota.setUsuarioid(usuarioid);
dao.adiciona(nota);
return "notas";
}

@RequestMapping(value = "/apaga", method = RequestMethod.POST)

public String apaga(@RequestParam(value = "id") Integer notaid) throws SQLException {
DAO dao = null;
try {
	dao = new
	DAO();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
dao.apaga(notaid);

dao.close();
return "notas";
}

@RequestMapping(value = "/edita", method = RequestMethod.POST)

public String edita(@RequestParam(value = "nome_doc") String nomedoc, @RequestParam(value = "tipo_doc") String tipodoc,
@RequestParam(value = "conteudo") String conteudo, @RequestParam(value = "notaid") Integer notaid,@RequestParam(value = "usuarioid") Integer usuarioid) throws SQLException {

System.out.println("chegou no edita ");
	DAO dao = null;
try {
	dao = new
	DAO();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
Notas nota = new Notas();
nota.setNome_doc(nomedoc);
nota.setConteudo(conteudo);
nota.setTipo_doc(tipodoc);
nota.setId(notaid);
nota.setUsuarioid(usuarioid);

dao.edita(nota);

dao.close();
return "notas";
}

@RequestMapping(value = "/ficarLogado", method = RequestMethod.GET)
public String direciona() {
	return "adicionaNota";
}

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
