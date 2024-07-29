package tipoTarifa.infraestructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import resource.ConfiguracionBaseDeDatos;
import tipoTarifa.Domain.entity.TipoTarifa;
import tipoTarifa.Domain.services.TipoTarifaServices;

public class TipoTarifaRepository implements TipoTarifaServices {

    @Override
    public void crearTipoTarifa(TipoTarifa tipoTarifa) {
        String sql = "INSERT INTO tipo_tarifa (tipo) VALUES (?)";
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, tipoTarifa.getTipo());
            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        tipoTarifa.setId(generatedKeys.getLong(1));  // Establecer el ID generado
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TipoTarifa> obtenerTodosLosTiposTarifa() {
        List<TipoTarifa> tiposTarifa = new ArrayList<>();
        String sql = "SELECT * FROM tipo_tarifa";
        
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                TipoTarifa tipoTarifa = new TipoTarifa();
                tipoTarifa.setId(resultSet.getLong("id"));
                tipoTarifa.setTipo(resultSet.getString("tipo"));
                tiposTarifa.add(tipoTarifa);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return tiposTarifa;
    }

    @Override
    public TipoTarifa obtenerTipoTarifaPorId(Long id) {
        TipoTarifa tipoTarifa = null;
        String sql = "SELECT * FROM tipo_tarifa WHERE id = ?";
        
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    tipoTarifa = new TipoTarifa();
                    tipoTarifa.setId(resultSet.getLong("id"));
                    tipoTarifa.setTipo(resultSet.getString("tipo"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return tipoTarifa;
    }

    @Override
    public void actualizarTipoTarifa(TipoTarifa tipoTarifa) {
        String sql = "UPDATE tipo_tarifa SET tipo = ? WHERE id = ?";
        
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, tipoTarifa.getTipo());
            statement.setLong(2, tipoTarifa.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarTipoTarifa(Long id) {
        String sql = "DELETE FROM tipo_tarifa WHERE id = ?";
        
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
