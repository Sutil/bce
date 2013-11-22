package br.com.biblioteca.app.userType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;

import br.com.biblioteca.app.model.Categoria;


public class CategoriaUserType extends ImmutableUserType{

	private static final long serialVersionUID = 1L;

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException, SQLException {
		int value = rs.getInt(names[0]);
		if(rs.wasNull()){
			return null;
		}
		else{
			return Categoria.fromCodigo(value);
		}
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index) throws HibernateException, SQLException {
		 if (value == null) {
             st.setNull(index, Types.INTEGER);
     } else {
             Categoria categoria = (Categoria) value;
             st.setInt(index, categoria.getCodigo());
     }
		
	}

	@Override
	public Class<?> returnedClass() {
		return Categoria.class;
	}

	@Override
	public int[] sqlTypes() {
		return new int[] {Types.INTEGER};
	}

}
