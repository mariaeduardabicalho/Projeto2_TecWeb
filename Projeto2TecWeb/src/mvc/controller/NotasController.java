package mvc.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.*;

import mvc.model.DAO;
import mvc.model.Notas;
import mvc.model.Usuario;
import com.sendgrid.*;
import org.springframework.web.multipart.MultipartFile;

@Controller

public class NotasController {
	
	@RequestMapping("/")
	public String executa(HttpSession session, Model model) throws SQLException, IOException, ParseException {
		String gif_url = (String) session.getAttribute("palavra_gif");
		model.addAttribute("gif_url",gif_url);
		return "notas";
	}

	@RequestMapping( value = "/adicionar")
	public String encaminhar(){
	
	return "adicionaNota";
	}
	
	@RequestMapping( value = "/notaTexto")
	public String encaminhar_texto(){
	
	return "notaTexto";
	}
	@RequestMapping( value = "/notaImagem")
	public String encaminhar_imagem(){
	
	return "notaImagem";
	}
	
	@RequestMapping(value = "/posta",headers = "content-type=multipart/form-data", method = RequestMethod.POST)
	public String adiciona(@RequestParam(value = "nome_doc") String nomedoc,
			@RequestParam(value = "conteudo") String conteudo,@RequestParam(value = "usuario") String usuario, @RequestParam(value = "tipo_doc") String tipodoc, @RequestParam(value = "arquivo") MultipartFile filePart) throws SQLException, IOException {
	
	DAO dao = null;
	InputStream fileContent = null;
	 if(conteudo.equals("nota com imagem")) {
		   fileContent = (InputStream) filePart.getInputStream();
		    }
    InputStream stream = new ByteArrayInputStream("sem imagem".getBytes(StandardCharsets.UTF_8));

	try {
		dao = new
		DAO();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	Notas nota = new Notas();
	
	if(conteudo.equals("nota com imagem")) {
	nota.setNome_doc(nomedoc);
	nota.setConteudo(conteudo);
	nota.setTipo_doc(tipodoc);
	Integer usuarioid = dao.pegarId(usuario);
	nota.setUsuarioid(usuarioid);
	nota.setImagem(fileContent);
	
	dao.adiciona(nota);
	
	}
	else {
		nota.setNome_doc(nomedoc);
		nota.setConteudo(conteudo);
		nota.setImagem(stream);
		nota.setTipo_doc(tipodoc);
		Integer usuarioid = dao.pegarId(usuario);
		nota.setUsuarioid(usuarioid);
		dao.adiciona(nota);
		
		
		
	}
	List<Usuario> usuarios = dao.getListau();
//	
//	for (Usuario usuario1 : usuarios ) {
//		try {
//			main(usuario1.getEmail());
//			
//		}
//		catch(IOException ex){
//			throw ex;
//		}
//		
//	}
	return "redirect:/";
	

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
	
	@RequestMapping("buscaGif") //buscar gif
	public String gif(HttpSession session,
			@RequestParam(value = "palavra_gif") String gif) throws Exception{
		giphy(gif, session);
		
		
		return "redirect:/";
	}
	
	public String giphy(String palavra, HttpSession session) throws IOException{
		
		String  tag = palavra;
		
		String site = "https://api.giphy.com/v1/gifs/random?api_key=h5gI07ghz7ZRvE0VAlQk9sduQ2cud697&tag="+tag+"&rating=R";
		
		site = site.replace(" ","%20");
		
		URL url = new URL(site);
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("GET");
		con.connect();
		
		int resposta = con.getResponseCode(); 
		String inline = "";
		if(resposta != 200)
			throw new RuntimeException("HttpResponseCode: " +resposta);
			else
			{
	
				Scanner sc = new Scanner(url.openStream());
				while(sc.hasNext())
				{
				inline+=sc.nextLine();
				}
	
				sc.close();
				
				JsonElement root = new JsonParser().parse(inline);
				String gif = root.getAsJsonObject().get("data").getAsJsonObject().get("images").getAsJsonObject().get("fixed_height").getAsJsonObject().get("url").getAsString();
				
				session.setAttribute("palavra_gif", gif);
				return gif;
			}
	}
	  public static void main(String remetente) throws IOException {
		    Email from = new Email("pedrooa@al.insper.edu.br");
		    String subject = "Acabaram de publicar no mural!";
		    Email to = new Email(remetente);
		    Content content = new Content("text/plain", "Acesse o mural para ver o que foi publicado: http://localhost:12032/Projeto2TecWeb/");
		    Mail mail = new Mail(from, subject, to, content);

		    SendGrid sg = new SendGrid("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");
		    Request request = new Request();
		    try {
		      request.setMethod(Method.POST);
		      request.setEndpoint("mail/send");
		      request.setBody(mail.build());
		      Response response = sg.api(request);
		      System.out.println(response.getStatusCode());
		      System.out.println(response.getBody());
		      System.out.println(response.getHeaders());
		    } catch (IOException ex) {
		      throw ex;
		    }
		  }

}
