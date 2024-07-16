package tarifa.Domain.entity;

import java.math.BigDecimal;
import java.util.Set;

import historialTarifa.Domain.entity.HistorialTarifa;
import tipoTarifa.Domain.entity.TipoTarifa;

public class Tarifa {
    private Long id;
    private TipoTarifa tipoTarifa;
    private java.math.BigDecimal valor;
    private Set<HistorialTarifa> historialTarifas;

    public Tarifa() {
    }

    public Tarifa(Long id, TipoTarifa tipoTarifa, BigDecimal valor, Set<HistorialTarifa> historialTarifas) {
        this.id = id;
        this.tipoTarifa = tipoTarifa;
        this.valor = valor;
        this.historialTarifas = historialTarifas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoTarifa getTipoTarifa() {
        return tipoTarifa;
    }

    public void setTipoTarifa(TipoTarifa tipoTarifa) {
        this.tipoTarifa = tipoTarifa;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Set<HistorialTarifa> getHistorialTarifas() {
        return historialTarifas;
    }

    public void setHistorialTarifas(Set<HistorialTarifa> historialTarifas) {
        this.historialTarifas = historialTarifas;
    }

    

}
