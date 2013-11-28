@TypeDefs({
	@TypeDef(name = "categoria", defaultForType = Categoria.class, typeClass = CategoriaUserType.class),
	@TypeDef(name = "disposicaoParaEmprestimo", defaultForType = DisposicaoParaEmprestimo.class, typeClass = DisposicaoParaEmprestimoUserType.class),
	@TypeDef(name = "localDate", defaultForType = LocalDate.class, typeClass = LocalDateUserType.class)
})
package br.com.biblioteca.app.userType;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.joda.time.LocalDate;

import br.com.biblioteca.app.model.Categoria;
import br.com.biblioteca.app.model.DisposicaoParaEmprestimo;

