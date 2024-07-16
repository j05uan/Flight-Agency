package tipoTarifa.Domain.entity;

import java.util.Set;

import tarifa.Domain.entity.Tarifa;

public class TipoTarifa {
    private Long id;
    private String tipo;
    private Set<Tarifa> tarifas;

    public TipoTarifa() {
    }

    public TipoTarifa(Long id, Set<Tarifa> tarifas, String tipo) {
        this.id = id;
        this.tarifas = tarifas;
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

    public Set<Tarifa> getTarifas() {
        return tarifas;
    }

    public void setTarifas(Set<Tarifa> tarifas) {
        this.tarifas = tarifas;
    }


}
