<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inserir Livro</title>
    <style>
        body { font-family: Arial, sans-serif; }
        .container { width: 50%; margin: auto; }
        .error { color: red; }
    </style>
</head>
<body>
    <div class="container">
        <h1>Inserir Novo Livro</h1>
        
        <!-- Exibe mensagens de erro, se houver -->
        <%
            String mensagemErro = (String) request.getAttribute("mensagemErro");
            if (mensagemErro != null && !mensagemErro.isEmpty()) {
        %>
            <p class="error"><%= mensagemErro %></p>
        <% 
            }
        %>
        
        <form action="inserirLivro" method="post">
            <label for="titulo">Titulo:</label><br>
            <input type="text" id="titulo" name="titulo" required><br><br>
            
            <label for="autor">Autor:</label><br>
            <input type="text" id="autor" name="autor" required><br><br>
            
            <label for="editora">Editora:</label><br>
            <input type="text" id="editora" name="editora" required><br><br>
            
            <label for="anoPublicacao">Ano:</label><br>
            <input type="number" id="anoPublicacao" name="anoPublicacao" required><br><br>
            
            <input type="submit" value="Inserir Livro">
        </form>
        
        <br>
        <a href="listarLivros">Voltar para a Lista de Livros</a>
    </div>
</body>
</html>
