package cliente.Application;

import java.util.List;

import cliente.Domain.entity.Cliente;
import cliente.Domain.services.ClienteServices;

public class ClienteUseCase {

    private final ClienteServices clienteServices;

    public ClienteUseCase(ClienteServices clienteServices) {
        this.clienteServices = clienteServices;
    }

    
    public void CrearCliente(Cliente cliente){
        clienteServices.CrearCliente(cliente);

    }

    public List<Cliente> obtenerTodosLosClientes(){
        return clienteServices.obtenerTodosLosClientes();
    }

    public Cliente obtenerClientePorId(Long id){
        return clienteServices.obtenerClientePorId(id);
    }

    public void actualizarCliente(Cliente cliente){
        clienteServices.actualizarCliente(cliente);
    }

    public void eliminarCliente(Long id){
        clienteServices.eliminarCliente(id);
    }
    

}
