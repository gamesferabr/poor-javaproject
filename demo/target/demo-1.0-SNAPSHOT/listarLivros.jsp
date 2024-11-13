<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Livros</title>
    <style>
        /* Seu CSS aqui */
    </style>
</head>
<body>
    <div class="container">
        <h1>Livros Disponíveis</h1>
        <a href="inserirLivro.jsp">Adicionar Novo Livro</a>
        <br><br>
        <c:choose>
            <c:when test="${empty livros}">
                <p>Nenhum livro encontrado.</p>
            </c:when>
            <c:otherwise>
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Título</th>
                        <th>Autor</th>
                        <th>Ano</th>
                        <th>Editora</th>
                        <th>Ações</th>
                    </tr>
                    <c:forEach var="livro" items="${livros}">
                        <tr>
                            <td>${livro.id}</td>
                            <td>${livro.titulo}</td>
                            <td>${livro.autor}</td>
                            <td>${livro.anoPublicacao}</td>
                            <td>${livro.editora}</td>
                            <td>
                                <a href="editarLivro?id=${livro.id}">Editar</a>
                                <a href="deletarLivro?id=${livro.id}" onclick="return confirm('Tem certeza que deseja excluir este livro?');">Excluir</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
