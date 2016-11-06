package controller;

import java.awt.SecondaryLoop;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		
		String acao = req.getParameter("acao");
		
		if(acao != null){
			if(acao.equals("excluir")){
				excluir(req, resp);
			}else if(acao.equals("listar")){
				listar(req, resp);
			}else if(acao.equals("alterar")){
				alterar(req, resp);
			}
		}else{
			resp.sendRedirect("usuController.do?acao=listar");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String nome = req.getParameter("nome");
		String sobrenome = req.getParameter("sobrenome");
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		
		Usuario usuario = new Usuario();
		usuario.setId(Integer.parseInt(id));
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
		
		resp.sendRedirect("usuController.do?acao=listar");
	}
	
	private void excluir(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String id = req.getParameter("id");
		Usuario usuario = new Usuario();
		if (id != null && !id.isEmpty()) {
			usuario.setId(Integer.parseInt(id));
		}

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		try {
			usuarioDAO.deletar(usuario);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		resp.sendRedirect("usuController.do?acao=listar");
	}

	private void listar(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		usuarios = usuarioDAO.listar();

		req.setAttribute("usuarios", usuarios);
		req.getRequestDispatcher("WEB-INF/jsp/listarUsuarios.jsp").forward(req, resp);
	}

	private void alterar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("id");
		Usuario usuario = new Usuario();
		if(id != null && !id.isEmpty()){
			usuario.setId(Integer.parseInt(id));
		}else{
			resp.sendRedirect("usuController.do?acao=listar");
		}
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		usuario = usuarioDAO.buscarPorId(usuario);
		if(usuario != null){
			req.setAttribute("usuario", usuario);
			req.getRequestDispatcher("WEB-INF/jsp/formCadastro.jsp").forward(req, resp);
			return;
		}
		resp.sendRedirect("usuController.do?acao=listar");
	}
}
