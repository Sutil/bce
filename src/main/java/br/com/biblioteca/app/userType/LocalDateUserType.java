package br.com.biblioteca.app.userType;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.joda.time.LocalDate;


public class LocalDateUserType extends ImmutableUserType {

	private static final long serialVersionUID = 1L;

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, Object owner)
			throws HibernateException, SQLException {
		Date value = rs.getDate(names[0]);
		if (rs.wasNull()) {
			return null;
		}
		return new LocalDate(value);
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index)
			throws HibernateException, SQLException {
		if (value == null) {
			st.setNull(index, Types.DATE);
		}
		else {
			LocalDate localDate = (LocalDate) value;
			st.setDate(index, new Date(localDate.toDate().getTime()));
		}
	}

	@Override
	public Class<?> returnedClass() {
		return LocalDate.class;
	}

	@Override
	public int[] sqlTypes() {
		return new int[] { Types.DATE };
	}

}
