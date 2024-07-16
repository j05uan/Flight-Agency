package tipoDocumento.Applicacion;

import java.util.List;

import tipoDocumento.Domain.entity.TipoDocumento;
import tipoDocumento.Domain.services.TipoDocumentoServices;

public class TipoDocumentoUseCase {
       private final TipoDocumentoServices tipoDocumentoServices;

    public TipoDocumentoUseCase(TipoDocumentoServices tipoDocumentoServices) {
        this.tipoDocumentoServices = tipoDocumentoServices;
    }

    public void crearTipoDocumento(TipoDocumento tipoDocumento) {
        tipoDocumentoServices.crearTipoDocumento(tipoDocumento);
    }

    public List<TipoDocumento> obtenerTodosLosTiposDocumento() {
        return tipoDocumentoServices.obtenerTodosLosTiposDocumento();
    }

    public TipoDocumento obtenerTipoDocumentoPorId(Long id) {
        return tipoDocumentoServices.obtenerTipoDocumentoPorId(id);
    }

    public void actualizarTipoDocumento(TipoDocumento tipoDocumento) {
        tipoDocumentoServices.actualizarTipoDocumento(tipoDocumento);
    }

    public void eliminarTipoDocumento(Long id) {
        tipoDocumentoServices.eliminarTipoDocumento(id);
    }
}
