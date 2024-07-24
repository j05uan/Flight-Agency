package reserva.infraestructure.out;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import reserva.Domain.entity.Reserva;
import reserva.Domain.services.ReservaServices;
import resource.ConfiguracionBaseDeDatos;

public class ReservaRepository implements ReservaServices{

    @Override
    public void crearReserva(Reserva reserva) {
        String sql =  "INSERT INTO reservas (cliente_id,ruta_id,fechaReservacion,tarifasRutas_id) ";
        
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS))
        {
            statement.setLong(1, reserva.getCliente().getId());
            statement.setLong(2, reserva.getRuta().getId());
            statement.setDate(3, new Date(reserva.getFecha().getTime()));
            statement.setLong(4, reserva.getRuta().getId());

            int affectedRows = statement.executeUpdate();

            if(affectedRows > 0){
                try(ResultSet generatedKeys = statement.getGeneratedKeys()){
                    if(generatedKeys.next()){
                        reserva.setId(generatedKeys.getLong(1));
                    }
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();

        }

        
    }

    @Override
    public List<Reserva> obtenerTodasReserva() {
        
    }

    @Override
    public Reserva obtenerReservaPorId(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerReservaPorId'");
    }

    @Override
    public void actualizarReserva(Reserva reserva) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizarReserva'");
    }

    @Override
    public void eliminarReserva(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarReserva'");
    }

    

}
