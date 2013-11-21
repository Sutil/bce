package br.com.biblioteca.app.userType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;

import br.com.biblioteca.app.obra.DisposicaoParaEmprestimo;


public class DisposicaoParaEmprestimoUserType extends ImmutableUserType{
	
	private static final long serialVersionUID = 1L;

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException, SQLException {
		String value = rs.getString(names[0]);
		if(rs.wasNull()){
			return null;
		}
		else{
			return DisposicaoParaEmprestimo.fromString(value);
		}
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index) throws HibernateException, SQLException {
		 if (value == null) {
             st.setNull(index, Types.VARCHAR);
     } else {
             DisposicaoParaEmprestimo disposicao = (DisposicaoParaEmprestimo) value;
             st.setString(index, disposicao.getDescricao());
     }
		
	}

	@Override
	public Class<?> returnedClass() {
		return DisposicaoParaEmprestimo.class;
	}

	@Override
	public int[] sqlTypes() {
		return new int[] {Types.VARCHAR};
	}

}
