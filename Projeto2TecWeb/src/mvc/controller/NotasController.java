package mvc.controller;

import java.sql.SQLException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import mvc.model.DAO;
import mvc.model.Notas;

@Controller

public class NotasController {
@RequestMapping("/")
public String execute() {
System.out.println("Lógica do MVC");
return "notas";
}


@RequestMapping("/posta")
public String adiciona(Notas
nota) throws SQLException {
DAO dao = null;
try {
	dao = new
	DAO();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
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
return "notas";
}

@RequestMapping(value = "/edita", method = RequestMethod.POST)

public String edita(@RequestParam(value = "usuario") String usuario) throws SQLException {
DAO dao = null;
try {
	dao = new
	DAO();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
//dao.edita(usuario);
return "notas";
}}
