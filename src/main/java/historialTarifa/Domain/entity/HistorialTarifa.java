package historialTarifa.Domain.entity;

import java.math.BigDecimal;
import java.util.Date;

import tarifa.Domain.entity.Tarifa;

public class HistorialTarifa {
    private Long id;
    private Tarifa tarifa;
    private java.util.Date fechaInicio;
    private java.math.BigDecimal valor;

    public HistorialTarifa() {
    }

    public HistorialTarifa(Date fechaInicio, Long id, Tarifa tarifa, BigDecimal valor) {
        this.fechaInicio = fechaInicio;
        this.id = id;
        this.tarifa = tarifa;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public void setTarifa(Tarifa tarifa) {
        this.tarifa = tarifa;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }



}
