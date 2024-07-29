package salidaAeropuerto.infraestructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aeropuerto.domain.entity.Aeropuerto;
import aeropuerto.infraestructure.out.AeropuertoRepository;
import resource.ConfiguracionBaseDeDatos;
import salidaAeropuerto.Domain.entity.SalidaAeropuerto;
import salidaAeropuerto.Domain.services.SalidaAeropuertoServices;

public class SalidaAeropuertoRepository implements SalidaAeropuertoServices {

    @Override
    public void crearSalidaAeropuerto(SalidaAeropuerto aeropuertoSalida) {
        String sql = "INSERT INTO salidas_aeropuerto (aeropuerto_id, salidaAeropuerto) VALUES (?, ?)";
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setLong(1, aeropuertoSalida.getAeropuerto().getId());  
            statement.setString(2, aeropuertoSalida.getSalidaAeropuerto());  

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        aeropuertoSalida.setId(generatedKeys.getLong(1));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<SalidaAeropuerto> obtenerTodosAeropuertoSalidas() {
        List<SalidaAeropuerto> salidas = new ArrayList<>();
        String sql = "SELECT * FROM salidas_aeropuerto";

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                SalidaAeropuerto salida = mapResultSetToSalidaAeropuerto(resultSet);
                salidas.add(salida);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return salidas;
    }

    @Override
    public SalidaAeropuerto obtenerSalidaAeropuertoPorId(Long id) {
        SalidaAeropuerto salida = null;
        String sql = "SELECT * FROM salidas_aeropuerto WHERE id = ?";

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    salida = mapResultSetToSalidaAeropuerto(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return salida;
    }

    @Override
    public void actualizarSalidaAeropuerto(SalidaAeropuerto salidaAeropuerto) {
        String sql = "UPDATE salidas_aeropuerto SET aeropuerto_id = ?, salidaAeropuerto = ? WHERE id = ?";

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, salidaAeropuerto.getAeropuerto().getId());
            statement.setString(2, salidaAeropuerto.getSalidaAeropuerto());
            statement.setLong(3, salidaAeropuerto.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarSalidaAeropuerto(Long id) {
        String sql = "DELETE FROM salidas_aeropuerto WHERE id = ?";

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método auxiliar para mapear un ResultSet a un objeto SalidaAeropuerto
    private SalidaAeropuerto mapResultSetToSalidaAeropuerto(ResultSet resultSet) throws SQLException {
        SalidaAeropuerto salida = new SalidaAeropuerto();
        salida.setId(resultSet.getLong("id"));
        Aeropuerto aeropuerto = new AeropuertoRepository().obtenerAeropuertoPorId(resultSet.getLong("aeropuerto_id"));
        salida.setAeropuerto(aeropuerto);
        salida.setSalidaAeropuerto(resultSet.getString("salidaAeropuerto")); // Asegúrate de que el nombre del campo sea correcto
        return salida;
    }
}
