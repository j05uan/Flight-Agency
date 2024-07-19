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

    public Fabricante obtenerFabricantePorId (Long id){
        return fabricanteServices.obtenerFabricantePorId(id);
    }

    public void actualizarFabricante (Fabricante fabricante){
        fabricanteServices.actualizarFabricante(fabricante);
    } 

    public void eliminarFabricante (Long id){
        fabricanteServices.eliminarFabricante(id);
    }

}
