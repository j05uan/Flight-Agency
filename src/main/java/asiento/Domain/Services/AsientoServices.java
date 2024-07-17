package asiento.Domain.Services;

import java.util.List;

import asiento.Domain.Entity.Asiento;

public interface AsientoServices {



    void crearAsiento(Asiento asiento);

    List<Asiento> obtenerTodasLasAsientos();

    Asiento obtenerAsientoPorId(Long id);

    void actualizarAsiento(Asiento asiento);

    void eliminarAsiento(Long id);
    
    

}
