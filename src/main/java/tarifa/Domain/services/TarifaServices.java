package tarifa.Domain.services;

import java.util.List;

import tarifa.Domain.entity.Tarifa;

public interface TarifaServices {
    void crearTarifa(Tarifa tarifa);
    List<Tarifa> obtenerTodasLasTarifas();
    Tarifa obtenerTarifaPorId(Long id);
    void actualizarTarifa(Tarifa tarifa);
    void eliminarTarifa(Long id);
}
