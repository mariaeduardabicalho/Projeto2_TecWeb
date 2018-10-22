<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>

<html>


<head>

<%@ page import="java.util.*,mvc.model.*" %>
<%@ page import="java.util.*,mvc.controller.*" %>


<link  href="http://fonts.googleapis.com/css?
family=Reenie+Beanie:regular" 
rel="stylesheet"
type="text/css">
  
<link type="text/css" href="/notas.css" rel="stylesheet">

<meta charset="ISO-8859-1">


<title>Mural</title>

<style type="text/css">
*{
  margin:0;
  padding:0;
}
body{
  font-family:Poppins,arial,sans-serif;
  font-size:100%;
  margin:3em;
  background:#141A26;
  color:#fff;
  
}
h0{
	font-size:250%;
}
h2,p{
  font-size:100%;
  font-weight:normal;
}
ul,li{
  list-style:none;
}
ul{
  overflow:hidden;
  padding:3em;
}
a{
 
  color:#000;
/*   background:#ffc; */
  display:block;
  height:20em;
  width:20em;
  padding:1em;
  font-family:"Poppins",arial,sans-serif;
  
}
ul li{
  margin:1em;
  float:
  }
  
  ul li h6 {
  font-size:150%;
  font-weight:bold;
  padding-bottom:10px;
}
ul li p h0 h2{
  font-family:"Poppins",arial,sans-serif;
  font-size:200%;
}
 input[type=text]{
    font-family:"Poppins",arial,sans-serif;
  	font-size:150%;

    color: #223254;
    background:#ffc;
  
            
}

input[type=submit]{
    font-family:"Poppins",arial,sans-serif;
  	font-size:100%;
    color: #223254;
    background:#fff;
    border-radius: 12px;           
}

h6{
	font-size:150%;
}
h1{
	font-size:200%;
}
</style>

<%String gif_url = (String) request.getAttribute("gif_url");%>

</head>
<%-- String usuario = c:out value= usuario ;
 	 System.out.println(usuario);
	--%>
	
<body>



<h1>Mural de Notas</h1>



	<jsp:useBean id="dao" class="mvc.model.DAO"/>
 
 <c:if test = "${sessionScope.usuarioLogado == null}">

	  <form action="Login" method="post">
   
    <input type="submit" value="Fazer Login" />
     </form>
   	<h6> Ainda não é cadastrado?</h6>
    
    
    <form action="cadastro" >
    		<input type="submit"  value="Cadastrar" />
    </form>
  
	 </c:if>
	
	<ul>
	
	<c:forEach var="nota" items="${dao.lista}" varStatus="id">

 		<li>
			<a style = "background:#${nota.categoria == 'aviso'? 'FF5359' : 'B9F1FF' }">
			 
			 
			 <form action="edita" method="post">
			 <input type="hidden" name="usuario" value=${usuario}>
			 
			<p> ${nota.nome_doc} </p> <br/>
		     
		
			 <c:if test = "${nota.conteudo == 'nota com imagem'}">
			 <img src="data:image/jpg;base64,${nota.base64Image}" width="80" height="53"/>
			 </c:if>
			 
		     <input type = "hidden" name = "nome_doc" value= ${nota.nome_doc} ><br/>
			 
		     <input type = "hidden" name = "tipo_doc" value= ${nota.tipo_doc} ><br/>
		     <input type = "text" name = "conteudo" value= ${nota.conteudo} ><br/>
		     <input type="hidden" name="notaid" value = ${nota.id}>
		     <input type="hidden" name="usuarioid" value = ${nota.usuarioid}>
		 
		    <h6> ${dao.pegarnome(Integer.parseInt(nota.usuarioid))} ${dao.pegarsobrenome(Integer.parseInt(nota.usuarioid))} </h6>
		      <h6> ${nota.data_postagem} </h6> <br/>
		    
		     
		      <input type="submit" value="Editar" />
		   </form> </a>
		
			
		   <form action="apaga" method="post">
		   
		   	<input type="hidden" name="id" value=${nota.id}>
		   	<input type="hidden" name="username" value=${usuario}>
		   
		   <input type="submit" value="Apagar" />
		   </form>

	</li>
	</c:forEach>
	</ul>
 	
 	<form action="apaga" method="post">
   
   	<input type="hidden" name="id" value=${nota.id}>
   	<input type="hidden" name="username" value=${usuario}>
   
   <input type="submit" value="Apagar" />
   </form>
 	 
 <c:if test = "${sessionScope.usuarioLogado!= null}">

  <form action="adicionar" >
    
   	<input type="hidden" name="usuario" value=${usuario}>
   	
   
    <input type="submit" value="Adicionar Nota" />
   </form>
   
 </c:if>
	
   
   
   <form action="logout" >
    
    <input type="submit" value="Logout" />
   </form>
   

<div class="form">
	<form class="login-form" action = "buscaGif">
		<input type = "text" name="palavra_gif">
		<input id="home" class="button" type = "submit" value="Buscar Giphy">
	</form></div>
   
	<img src="<%=gif_url%>" class="center" style="float:center;"/>
	
	
	
</body>
</html>