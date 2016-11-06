package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.ConnectionFactory;
import model.Usuario;

public class UsuarioDAO {

	private Connection connection = ConnectionFactory.getConnection();

	public void cadastrar(Usuario usuario) throws SQLException {
		String sql = "insert into usuario (nome, sobrenome, login, senha) values (?, ?, ?, ?)";
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, usuario.getNome());
			statement.setString(2, usuario.getSobrenome());
			statement.setString(3, usuario.getLogin());
			statement.setString(4, usuario.getSenha());

			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void alterar(Usuario usuario) throws SQLException {
		String sql = "update usuario set nome = ?,  sobrenome = ?, login = ?, senha = ? where id = ?";
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, usuario.getNome());
			statement.setString(2, usuario.getSobrenome());
			statement.setString(3, usuario.getLogin());
			statement.setString(4, usuario.getSenha());
			statement.setInt(5, usuario.getId());

			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void deletar(Usuario usuario) throws SQLException {
		String sql = "delete from usuario where id = ?";
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, usuario.getId());

			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<Usuario>();
        ResultSet rs;
        PreparedStatement statement = null;
        try {
            String sql = "select * from usuario order by id";
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {

                Usuario usuario = new Usuario();
                usuario.setNome(rs.getString("nome"));
                usuario.setSobrenome(rs.getString("sobrenome"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setId(rs.getInt("id"));
                lista.add(usuario);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
        	try {
//        		statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
        return lista;
    }
	
	public Usuario buscarPorId(Usuario usu) {
        ResultSet rs;
        PreparedStatement statement = null;
        Usuario usuario = null;
        try {
            String sql = "select * from usuario where id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, usu.getId());
            rs = statement.executeQuery();
            if(rs.next()) {

                usuario = new Usuario();
                usuario.setNome(rs.getString("nome"));
                usuario.setSobrenome(rs.getString("sobrenome"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setId(rs.getInt("id"));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
        	try {
//        		statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
        return usuario;
    }

	public static void main(String[] args) throws SQLException {

		Usuario u = new Usuario();
		u.setNome("TESTE ALTERAR");
		u.setSobrenome("luis");
		u.setLogin("jonatas");
		u.setSenha("12345");
		u.setId(2);

		UsuarioDAO dao = new UsuarioDAO();
//		dao.deletar(u);
		
		u = dao.buscarPorId(u);
		System.out.println(u);
	}

}
