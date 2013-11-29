package br.com.biblioteca.app.login.seguranca;

import java.util.Map;

import org.springframework.security.core.GrantedAuthority;

import com.google.common.collect.Maps;


public enum Permissao implements GrantedAuthority {

	ROLE_USER("Permissões básicas"),
	CRIAR_USUARIO("Criar Usuario"),
	EDITAR_USUARIO("Editar Usuário"),
	EXCLUIR_USUARIO("Excluir Usuário"),
	CRIAR_PERFIL("Criar Perfil"),
	EDIATAR_PERFIL("Editar Perfil"),
	EXCLUIR_PERFIL("Excluir Perfil"),
    CRIAR_EXEMPLAR("Criar Exemplar"), 
    EDITAR_EXEMPLAR("Editar Exemplar"),
    EXCLUIR_EXEMPLAR("Excluir Exemplar"),
    EMPRESTAR_EXEMPLAR("Emprestar Exemplar"),
    CADASTRAR_RENOVACAO("Cadastrar Renovação"),
    REGISTRAR_DEVOLUCOES("Registrar Devoluções");
    

    @Override
    public String getAuthority() {
            return name();
    }

    private String codigo;

    private static final Map<String, Permissao> valueMap;

    static {
            Map<String, Permissao> builder = Maps.newLinkedHashMap();

            for (Permissao permissao : values()) {
                    builder.put(permissao.codigo, permissao);
            }

            valueMap = builder;
    }
    
    @Override
    public String toString() {
    	return codigo;
    }

    private Permissao(String codigo) {
            this.codigo = codigo;
    }

    public static Permissao fromCodigo(String codigo) {
            return valueMap.get(codigo);
    }

    public String getCodigo() {
            return codigo;
    }

    public static Map<String, Permissao> getValueMap() {
            return valueMap;
    }
}