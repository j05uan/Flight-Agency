package fabricante.Domain.Services;

import java.util.List;

import fabricante.Domain.Entity.Fabricante;

public interface FabricanteServices {

    void CrearFabricante(Fabricante fabricante);
    List<Fabricante>ObtenerTodosLosFabricantes();
}

