package cliente.infraestructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cliente.Domain.entity.Cliente;
import cliente.Domain.services.ClienteServices;
import resource.ConfiguracionBaseDeDatos;
import tipoDocumento.Domain.entity.TipoDocumento;
import tipoDocumento.infraestructure.out.TipoDocumentoRepository;

public class ClienteRepository implements ClienteServices {

    private final TipoDocumentoRepository tipoDocumentoRepo = new TipoDocumentoRepository();

    @Override
    public void CrearCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes(nombre, edad, tipo_documento_id, documento) VALUES (?, ?, ?, ?)";
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, cliente.getNombre());
            statement.setInt(2, cliente.getEdad());
            statement.setLong(3, cliente.getTipoDocumento().getId());
            statement.setString(4, cliente.getDocumento());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        cliente.setId(generatedKeys.getLong(1));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Cliente> obtenerTodosLosClientes() {
        String sql = "SELECT * FROM clientes";
        List<Cliente> clientes = new ArrayList<>();
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Cliente cliente = mapResultSetToCliente(resultSet);
                clientes.add(cliente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    @Override
    public Cliente obtenerClientePorId(Long id) {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        Cliente cliente = null;
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    cliente = mapResultSetToCliente(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    @Override
    public void actualizarCliente(Cliente cliente) {
        String sql = "UPDATE clientes SET nombre = ?, edad = ?, tipo_documento_id = ?, documento = ? WHERE id = ?";
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, cliente.getNombre());
            statement.setInt(2, cliente.getEdad());
            statement.setLong(3, cliente.getTipoDocumento().getId());
            statement.setString(4, cliente.getDocumento());
            statement.setLong(5, cliente.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarCliente(Long id) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Cliente mapResultSetToCliente(ResultSet resultSet) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(resultSet.getLong("id"));
        cliente.setNombre(resultSet.getString("nombre"));
        cliente.setEdad(resultSet.getInt("edad"));
        cliente.setDocumento(resultSet.getString("documento"));

        TipoDocumento tipoDocumento = tipoDocumentoRepo.obtenerTipoDocumentoPorId(resultSet.getLong("tipo_documento_id"));
        cliente.setTipoDocumento(tipoDocumento);
        return cliente;
    }
}
