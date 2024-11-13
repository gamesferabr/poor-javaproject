package com.exemple.servlet;
import java.io.IOException;

import com.exemple.dao.LivroDAO;
import com.exemple.model.Livro;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "AtualizarLivroServlet", urlPatterns = {"/atualizarLivro"})
public class AtualizarLivroServlet extends HttpServlet {
     private LivroDAO livroDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        livroDAO = new LivroDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        String editora = request.getParameter("editora");
        String anoPublicacaoStr = request.getParameter("anoPublicacao");

        // Validação básica dos dados
        if (idStr == null || idStr.isEmpty() ||
            titulo == null || titulo.isEmpty() ||
            autor == null || autor.isEmpty() ||
            editora == null || editora.isEmpty() ||
            anoPublicacaoStr == null || anoPublicacaoStr.isEmpty()) {
            request.setAttribute("mensagemErro", "Todos os campos são obrigatórios.");
            request.getRequestDispatcher("editarLivro.jsp?id=" + idStr).forward(request, response);
            return;
        }

        int id, anoPublicacao;
        try {
            id = Integer.parseInt(idStr);
            anoPublicacao = Integer.parseInt(anoPublicacaoStr);
        } catch (NumberFormatException e) {
            request.setAttribute("mensagemErro", "ID ou Ano de Publicação inválido.");
            request.getRequestDispatcher("editarLivro.jsp?id=" + idStr).forward(request, response);
            return;
        }

        Livro livro = new Livro(id, titulo, autor, editora, anoPublicacao);
        livroDAO.atualizar(livro);
        response.sendRedirect("listarLivros");
    }
}
