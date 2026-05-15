import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import br.com.douglas.dao.generic.jdbc.dao.ClienteDAO;
import br.com.douglas.dao.generic.jdbc.dao.IClienteDAO;
import br.com.douglas.domain.Cliente;

public class ClienteTest {

    @Test
    public void cadastrarTest() throws Exception {
        IClienteDAO dao = new ClienteDAO();

        Cliente cliente = new Cliente();
        cliente.setCodigo("10");
        cliente.setNome("Douglas");

        // Cadastra e valida que 1 linha foi inserida
        Integer resultado = dao.cadastrar(cliente);
        assertTrue(resultado == 1);

        // Busca no banco e valida que os dados batem
        Cliente clienteBD = dao.buscar("10");
        assertNotNull(clienteBD);
        assertEquals("10", clienteBD.getCodigo());
        assertEquals("Douglas", clienteBD.getNome());

        // Limpa o registro criado
        dao.excluir(clienteBD);
    }

    @Test
    public void buscarTest() throws Exception {
        IClienteDAO dao = new ClienteDAO();

        Cliente cliente = new Cliente();
        cliente.setCodigo("20");
        cliente.setNome("Maria");
        dao.cadastrar(cliente);

        // Busca pelo codigo e valida
        Cliente clienteBD = dao.buscar("20");
        assertNotNull(clienteBD);
        assertNotNull(clienteBD.getId());
        assertEquals("20", clienteBD.getCodigo());
        assertEquals("Maria", clienteBD.getNome());

        // Busca codigo inexistente e valida que retorna null
        Cliente inexistente = dao.buscar("9999");
        assertNull(inexistente);

        // Limpa
        dao.excluir(clienteBD);
    }

    @Test
    public void atualizarTest() throws Exception {
        IClienteDAO dao = new ClienteDAO();

        Cliente cliente = new Cliente();
        cliente.setCodigo("30");
        cliente.setNome("Pedro");
        dao.cadastrar(cliente);

        // Busca, altera e atualiza
        Cliente clienteBD = dao.buscar("30");
        assertNotNull(clienteBD);

        clienteBD.setCodigo("31");
        clienteBD.setNome("Pedro Atualizado");
        Integer resultado = dao.atualizar(clienteBD);
        assertTrue(resultado == 1);

        // Codigo antigo nao existe mais
        Cliente antigo = dao.buscar("30");
        assertNull(antigo);

        // Codigo novo existe com dados corretos
        Cliente atualizado = dao.buscar("31");
        assertNotNull(atualizado);
        assertEquals(clienteBD.getId(), atualizado.getId());
        assertEquals("31", atualizado.getCodigo());
        assertEquals("Pedro Atualizado", atualizado.getNome());

        // Limpa
        dao.excluir(atualizado);
    }

    @Test
    public void excluirTest() throws Exception {
        IClienteDAO dao = new ClienteDAO();

        Cliente cliente = new Cliente();
        cliente.setCodigo("40");
        cliente.setNome("Ana");
        dao.cadastrar(cliente);

        Cliente clienteBD = dao.buscar("40");
        assertNotNull(clienteBD);

        // Exclui e valida que 1 linha foi removida
        Integer resultado = dao.excluir(clienteBD);
        assertTrue(resultado == 1);

        // Confirma que nao existe mais no banco
        Cliente excluido = dao.buscar("40");
        assertNull(excluido);
    }

    @Test
    public void buscarTodosTest() throws Exception {
        IClienteDAO dao = new ClienteDAO();

        // Cadastra 2 clientes
        Cliente cliente1 = new Cliente();
        cliente1.setCodigo("50");
        cliente1.setNome("Carlos");
        dao.cadastrar(cliente1);

        Cliente cliente2 = new Cliente();
        cliente2.setCodigo("60");
        cliente2.setNome("Julia");
        dao.cadastrar(cliente2);

        // Busca todos e valida que tem pelo menos 2
        List<Cliente> lista = dao.buscarTodos();
        assertNotNull(lista);
        assertTrue(lista.size() >= 2);

        // Limpa todos os registros criados
        for (Cliente cli : lista) {
            dao.excluir(cli);
        }

        // Valida que a tabela ficou vazia
        List<Cliente> listaVazia = dao.buscarTodos();
        assertEquals(0, listaVazia.size());
    }
}