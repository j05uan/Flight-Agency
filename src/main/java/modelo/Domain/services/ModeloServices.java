package modelo.Domain.services;

import java.util.List;

import modelo.Domain.entity.Modelo;

public interface ModeloServices {
    void CrearModelo(Modelo modelo);
    List<Modelo> obtenerTodosLosModelos();
    Modelo obtenerModeloPorId(Long id);
    void actualizarModelo(Modelo modelo);
    void eliminarModelo(Long id);

}
