package tipoDocumento.Domain.services;

import java.util.List;

import tipoDocumento.Domain.entity.TipoDocumento;

public interface TipoDocumentoServices {
    void crearTipoDocumento(TipoDocumento tipoDocumento);
    List<TipoDocumento> obtenerTodosLosTiposDocumento();
    TipoDocumento obtenerTipoDocumentoPorId(Long id);
    void actualizarTipoDocumento(TipoDocumento tipoDocumento);
    void eliminarTipoDocumento(Long id);
}
