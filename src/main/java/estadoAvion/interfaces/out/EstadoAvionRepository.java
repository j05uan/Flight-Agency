package estadoAvion.interfaces.out;

import estadoAvion.Domain.entity.EstadoAvion;
import estadoAvion.Domain.services.EstadoAvionServices;
import resource.ConfiguracionBaseDeDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstadoAvionRepository implements EstadoAvionServices {

    @Override
    public void crearEstadoAvion(EstadoAvion estadoAvion) {
        String sql = "INSERT INTO estados_avion (estado) VALUES (?)";

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, estadoAvion.getEstado());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        estadoAvion.setId(generatedKeys.getLong(1));  // Establecer el ID generado
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<EstadoAvion> obtenerTodosLosEstadosAvion() {
        String sql = "SELECT * FROM estados_avion";
        List<EstadoAvion> estadosAvion = new ArrayList<>();

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                EstadoAvion estadoAvion = new EstadoAvion();
                estadoAvion.setId(resultSet.getLong("id"));
                estadoAvion.setEstado(resultSet.getString("estado"));
                estadosAvion.add(estadoAvion);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return estadosAvion;
    }

    @Override
    public EstadoAvion obtenerEstadoAvionPorId(Long id) {
        String sql = "SELECT * FROM estados_avion WHERE id = ?";
        EstadoAvion estadoAvion = null;

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    estadoAvion = new EstadoAvion();
                    estadoAvion.setId(resultSet.getLong("id"));
                    estadoAvion.setEstado(resultSet.getString("estado"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return estadoAvion;
    }

    @Override
    public void actualizarEstadoAvion(EstadoAvion estadoAvion) {
        String sql = "UPDATE estados_avion SET estado = ? WHERE id = ?";
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, estadoAvion.getEstado());
            statement.setLong(2, estadoAvion.getId());

            int filasActualizadas = statement.executeUpdate();

            if (filasActualizadas == 0) {
                System.out.println("No se encontró ningún estado de avión con el ID proporcionado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarEstadoAvion(Long id) {
        String sql = "DELETE FROM estados_avion WHERE id = ?";
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            int filasEliminadas = statement.executeUpdate();

            if (filasEliminadas == 0) {
                System.out.println("No se encontró ningún estado de avión con el ID proporcionado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
