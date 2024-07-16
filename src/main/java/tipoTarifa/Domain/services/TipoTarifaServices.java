package tipoTarifa.Domain.services;

import java.util.List;

import tipoTarifa.Domain.entity.TipoTarifa;

public interface TipoTarifaServices {
    void crearTipoTarifa(TipoTarifa tipoTarifa);
    List<TipoTarifa> obtenerTodosLosTiposTarifa();
    TipoTarifa obtenerTipoTarifaPorId(Long id);
    void actualizarTipoTarifa(TipoTarifa tipoTarifa);
    void eliminarTipoTarifa(Long id);
    
}
