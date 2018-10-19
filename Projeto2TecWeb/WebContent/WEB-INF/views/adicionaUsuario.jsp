<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div style = "text-align:center"><h1>Preencha suas informações: </h1></div>


<section class = "container">

<div class = "col-lg-4"></div>

 <form  action = "cria" method = "post">
 
		<div class = "form-group">
		<label>Nome:</label>
		<input type = "text" name = "primeiro_nome">
		</div>
      	<div class = "form-group">
		<label>Sobrenome:</label>
      	<input type = "text" name = "sobrenome">
      	</div>
      	
      	<div class = "form-group">
		<label>Tipo:</label>
      	<input type = "text" name = "tipo" /><br/>
		</div>
		
		<div class = "form-group">
		<label>email:</label>
		<input type = "text" name = "email" /><br/>
		</div>
		
		<div class = "form-group">
		<label>Usuário:</label>
		<input type = "text" name = "usuario" /><br/>
		
		</div>
		
		<div class = "form-group">
		<label>Senha:</label>
		<input type = "senha" name = "senha" />
		</div>
      
      <input type = "submit" value = "Criar" />
   </form>

<div class = "col-lg-4"></div>


</section>
</body>
</html>