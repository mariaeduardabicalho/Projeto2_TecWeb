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
	public String executa(HttpSession session) throws SQLException, IOException, ParseException {
		//String gif_url = (String) session.getAttribute("palavra_gif");
		//model.addAttribute("gif_url",gif_url);
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
			@RequestParam(value = "conteudo") String conteudo,@RequestParam(value = "usuario") String usuario, @RequestParam(value = "tipo_doc") String tipodoc,@RequestParam(value = "palavra_gif") String palavra_gif, @RequestParam(value = "arquivo") MultipartFile filePart) throws SQLException, IOException {
	
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
	String gif = palavra_gif.substring(0, palavra_gif.length() - 1);
	if(conteudo.equals("nota com imagem")) {
	nota.setNome_doc(nomedoc);
	nota.setConteudo(conteudo);
	nota.setTipo_doc(tipodoc);
	Integer usuarioid = dao.pegarId(usuario);
	nota.setUsuarioid(usuarioid);
	nota.setImagem(fileContent);
	nota.setpalavra_gif(gif);
	
	dao.adiciona(nota);
	
	}
	else {
		nota.setNome_doc(nomedoc);
		nota.setConteudo(conteudo);
		nota.setImagem(stream);
		nota.setTipo_doc(tipodoc);
		Integer usuarioid = dao.pegarId(usuario);
		nota.setUsuarioid(usuarioid);
		nota.setpalavra_gif(gif);
		System.out.println("NO CONTROLLER");
		System.out.println(gif);
		dao.adiciona(nota);
		
		
		
	}
	List<Usuario> usuarios = dao.getListau();
	
	for (Usuario usuario1 : usuarios ) {
		try {
			dao.main(usuario1.getEmail());
		}
		catch(IOException ex){
			throw ex;
		}
		
	}
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
	
	
	
	
	@RequestMapping("buscaGif") //buscar gif
	public String gif( Model model, @RequestParam(value = "palavra_gif") String gif) throws Exception{
		
		DAO dao = null;
		try {
			dao = new
			DAO();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String gif_url = dao.gif(gif);
		model.addAttribute("gif_url",gif_url);
		dao.close();
		
		return "adicionaNota";
	}
	

	
}
