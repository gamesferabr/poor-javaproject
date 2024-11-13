<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Livro</title>
    <style>
        body { font-family: Arial, sans-serif; }
        .container { width: 50%; margin: auto; }
        .error { color: red; }
    </style>
</head>
<body>
    <div class="container">
        <h1>Editar Livro</h1>
        
        <% 
            // Recupera a mensagem de erro, se houver
            String mensagemErro = (String) request.getAttribute("mensagemErro");
            if (mensagemErro != null && !mensagemErro.isEmpty()) {
        %>
            <p class="error"><%= mensagemErro %></p>
        <% 
            }
            
            // Recupera o objeto Livro do atributo de requisição
            com.exemple.model.Livro livro = 
                (com.exemple.model.Livro) request.getAttribute("livro");
            if (livro == null) {
        %>
            <p class="error">Livro não encontrado.</p>
            <a href="listarLivros">Voltar para a Lista de Livros</a>
        <% 
            } else { 
        %>
            <form action="atualizarLivro" method="post">
                <input type="hidden" name="id" value="<%= livro.getId() %>">
                
                <label for="titulo">Título:</label><br>
                <input type="text" id="titulo" name="titulo" value="<%= livro.getTitulo() %>" required><br><br>
                
                <label for="autor">Autor:</label><br>
                <input type="text" id="autor" name="autor" value="<%= livro.getAutor() %>" required><br><br>
                
                <label for="editora">Editora:</label><br>
                <input type="text" id="editora" name="editora" value="<%= livro.getEditora() %>" required><br><br>
                
                <label for="anoPublicacao">Ano de Publicação:</label><br>
                <input type="number" id="anoPublicacao" name="anoPublicacao" value="<%= livro.getAnoPublicacao() %>" required><br><br>
                
                <input type="submit" value="Atualizar Livro">
            </form>
            
            <br>
            <a href="listarLivros">Voltar para a Lista de Livros</a>
        <% 
            } 
        %>
    </div>
</body>
</html>
