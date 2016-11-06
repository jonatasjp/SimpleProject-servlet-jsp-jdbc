package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Usuario;
import persistencia.UsuarioDAO;

@WebServlet("/usuController.do")
public class UsuarioController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("passou aqui");
		String a = req.getParameter("nome");
		String b = req.getParameter("sobrenome");
		String c = req.getParameter("login");
		String d = req.getParameter("senha");
		
		Usuario u = new Usuario();
		u.setNome(a);
		u.setSobrenome(b);
		u.setLogin(c);
		u.setSenha(d);
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		try {
			usuarioDAO.cadastrar(u);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		resp.getWriter().append(
				"<html>"
				+ "eu quero ver se imprime algo na tela"
				+ "</html>"
				);
		System.out.println("passou aqui");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("passou aqui");
		super.doPost(req, resp);
		System.out.println("passou aqui");
	}

}
