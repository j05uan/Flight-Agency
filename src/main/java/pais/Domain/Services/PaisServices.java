package pais.Domain.Services;

import java.util.List;

import pais.Domain.Entity.Pais;

public interface PaisServices {
    void crearPais(Pais pais);
    List<Pais> obtenerTodosLosPaises();
    Pais obtenerPaisPorId(Long id);
    void actualizarPais(Pais pais);
    void eliminarPais(Long id);
}
