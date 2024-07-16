package pais.infraesfructure.out;

import java.util.ArrayList;
import java.util.List;

import pais.Domain.Entity.Pais;
import pais.Domain.Services.PaisServices;

public class PaisRepository implements PaisServices{

     private final List<Pais> paises = new ArrayList<>();

    @Override
    public void crearPais(Pais pais) {
        paises.add(pais);
    }

    @Override
    public List<Pais> obtenerTodosLosPaises() {
        return new ArrayList<>(paises);
    }

    @Override
    public Pais obtenerPaisPorId(Long id) {
        return paises.stream()
                .filter(pais -> pais.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void actualizarPais(Pais pais) {
        Pais paisExistente = obtenerPaisPorId(pais.getId());
        if (paisExistente != null) {
            paisExistente.setNombre(pais.getNombre());
        }
    }

    @Override
    public void eliminarPais(Long id) {
        paises.removeIf(pais -> pais.getId().equals(id));
    }
    
}
