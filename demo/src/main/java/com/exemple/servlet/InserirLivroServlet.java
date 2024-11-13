package com.exemple.servlet;

import com.exemple.util.DatabaseConnection;
import com.exemple.model.Livro;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "InserirLivroServlet", urlPatterns = {"/inserirLivro"})
public class InserirLivroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, 
    HttpServletResponse response) throws ServletException, IOException {
        
        // Recupera os parâmetros do formulário
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        String editora = request.getParameter("editora");
        String anoPublicacaoStr = request.getParameter("anoPublicacao");

        // Validação básica dos dados
        if (titulo == null || titulo.isEmpty() ||
            autor == null || autor.isEmpty() ||
            editora == null || editora.isEmpty() ||
            anoPublicacaoStr == null || anoPublicacaoStr.isEmpty()) {
            // Se algum campo estiver vazio, redireciona de volta com uma mensagem de erro
            request.setAttribute("mensagemErro", "Todos os campos são obrigatórios.");
            request.getRequestDispatcher("inserirLivro.jsp").forward(request, response);
            return;
        }

        int anoPublicacao;
        
        try {
            anoPublicacao = Integer.parseInt(anoPublicacaoStr);
        } catch (NumberFormatException e) {
            // Se o ano de publicação não for um número válido
            request.setAttribute("mensagemErro", "Ano de Publicação inválido.");
            request.getRequestDispatcher("inserirLivro.jsp").forward(request, response);
            return;
        }

        Livro livro = new Livro(titulo, autor, editora, anoPublicacao);

        // Inserir o livro no banco de dados
        String sql = "INSERT INTO livros (titulo, autor, editora, ano_publicacao) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, livro.getTitulo());
            pstmt.setString(2, livro.getAutor());
            pstmt.setString(3, livro.getEditora());
            pstmt.setInt(4, livro.getAnoPublicacao());

            int linhasInseridas = pstmt.executeUpdate();

            if (linhasInseridas > 0) {
                // Redireciona para a página de listagem de livros após a inserção
                response.sendRedirect("listarLivros.jsp");
            } else {
                // Se a inserção não ocorreu como esperado
                request.setAttribute("mensagemErro", "Erro ao inserir o livro.");
                request.getRequestDispatcher("inserirLivro.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Em caso de erro no banco de dados
            request.setAttribute("mensagemErro", "Erro ao inserir o livro: " + e.getMessage());
            request.getRequestDispatcher("inserirLivro.jsp").forward(request, response);
        }
    }
}
