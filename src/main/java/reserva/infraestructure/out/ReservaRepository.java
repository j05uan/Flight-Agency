package reserva.infraestructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cliente.Domain.entity.Cliente;
import cliente.infraestructure.out.ClienteRepository;
import reserva.Domain.entity.Reserva;
import reserva.Domain.services.ReservaServices;
import resource.ConfiguracionBaseDeDatos;
import ruta.Domain.Entity.Ruta;
import ruta.Infraestructure.out.RutaRepository;
import tarifa.Domain.entity.Tarifa;
import tarifa.interfaces.out.TarifaRepository;

public class ReservaRepository implements ReservaServices {

    @Override
    public void crearReserva(Reserva reserva) {
        String sql = "INSERT INTO reservas (cliente_id, ruta_id, fechaReservacion, tarifasRutas_id) "
                   + "VALUES (?, ?, ?, ?)";
        
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            statement.setLong(1, reserva.getCliente().getId());
            statement.setLong(2, reserva.getRuta().getId());
            statement.setDate(3, new java.sql.Date(reserva.getFecha().getTime()));
            statement.setLong(4, reserva.getTarifa().getId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
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
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM reservas";
        
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                Reserva reserva = mapResultSetToReserva(resultSet);
                reservas.add(reserva);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return reservas;
    }

    @Override
    public Reserva obtenerReservaPorId(Long id) {
        String sql = "SELECT * FROM reservas WHERE id = ?";
        Reserva reserva = null;

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    reserva = mapResultSetToReserva(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reserva;
    }

    @Override
    public void actualizarReserva(Reserva reserva) {
        String sql = "UPDATE reservas SET cliente_id = ?, ruta_id = ?, fechaReservacion = ?, tarifasRutas_id = ? WHERE id = ?";
        
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, reserva.getCliente().getId());
            statement.setLong(2, reserva.getRuta().getId());
            statement.setDate(3, new java.sql.Date(reserva.getFecha().getTime()));
            statement.setLong(4, reserva.getTarifa().getId());
            statement.setLong(5, reserva.getId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
            System.out.println("Actualizacion Exitosa");            
        }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarReserva(Long id) {
        String sql = "DELETE FROM reservas WHERE id = ?";
        
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Reserva mapResultSetToReserva(ResultSet resultSet) throws SQLException {
        Reserva reserva = new Reserva();
        reserva.setId(resultSet.getLong("id"));

        Cliente cliente = new ClienteRepository().obtenerClientePorId(resultSet.getLong("cliente_id"));
        reserva.setCliente(cliente);

        Ruta ruta = new RutaRepository().obtenerRutaPorId(resultSet.getLong("ruta_id"));
        reserva.setRuta(ruta);

        reserva.setFecha(resultSet.getDate("fechaReservacion"));

        Tarifa tarifa = new TarifaRepository().obtenerTarifaPorId(resultSet.getLong("tarifasRutas_id"));
        reserva.setTarifa(tarifa);

        return reserva;
    }
}
