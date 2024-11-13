package com.exemple.servlet;

import com.exemple.dao.LivroDAO;
import com.exemple.model.Livro;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListarLivrosServlet", urlPatterns = {"/listarLivros"})
public class ListarLivrosServlet extends HttpServlet {

    private LivroDAO livroDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        livroDAO = new LivroDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Livro> livros = livroDAO.listar();
        request.setAttribute("livros", livros);
        request.getRequestDispatcher("listarLivros.jsp").forward(request, response);
    }
}
