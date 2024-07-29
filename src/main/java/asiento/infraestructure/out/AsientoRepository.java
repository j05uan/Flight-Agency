package asiento.infraestructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import asiento.Domain.Entity.Asiento;
import asiento.Domain.Services.AsientoServices;
import resource.ConfiguracionBaseDeDatos;

public class AsientoRepository implements AsientoServices {

    @Override
    public void crearAsiento(Asiento asiento) {
        String sql = "INSERT INTO asientos (nombre) VALUES (?)";
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, asiento.getNombre());  // Asumiendo que 'nombre' es el único campo en la tabla

            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    asiento.setId(generatedKeys.getLong(1));  // Establecer el ID generado
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Asiento obtenerAsientoPorId(Long id) {
        String sql = "SELECT * FROM asientos WHERE id = ?";
        Asiento asiento = null;

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    asiento = new Asiento();
                    asiento.setId(resultSet.getLong("id"));
                    asiento.setNombre(resultSet.getString("nombre"));  // Obtener el valor de 'nombre'
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return asiento;
    }

    @Override
    public void actualizarAsiento(Asiento asiento) {
        String sql = "UPDATE asientos SET nombre = ? WHERE id = ?";

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, asiento.getNombre());
            statement.setLong(2, asiento.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarAsiento(Long id) {
        String sql = "DELETE FROM asientos WHERE id = ?";

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Asiento> obtenerTodasLasAsientos() {
        List<Asiento> asientos = new ArrayList<>();
        String sql = "SELECT * FROM asientos";

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Asiento asiento = new Asiento();
                asiento.setId(resultSet.getLong("id"));
                asiento.setNombre(resultSet.getString("nombre"));  // Obtener el valor de 'nombre'
                asientos.add(asiento);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return asientos;
    }

    
}
