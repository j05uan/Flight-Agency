package tipoTarifa.Application;

import java.util.List;

import tipoTarifa.Domain.entity.TipoTarifa;
import tipoTarifa.Domain.services.TipoTarifaServices;

public class TipoTarifaUseCase {
     private final TipoTarifaServices tipoTarifaServices;

    public TipoTarifaUseCase(TipoTarifaServices tipoTarifaServices) {
        this.tipoTarifaServices = tipoTarifaServices;
    }

    public void crearTipoTarifa(TipoTarifa tipoTarifa) {
        tipoTarifaServices.crearTipoTarifa(tipoTarifa);
    }

    public List<TipoTarifa> obtenerTodosLosTiposTarifa() {
        return tipoTarifaServices.obtenerTodosLosTiposTarifa();
    }

    public TipoTarifa obtenerTipoTarifaPorId(Long id) {
        return tipoTarifaServices.obtenerTipoTarifaPorId(id);
    }

    public void actualizarTipoTarifa(TipoTarifa tipoTarifa) {
        tipoTarifaServices.actualizarTipoTarifa(tipoTarifa);
    }

    public void eliminarTipoTarifa(Long id) {
        tipoTarifaServices.eliminarTipoTarifa(id);
    }

}
