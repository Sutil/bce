@TypeDefs({
	@TypeDef(name = "categoria", defaultForType = Categoria.class, typeClass = CategoriaUserType.class),
	@TypeDef(name = "disposicaoParaEmprestimo", defaultForType = DisposicaoParaEmprestimo.class, typeClass = DisposicaoParaEmprestimoUserType.class)
})
package br.com.biblioteca.app.userType;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import br.com.biblioteca.app.model.Categoria;
import br.com.biblioteca.app.model.DisposicaoParaEmprestimo;

