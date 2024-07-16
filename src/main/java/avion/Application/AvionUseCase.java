package avion.Application;

import java.util.List;

import avion.Domain.Entity.Avion;
import avion.Domain.Services.AvionServices;

public class AvionUseCase {
    private final AvionServices avionServices;

    public AvionUseCase(AvionServices avionServices) {
        this.avionServices = avionServices;
    }

    
    public void crearAvion(Avion avion){
        avionServices.CrearAvion(avion);
    }

    public List<Avion> obtenerTodosLosAviones() {
        return avionServices.obtenerTodosLosAviones();
    }

    public Avion obtenerAvionPorMatricula(String matricula) {
        return avionServices.obtenerAvionesPorMatricula(matricula);
    }

    public Avion obtenerAvionPorId(Long id) {
        return avionServices.obtenerAvionPorId(id);
    }
}
