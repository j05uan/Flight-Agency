package tripulacionDeVuelo.infraestructure.out;

import java.util.List;

import tripulacionDeVuelo.Domain.entity.TripulacionDeVuelo;
import tripulacionDeVuelo.Domain.services.TripulacionDeVueloServices;

public class TripulacionDeVueloRepository implements TripulacionDeVueloServices{

    @Override
    public void crearTripulacionDeVuelo(TripulacionDeVuelo tripulacionDeVuelo) {
        String sql ="INSERT INTO (vuelo_id,vuelo_id,vuelo_id) vALUES (?, ?, ?)";
        

    }

    @Override
    public List<TripulacionDeVuelo> obtenerTodosAeropuertoSalidas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerTodosAeropuertoSalidas'");
    }

    @Override
    public TripulacionDeVuelo obtenerTripulacionDeVueloPorId(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerTripulacionDeVueloPorId'");
    }

    @Override
    public void actualizarTripulacionDeVuelo(TripulacionDeVuelo tripulacionDeVuelo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizarTripulacionDeVuelo'");
    }

    @Override
    public void eliminarTripulacionDeVuelo(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarTripulacionDeVuelo'");
    }

    
}
