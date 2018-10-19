<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="login.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="dao" class="mvc.model.DAO"/>
 


 <form action = "loga">
<fieldset>
    <legend>Sign in</legend>

    <div>
        <label for="userName">Usuario:</label>
        <input type="text" id="usuario" name="usuario"
               required />
	<%;
 	 System.out.println("ta no form");
	%>
	</ul>

    </div>

    <div>
        <label for="password">Senha:</label>

        <input type="text"  id="senha" name="senha"
                required
               placeholder="8 characters minimum" />
    </div>

    <input type="submit" value="Entrar">

</fieldset>
 </form>
</body>

</html>