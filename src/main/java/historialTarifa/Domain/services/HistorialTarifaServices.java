package historialTarifa.Domain.services;

import java.util.List;

import historialTarifa.Domain.entity.HistorialTarifa;

public interface HistorialTarifaServices {
    void crearHistorialTarifa (HistorialTarifa historialTarifa);
    List<HistorialTarifa> obtenerTodosLosHistorialesTarifa();
    HistorialTarifa obtenertHistorialTarifaPorId(Long id);
    void actualizarHistorialTarifa(HistorialTarifa historialTarifa);
    void eliminarHistorialTarifa(Long id);


}
