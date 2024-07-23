package tarifa.interfaces.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import resource.ConfiguracionBaseDeDatos;
import tarifa.Domain.entity.Tarifa;
import tarifa.Domain.services.TarifaServices;
import tipoTarifa.infraestructure.out.TipoTarifaRepository;

public class TarifaRepository implements TarifaServices {

    @Override
    public void crearTarifa(Tarifa tarifa) {
        String sql = "INSERT INTO tarifas (tipo_tarifa_id, valor) VALUES (?, ?)";
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Establecer los parámetros de la consulta
            statement.setLong(1, tarifa.getTipoTarifa().getId());
            statement.setBigDecimal(2, tarifa.getValor()); // Corregido el índice del parámetro a 2

            // Ejecutar la consulta
            int affectedRows = statement.executeUpdate();

            // Verificar si se generaron claves
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        tarifa.setId(generatedKeys.getLong(1));  // Establecer el ID generado
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Tarifa> obtenerTodasLasTarifas() {
        String sql = "SELECT id, tipo_tarifa_id, valor FROM tarifas";
        List<Tarifa> tarifas = new ArrayList<>();
        TipoTarifaRepository tipoTarifaRepository = new TipoTarifaRepository();

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                Tarifa tarifa = new Tarifa();
                tarifa.setId(resultSet.getLong("id"));
                tarifa.setTipoTarifa(tipoTarifaRepository.obtenerTipoTarifaPorId(resultSet.getLong("Tipo Tarifa id")));
                tarifa.setValor(resultSet.getBigDecimal("valor"));
                tarifas.add(tarifa);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return tarifas;
    }

    @Override
    public Tarifa obtenerTarifaPorId(Long id) {
        String sql = "SELECT id, tipo_tarifa_id, valor FROM tarifas WHERE id = ?";
        Tarifa tarifa = null;
        TipoTarifaRepository tipoTarifaRepository = new TipoTarifaRepository();
        
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    tarifa = new Tarifa();
                    tarifa.setId(resultSet.getLong("id"));
                    tarifa.setTipoTarifa(tipoTarifaRepository.obtenerTipoTarifaPorId(resultSet.getLong("Tipo Tarifa id")));
                    tarifa.setValor(resultSet.getBigDecimal("valor"));
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return tarifa;
    }

    @Override
    public void actualizarTarifa(Tarifa tarifa) {
        String sql = "UPDATE tarifas SET tipo_tarifa_id = ?, valor = ? WHERE id = ?";
        
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            // Establecer los parámetros de la consulta
            statement.setLong(1, tarifa.getTipoTarifa().getId());
            statement.setBigDecimal(2, tarifa.getValor());
            statement.setLong(3, tarifa.getId());

            // Ejecutar la consulta
            statement.executeUpdate();
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarTarifa(Long id) {
        String sql = "DELETE FROM tarifas WHERE id = ?";
        
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setLong(1, id);
    
            // Ejecutar la consulta
            statement.executeUpdate();
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
