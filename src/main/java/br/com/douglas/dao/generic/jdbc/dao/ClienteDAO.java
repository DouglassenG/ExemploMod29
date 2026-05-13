/**
 * 
 */
package br.com.douglas.dao.generic.jdbc.dao;

import java.util.List;

import br.com.douglas.domain.Cliente;

/**
 * @author rodrigo.pires
 *
 */
public interface ClienteDAO {

	public Integer cadastrar(Cliente cliente) throws Exception;
	
	public Integer atualizar(Cliente cliente) throws Exception;
	
	public Cliente buscar(String codigo) throws Exception;
	
	public List<Cliente> buscarTodos() throws Exception;
	
	public Integer excluir(Cliente cliente) throws Exception;
}