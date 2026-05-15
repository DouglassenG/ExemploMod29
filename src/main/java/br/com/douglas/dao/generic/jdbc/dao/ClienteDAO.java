package br.com.douglas.dao.generic.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.douglas.dao.generic.jdbc.ConnectionFactory;
import br.com.douglas.domain.Cliente;

public class ClienteDAO implements IClienteDAO {

    private final Connection connection;

    public ClienteDAO() throws Exception {
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public Integer cadastrar(Cliente cliente) throws Exception {
        String sql = "INSERT INTO TB_CLIENTE_2 (ID, CODIGO, NOME) VALUES (NEXTVAL('SQ_CLIENTE_2'), ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, cliente.getCodigo());
        stmt.setString(2, cliente.getNome());
        int resultado = stmt.executeUpdate();
        stmt.close();
        return resultado;
    }

    @Override
    public Integer atualizar(Cliente cliente) throws Exception {
        String sql = "UPDATE TB_CLIENTE_2 SET CODIGO = ?, NOME = ? WHERE ID = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, cliente.getCodigo());
        stmt.setString(2, cliente.getNome());
        stmt.setLong(3, cliente.getId());
        int resultado = stmt.executeUpdate();
        stmt.close();
        return resultado;
    }

    @Override
    public Cliente buscar(String codigo) throws Exception {
        String sql = "SELECT ID, CODIGO, NOME FROM TB_CLIENTE_2 WHERE CODIGO = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, codigo);
        ResultSet rs = stmt.executeQuery();
        Cliente cliente = null;
        if (rs.next()) {
            cliente = new Cliente();
            cliente.setId(rs.getLong("ID"));
            cliente.setCodigo(rs.getString("CODIGO"));
            cliente.setNome(rs.getString("NOME"));
        }
        rs.close();
        stmt.close();
        return cliente;
    }

    @Override
    public List<Cliente> buscarTodos() throws Exception {
        String sql = "SELECT ID, CODIGO, NOME FROM TB_CLIENTE_2";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<Cliente> clientes = new ArrayList<>();
        while (rs.next()) {
            Cliente cliente = new Cliente();
            cliente.setId(rs.getLong("ID"));
            cliente.setCodigo(rs.getString("CODIGO"));
            cliente.setNome(rs.getString("NOME"));
            clientes.add(cliente);
        }
        rs.close();
        stmt.close();
        return clientes;
    }

    @Override
    public Integer excluir(Cliente cliente) throws Exception {
        String sql = "DELETE FROM TB_CLIENTE_2 WHERE ID = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, cliente.getId());
        int resultado = stmt.executeUpdate();
        stmt.close();
        return resultado;
    }

    public void closeConnection() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
