package tarifa.Application;

import java.util.List;

import tarifa.Domain.entity.Tarifa;
import tarifa.Domain.services.TarifaServices;

public class TarifaUseCase {
    private final TarifaServices tarifaServices;

    public TarifaUseCase(TarifaServices tarifaServices) {
        this.tarifaServices = tarifaServices;
    }

    public void crearTarifa(Tarifa tarifa) {
        tarifaServices.crearTarifa(tarifa);
    }

    public List<Tarifa> obtenerTodasLasTarifas() {
        return tarifaServices.obtenerTodasLasTarifas();
    }

    public Tarifa obtenerTarifaPorId(Long id) {
        return tarifaServices.obtenerTarifaPorId(id);
    }

    public void actualizarTarifa(Tarifa tarifa) {
        tarifaServices.actualizarTarifa(tarifa);
    }

    public void eliminarTarifa(Long id) {
        tarifaServices.eliminarTarifa(id);
    }
}
