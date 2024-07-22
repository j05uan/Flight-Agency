package ruta.Domain.Services;

import java.util.List;

import ruta.Domain.Entity.Ruta;

public interface RutaServices {
    void CrearRuta(Ruta ruta);
    List<Ruta> obtenerTodasLasRutas();
    Ruta obtenerRutaPorId(Long id);
    void actualizarRuta (Ruta ruta);
    void eliminarRuta(Long id);
}
