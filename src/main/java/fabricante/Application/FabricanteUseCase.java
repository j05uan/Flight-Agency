package fabricante.Application;

import java.util.List;

import fabricante.Domain.Entity.Fabricante;
import fabricante.Domain.Services.FabricanteServices;

public class FabricanteUseCase {
    
    private final FabricanteServices fabricanteServices;

    public FabricanteUseCase(FabricanteServices fabricanteServices) {
        this.fabricanteServices = fabricanteServices;
    }

    public void crearFabricante (Fabricante fabricante){
        fabricanteServices.CrearFabricante(fabricante);
    }
    public List<Fabricante> ObtenerTodosLosFabricantes(){
    return fabricanteServices.ObtenerTodosLosFabricantes();
}

}
