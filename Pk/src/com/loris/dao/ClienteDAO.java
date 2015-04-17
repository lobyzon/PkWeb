package com.loris.dao;

import java.util.List;

import com.loris.domain.Cliente;

public interface ClienteDAO {
	public Cliente getCliente(Integer id);
	public List<Cliente> getClientes();
	public List<Cliente> getClienteRecordsPage(Integer pageNumber, Integer recordSize);
	public void saveUpdateCliente(Cliente cliente);
	public void deleteCliente(Cliente cliente);
}
