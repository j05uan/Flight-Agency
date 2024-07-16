package tipoTarifa.infraestructure.out;

import java.util.ArrayList;
import java.util.List;

import tipoTarifa.Domain.entity.TipoTarifa;
import tipoTarifa.Domain.services.TipoTarifaServices;

public class TipoTarifaRepository implements TipoTarifaServices{
    private final List<TipoTarifa> tiposTarifa = new ArrayList<>();

    @Override
    public void crearTipoTarifa(TipoTarifa tipoTarifa) {
        tiposTarifa.add(tipoTarifa);
    }

    @Override
    public List<TipoTarifa> obtenerTodosLosTiposTarifa() {
        return new ArrayList<>(tiposTarifa);
    }

    @Override
    public TipoTarifa obtenerTipoTarifaPorId(Long id) {
        return tiposTarifa.stream()
                .filter(tipoTarifa -> tipoTarifa.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void actualizarTipoTarifa(TipoTarifa tipoTarifa) {
        TipoTarifa tipoTarifaExistente = obtenerTipoTarifaPorId(tipoTarifa.getId());
        if (tipoTarifaExistente != null) {
            tipoTarifaExistente.setTipo(tipoTarifa.getTipo());
        }
    }

    @Override
    public void eliminarTipoTarifa(Long id) {
        tiposTarifa.removeIf(tipoTarifa -> tipoTarifa.getId().equals(id));
    }

}
