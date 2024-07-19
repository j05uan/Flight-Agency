package historialEstado.interfaces.out;

import historialEstado.Domain.entity.HistorialEstado;
import historialEstado.Domain.services.HistorialEstadoServices;
import resource.ConfiguracionBaseDeDatos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import avion.Infraestructure.out.AvionRepository;
import estadoAvion.Domain.entity.EstadoAvion;
import estadoAvion.interfaces.out.EstadoAvionRepository;

public class HistorialEstadoRepository implements HistorialEstadoServices {
    
    @Override
    public void crearHistorialEstado(HistorialEstado historialEstado) {
        String sql = "INSERT INTO historial_estado (avion_id, estado_avion_id, fecha_inicio) VALUES (?,?,?)";

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setLong(1, historialEstado.getAvion().getId());
            statement.setLong(2, historialEstado.getEstadoAvion().getId());
            statement.setDate(3, new Date(historialEstado.getFechaInicio().getTime()));

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        historialEstado.setId(generatedKeys.getLong(1));  // Establecer el ID generado
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<HistorialEstado> obtenerTodosLosHistorialEstados() {
        String sql = "SELECT * FROM historial_estado";
        List<HistorialEstado> historialesEstado = new ArrayList<>();
        AvionRepository avionRepository = new AvionRepository();
        EstadoAvionRepository estadoAvionRepository = new EstadoAvionRepository();

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                HistorialEstado historialEstado = new HistorialEstado();
                historialEstado.setId(resultSet.getLong("id"));
                historialEstado.setFechaInicio(resultSet.getDate("fecha_inicio"));
                historialEstado.setAvion(avionRepository.obtenerAvionPorId(resultSet.getLong("avion_id")));
                historialEstado.setEstadoAvion(estadoAvionRepository.obtenerEstadoAvionPorId(resultSet.getLong("estado_avion_id")));
                historialesEstado.add(historialEstado);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return historialesEstado;
    }

    @Override
    public HistorialEstado obtenerHistorialEstadoPorId(Long id) {
        String sql = "SELECT * FROM historial_estado WHERE id = ?";
        HistorialEstado historialEstado = null;
        AvionRepository avionRepository = new AvionRepository();
        EstadoAvionRepository estadoAvionRepository = new EstadoAvionRepository();

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    historialEstado = new HistorialEstado();
                    historialEstado.setId(resultSet.getLong("id"));
                    historialEstado.setFechaInicio(resultSet.getDate("fecha_inicio"));
                    historialEstado.setAvion(avionRepository.obtenerAvionPorId(resultSet.getLong("avion_id")));
                    historialEstado.setEstadoAvion(estadoAvionRepository.obtenerEstadoAvionPorId(resultSet.getLong("estado_avion_id")));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return historialEstado;
    }

    @Override
    public void actualizarHistorialEstado(HistorialEstado historialEstado) {
        String sql = "UPDATE historial_estado SET avion_id = ?, estado_avion_id = ?, fecha_inicio = ? WHERE id = ?";
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, historialEstado.getAvion().getId());
            statement.setLong(2, historialEstado.getEstadoAvion().getId());
            statement.setDate(3, new Date(historialEstado.getFechaInicio().getTime()));
            statement.setLong(4, historialEstado.getId());

            int filasActualizadas = statement.executeUpdate();

            if (filasActualizadas == 0) {
                System.out.println("No se encontró ningún historial de estado con el ID proporcionado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarHistorialEstado(Long id) {
        String sql = "DELETE FROM historial_estado WHERE id = ?";
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            int filasEliminadas = statement.executeUpdate();

            if (filasEliminadas == 0) {
                System.out.println("No se encontró ningún historial de estado con el ID proporcionado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
