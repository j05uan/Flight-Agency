package rutaEscala.Domain.services;

import java.util.List;

import rutaEscala.Domain.entity.RutaEscala;

public interface RutaEscalaServices {

    void crearRutaEscala (RutaEscala rutaEscala);
    List<RutaEscala> obtenerTodasRutaEscalas();
    RutaEscala obtenerEscalaPorId(Long id);
    void actualizarEscala(RutaEscala rutaEscala);
    void eliminarEscala(Long id);
}
