package rolTripulante.Domain.services;

import java.util.List;

import rolTripulante.Domain.entity.RolTripulante;

public interface RolTripulanteServices {
    void crearRolTripulante(RolTripulante rolTripulante);
    List<RolTripulante> obtenerTodosLosRolTripulantes();
    RolTripulante obtenerRolTripulantePorId(Long id);
    void actualizarRolTripulante(RolTripulante rolTripulante);
    void eliminaRolTripulante(Long id);
}
