package fabricante.Domain.Services;

import java.util.List;

import fabricante.Domain.Entity.Fabricante;

public interface FabricanteServices {

    void CrearFabricante(Fabricante fabricante);
    List<Fabricante>ObtenerTodosLosFabricantes();
    Fabricante obtenerFabricantePorId(Long id);
    void actualizarFabricante(Fabricante fabricante);
    void eliminarFabricante(Long id);
    
}

