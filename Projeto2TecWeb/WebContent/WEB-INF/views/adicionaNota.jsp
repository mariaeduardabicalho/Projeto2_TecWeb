<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/styles.css">
<meta charset="UTF-8">
<title>Adicionar Nota</title>
</head>
<body>

<div style = "text-align:center"><h1>Preencha sua Nota</h1></div>

<section class = "container">

	<div class = "col-lg-4"></div>  

   <form action = "posta" class = "col-lg-4" enctype="multipart/form-data">  
	   
		<div class="form-group ">
	   		<label  for="nome_doc">Título</label>
	    	<input type="text" class="form-control" name = "nome_doc"/>
	    	
	 	</div>  
  
		<div>
		<p>Escolha o tipo da sua nota: </p>
		
		<button 
			type = "button" onClick="loadDoc('notaTexto',myFunction)" name="tipo_doc" value = "Texto">Texto
			</button>
			<button
			 type = "button" onClick="loadDoc('notaImagem',myFunction)" name="tipo_doc" value = "Imagem">Imagem
			 </button>
		</div>

		<script>
			function loadDoc(url, cFunction) {
			  var xhttp = new XMLHttpRequest();
			  xhttp.onreadystatechange = function() {
			    if (this.readyState == 4 && this.status == 200) {
			      cFunction(this);
			    }
			  };
			  xhttp.open("GET", url, true);
			  xhttp.send();
			}
			
			
			function myFunction(xhttp) {
				  document.getElementById("conteudo").innerHTML =
					  xhttp.responseText;
				} 
		</script>

		<div id = "conteudo"></div><br>  

  
		  	  
		  
		


		    <label for="categoria">Categoria: </label><br>
		    <label class = "radio-inline"><input  type="radio"  name = "categoria" value = "comum" checked> Comum</label>
  		    <label class = "radio-inline"><input  type="radio"  name = "categoria" value = "aviso"> Aviso</label><br>
		   
		    
		
		<input type="hidden"  name = "usuario" value= "${sessionScope.usuarioLogado}" />

  
		<input type = "submit" value = "Enviar" />
	</form>
	<div class = "col-lg-4"></div>
</section> 
</body>
</html>