package tipoDocumento.infraestructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import resource.ConfiguracionBaseDeDatos;
import tipoDocumento.Domain.entity.TipoDocumento;
import tipoDocumento.Domain.services.TipoDocumentoServices;

public class TipoDocumentoRepository implements TipoDocumentoServices {

    @Override
    public void crearTipoDocumento(TipoDocumento tipoDocumento) {
        String sql = "INSERT INTO tipos_documentos(tipo) VALUES (?)";  // Corregido "INSER" a "INSERT" y añadido paréntesis
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, tipoDocumento.getTipo());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        tipoDocumento.setId(generatedKeys.getLong(1));  // Establecer el ID generado
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TipoDocumento> obtenerTodosLosTiposDocumento() {
        List<TipoDocumento> tiposDocumento = new ArrayList<>();
        String sql = "SELECT * FROM tipos_documentos";
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                TipoDocumento tipoDocumento = new TipoDocumento();
                tipoDocumento.setId(resultSet.getLong("id"));
                tipoDocumento.setTipo(resultSet.getString("tipo"));
                tiposDocumento.add(tipoDocumento);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tiposDocumento;
    }

    @Override
    public TipoDocumento obtenerTipoDocumentoPorId(Long id) {
        String sql = "SELECT * FROM tipos_documentos WHERE id = ?";
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    TipoDocumento tipoDocumento = new TipoDocumento();
                    tipoDocumento.setId(resultSet.getLong("id"));
                    tipoDocumento.setTipo(resultSet.getString("tipo"));
                    return tipoDocumento;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void actualizarTipoDocumento(TipoDocumento tipoDocumento) {
        String sql = "UPDATE tipos_documentos SET tipo = ? WHERE id = ?";
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, tipoDocumento.getTipo());
            statement.setLong(2, tipoDocumento.getId());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("No se actualizó ningún tipo de documento.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarTipoDocumento(Long id) {
        String sql = "DELETE FROM tipos_documentos WHERE id = ?";
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("No se eliminó ningún tipo de documento.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
