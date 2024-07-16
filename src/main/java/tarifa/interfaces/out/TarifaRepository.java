package tarifa.interfaces.out;

import java.util.ArrayList;
import java.util.List;

import tarifa.Domain.entity.Tarifa;
import tarifa.Domain.services.TarifaServices;

public class TarifaRepository implements TarifaServices{
    private final List<Tarifa> tarifas = new ArrayList<>();

    @Override
    public void crearTarifa(Tarifa tarifa) {
        tarifas.add(tarifa);
    }

    @Override
    public List<Tarifa> obtenerTodasLasTarifas() {
        return new ArrayList<>(tarifas);
    }

    @Override
    public Tarifa obtenerTarifaPorId(Long id) {
        return tarifas.stream()
                .filter(tarifa -> tarifa.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void actualizarTarifa(Tarifa tarifa) {
        Tarifa tarifaExistente = obtenerTarifaPorId(tarifa.getId());
        if (tarifaExistente != null) {
            tarifaExistente.setTipoTarifa(tarifa.getTipoTarifa());
            tarifaExistente.setValor(tarifa.getValor());
            tarifaExistente.setHistorialTarifas(tarifa.getHistorialTarifas());
        }
    }

    @Override
    public void eliminarTarifa(Long id) {
        tarifas.removeIf(tarifa -> tarifa.getId().equals(id));
    }
}
