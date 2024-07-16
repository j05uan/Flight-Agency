package aerolinea.Domain.Services;

import java.util.List;

import aerolinea.Domain.Entity.Aerolinea;

public interface AerolineaServices {

    void crearAerolinea(Aerolinea aerolinea);

    List<Aerolinea> obtenerTodasLasAerolineas();

    Aerolinea obtenerAerolineaPorId(Long id);

    void actualizarAerolinea(Aerolinea aerolinea);

    void eliminarAerolinea(Long id);
}
