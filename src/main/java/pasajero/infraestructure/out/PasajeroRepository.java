package pasajero.infraestructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cliente.Domain.entity.Cliente;
import cliente.infraestructure.out.ClienteRepository;
import pasajero.Domain.entity.Pasajero;
import pasajero.Domain.services.PasajeroServices;
import resource.ConfiguracionBaseDeDatos;

public class PasajeroRepository implements PasajeroServices {

    @Override
    public void crearPasajero(Pasajero pasajero) {
        String sql = "INSERT INTO pasajeros(cliente_id, nombre, apellido) VALUES (?, ?, ?)";
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setLong(1, pasajero.getCliente().getId());
            statement.setString(2, pasajero.getNombre());
            statement.setString(3, pasajero.getApellido());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        pasajero.setId(generatedKeys.getLong(1));  // Establecer el ID generado
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Pasajero> obtenerTodosLosPasajeros() {
        List<Pasajero> pasajeros = new ArrayList<>();
        String sql = "SELECT * FROM pasajeros";  // Corrige el nombre de la tabla

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Pasajero pasajero = mapResultSetToPasajero(resultSet);
                pasajeros.add(pasajero);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pasajeros;
    }

    @Override
    public Pasajero obtenerPasajeroPorId(Long id) {
        String sql = "SELECT * FROM pasajeros WHERE id = ?";
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToPasajero(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;  // Retorna null si no se encuentra el pasajero
    }

    @Override
    public void actualizarPasajero(Pasajero pasajero) {
        String sql = "UPDATE pasajeros SET cliente_id = ?, nombre = ?, apellido = ? WHERE id = ?";
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, pasajero.getCliente().getId());
            statement.setString(2, pasajero.getNombre());
            statement.setString(3, pasajero.getApellido());
            statement.setLong(4, pasajero.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarPasajero(Long id) {
        String sql = "DELETE FROM pasajeros WHERE id = ?";
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Pasajero mapResultSetToPasajero(ResultSet resultSet) throws SQLException {
        Pasajero pasajero = new Pasajero();
        pasajero.setId(resultSet.getLong("id"));
        Cliente cliente = new ClienteRepository().obtenerClientePorId(resultSet.getLong("cliente_id"));  // Asegúrate de que ClienteRepository tiene este método
        pasajero.setCliente(cliente);
        pasajero.setNombre(resultSet.getString("nombre"));
        pasajero.setApellido(resultSet.getString("apellido"));
        return pasajero;
    }
}
