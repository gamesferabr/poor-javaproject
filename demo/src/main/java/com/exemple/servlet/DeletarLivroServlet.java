package com.exemple.servlet;

import java.io.IOException;

import com.exemple.dao.LivroDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "DeletarLivroServlet", urlPatterns = {"/deletarLivro"})
public class DeletarLivroServlet extends HttpServlet {
    private LivroDAO livroDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        livroDAO = new LivroDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr == null || idStr.isEmpty()) {
            response.sendRedirect("listarLivros");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            response.sendRedirect("listarLivros");
            return;
        }

        livroDAO.excluir(id);
        response.sendRedirect("listarLivros");
    }
}
