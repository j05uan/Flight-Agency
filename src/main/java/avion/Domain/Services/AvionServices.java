package avion.Domain.Services;

import java.util.List;

import avion.Domain.Entity.Avion;

public interface AvionServices {
    void CrearAvion(Avion avion);
    List<Avion> obtenerTodosLosAviones();
    Avion obtenerAvionesPorMatricula(String matricula);
    Avion obtenerAvionPorId(Long id);
    void actualizarAvion(Avion avion);
    void eliminarAvion(Long id);
    

}
