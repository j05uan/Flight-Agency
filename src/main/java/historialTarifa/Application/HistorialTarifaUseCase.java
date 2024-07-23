package historialTarifa.Application;

import java.util.List;

import historialTarifa.Domain.entity.HistorialTarifa;
import historialTarifa.Domain.services.HistorialTarifaServices;

public class HistorialTarifaUseCase {

    private final HistorialTarifaServices historialTarifaServices;

    public HistorialTarifaUseCase(HistorialTarifaServices historialTarifaServices) {
        this.historialTarifaServices = historialTarifaServices;
    }

    public void crearHistorialTarifa(HistorialTarifa historialTarifa){
        if( historialTarifa == null){
            throw new IllegalArgumentException("El objeto Historial Tarifa no puede ser nulo.");
        }
        historialTarifaServices.crearHistorialTarifa(historialTarifa);
    }

    public List<HistorialTarifa> obtenerTodosLosHistorialesTarifa(){
        return historialTarifaServices.obtenerTodosLosHistorialesTarifa();
    }

    public HistorialTarifa obtenertHistorialTarifaPorId(Long id){
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID debe ser un número positivo.");
        }
        return historialTarifaServices.obtenertHistorialTarifaPorId(id);
    }

    public void actualizarHistorialTarifa(HistorialTarifa historialTarifa){
        if (historialTarifa == null) {
            throw new IllegalArgumentException("El objeto historialTarifa no puede ser nulo.");
        }
        // Validar que el ID esté presente y sea válido
        if (historialTarifa.getId() == null || historialTarifa.getId() <= 0) {
            throw new IllegalArgumentException("El ID de historialTarifa debe ser un número positivo.");
        }
        historialTarifaServices.actualizarHistorialTarifa(historialTarifa);

    }

    public void eliminarHistorialTarifa(Long id){
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID debe ser un número positivo.");
        }
        historialTarifaServices.eliminarHistorialTarifa(id);
    }
    


}
