package tipoDocumento.infraestructure.out;

import java.util.ArrayList;
import java.util.List;

import tipoDocumento.Domain.entity.TipoDocumento;
import tipoDocumento.Domain.services.TipoDocumentoServices;

public class TipoDocumentoRepository implements TipoDocumentoServices{

private final List<TipoDocumento> tiposDocumento = new ArrayList<>();

@Override
public void crearTipoDocumento(TipoDocumento tipoDocumento) {
    tiposDocumento.add(tipoDocumento);
}

@Override
public List<TipoDocumento> obtenerTodosLosTiposDocumento() {
    return new ArrayList<>(tiposDocumento);
}

@Override
public TipoDocumento obtenerTipoDocumentoPorId(Long id) {
    return tiposDocumento.stream()
            .filter(tipoDocumento -> tipoDocumento.getId().equals(id))
            .findFirst()
            .orElse(null);
}

@Override
public void actualizarTipoDocumento(TipoDocumento tipoDocumento) {
    TipoDocumento tipoDocumentoExistente = obtenerTipoDocumentoPorId(tipoDocumento.getId());
    if (tipoDocumentoExistente != null) {
        tipoDocumentoExistente.setTipo(tipoDocumento.getTipo());
    }
}

@Override
public void eliminarTipoDocumento(Long id) {
    tiposDocumento.removeIf(tipoDocumento -> tipoDocumento.getId().equals(id));
}
}
