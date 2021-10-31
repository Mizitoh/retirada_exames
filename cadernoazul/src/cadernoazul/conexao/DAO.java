package cadernoazul.conexao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {
	
	private Connection conexao;
	
	public int incluir(String sql, Object... atributos) {
		try {
			PreparedStatement stmt = pegaConexao().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			adicionarAtributos(stmt, atributos);
			
			if(stmt.executeUpdate() > 0) {
				ResultSet resultado = stmt.getGeneratedKeys();
				
				if(resultado.next()) {
					return resultado.getInt(1);
				}
			}
			return -1;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	private void adicionarAtributos(PreparedStatement stmt, Object[] atributos) throws SQLException{
		
		int indice = 1;
		for (Object atributo: atributos) {
			if(atributo instanceof String) {
				stmt.setString(indice, (String) atributo);
			} else if(atributo instanceof Integer) {
				stmt.setInt(indice, (Integer) atributo);
			} else if(atributo instanceof Date) {
				stmt.setDate(indice, (Date) atributo);
			}
			
			indice++;
		}
	}
	
	private Connection pegaConexao() throws SQLException {
		try {
			if (conexao != null && !conexao.isClosed()) {
				return conexao;
		}
			
		} catch (SQLException e) {
			
		}
		
		conexao = FabricaConexao.captaConexao();
		return conexao;
	}
	
	public void fecha() {
		try {
			pegaConexao().close();
		} catch (SQLException e) {
		}
		finally {
			conexao = null;
		}
	}
	
	public ResultSet ExecuteSQL(String sql) {
        try {
            
            PreparedStatement stmt = pegaConexao().prepareStatement(sql);
            ResultSet resultset = stmt.executeQuery(sql);
            return resultset;            
            }  catch (SQLException sqlex) {
            /*JOptionPane.showMessageDialog(null, "nao foi possivel executar o comando sql, "
                    + sqlex + ", o sql passado foi " + sql);*/
        }
        return null;
    }

}
