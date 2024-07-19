package rolTripulante.Application;

import java.util.List;

import rolTripulante.Domain.entity.RolTripulante;
import rolTripulante.Domain.services.RolTripulanteServices;

public class RolTripulnteUseCase {

    private final RolTripulanteServices rolTripulanteServices;

    public RolTripulnteUseCase(RolTripulanteServices rolTripulanteServices) {
        this.rolTripulanteServices = rolTripulanteServices;
    }

    public void crearRolTripulante(RolTripulante rolTripulante){
        rolTripulanteServices.crearRolTripulante(rolTripulante);
    }

    public List<RolTripulante> obtenerTodosLosRolTripulantes(){
        return rolTripulanteServices.obtenerTodosLosRolTripulantes();
    }
    public RolTripulante obtenerRolTripulantePorId(Long id){
        return rolTripulanteServices.obtenerRolTripulantePorId(id);
    }
    public void actualizarRolTripulante(RolTripulante rolTripulante){
        rolTripulanteServices.actualizarRolTripulante(rolTripulante);
    }

    public void eliminaRolTripulante(Long id){
        rolTripulanteServices.eliminaRolTripulante(id);
    }

}
