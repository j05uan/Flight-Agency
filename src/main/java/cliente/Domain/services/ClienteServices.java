package cliente.Domain.services;

import java.util.List;

import cliente.Domain.entity.Cliente;

public interface ClienteServices {
    void CrearCliente(Cliente cliente);
    List<Cliente> obtenerTodosLosClientes();
    Cliente obtenerClientePorId(Long id);
    void actualizarCliente(Cliente cliente);
    void eliminarCliente(Long id);


}
