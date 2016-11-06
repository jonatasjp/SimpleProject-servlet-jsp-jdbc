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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		
		Usuario usuario = new Usuario();
		if(id != null && !id.isEmpty()){
			usuario.setId(Integer.parseInt(id));
		}
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		try {
			usuarioDAO.deletar(usuario);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		resp.getWriter().append(
				"<html>"
				+ "exclusão realizada com sucesso!"
				+ "</html>"
				);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nome = req.getParameter("nome");
		String sobrenome = req.getParameter("sobrenome");
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setSobrenome(sobrenome);
		usuario.setLogin(login);
		usuario.setSenha(senha);
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		try {
			usuarioDAO.cadastrar(usuario);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		resp.getWriter().append(
				"<html>"
				+ "Cadastro realizado com sucesso!"
				+ "</html>"
				);
	}

}
