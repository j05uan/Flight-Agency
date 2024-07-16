package tipoDocumento.Domain.entity;

import java.util.Set;

import cliente.Domain.entity.Cliente;

public class TipoDocumento {
    private Long id;
    private String tipo;
    private Set<Cliente> clientes;

    public TipoDocumento() {
    }

    public TipoDocumento(Set<Cliente> clientes, Long id, String tipo) {
        this.clientes = clientes;
        this.id = id;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Set<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(Set<Cliente> clientes) {
        this.clientes = clientes;
    }



}
