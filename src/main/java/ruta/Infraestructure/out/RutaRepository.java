package ruta.Infraestructure.out;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aeropuerto.domain.entity.Aeropuerto;
import aeropuerto.infraestructure.out.AeropuertoRepository;
import resource.ConfiguracionBaseDeDatos;
import ruta.Domain.Entity.Ruta;
import ruta.Domain.Services.RutaServices;
import salidaAeropuerto.Domain.entity.SalidaAeropuerto;
import salidaAeropuerto.infraestructure.out.SalidaAeropuertoRepository;

public class RutaRepository implements RutaServices {

    @Override
    public void CrearRuta(Ruta ruta) {
        String sql = "INSERT INTO rutas (fecha, aeropuerto_origen_id, aeropuerto_destino_id, idSaidaAeropuerto) VALUES (?, ?, ?, ?)";

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setDate(1, new Date(ruta.getFecha().getTime()));
            statement.setLong(2, ruta.getAeropuertoOrigen().getId());
            statement.setLong(3, ruta.getAeropuertoDestino().getId());
            statement.setLong(4, ruta.getSalidaAeropuerto().getId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        ruta.setId(generatedKeys.getLong(1));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Ruta> obtenerTodasLasRutas() {
        List<Ruta> rutas = new ArrayList<>();
        String sql = "SELECT * FROM rutas";

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Ruta ruta = mapResultSetToRuta(resultSet);
                rutas.add(ruta);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rutas;
    }

    @Override
    public Ruta obtenerRutaPorId(Long id) {
        Ruta ruta = null;
        String sql = "SELECT * FROM rutas WHERE id = ?";

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    ruta = mapResultSetToRuta(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ruta;
    }

    @Override
    public void actualizarRuta(Ruta ruta) {
        String sql = "UPDATE rutas SET fecha = ?, aeropuerto_origen_id = ?, aeropuerto_destino_id = ?, idSaidaAeropuerto = ? WHERE id = ?";

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setDate(1, new Date(ruta.getFecha().getTime()));
            statement.setLong(2, ruta.getAeropuertoOrigen().getId());
            statement.setLong(3, ruta.getAeropuertoDestino().getId());
            statement.setLong(4, ruta.getSalidaAeropuerto().getId());
            statement.setLong(5, ruta.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarRuta(Long id) {
        String sql = "DELETE FROM rutas WHERE id = ?";

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para mapear el ResultSet a un objeto Ruta
    private Ruta mapResultSetToRuta(ResultSet resultSet) throws SQLException {
        Ruta ruta = new Ruta();
        ruta.setId(resultSet.getLong("id"));
        ruta.setFecha(resultSet.getDate("fecha"));
        Aeropuerto aeropuertoOrigen = new AeropuertoRepository().obtenerAeropuertoPorId(resultSet.getLong("Aeropuerto Origen:"));
        Aeropuerto aeropuertoDestino = new AeropuertoRepository().obtenerAeropuertoPorId(resultSet.getLong("Aeropuerto Destino"));
        SalidaAeropuerto salidaAeropuerto = new SalidaAeropuertoRepository().obtenerSalidaAeropuertoPorId(resultSet.getLong("Salida Aeropuerto"));
        return ruta;
    }

}
