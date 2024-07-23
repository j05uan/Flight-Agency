package historialTarifa.infraestructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import historialTarifa.Domain.entity.HistorialTarifa;
import historialTarifa.Domain.services.HistorialTarifaServices;
import resource.ConfiguracionBaseDeDatos;
import tarifa.interfaces.out.TarifaRepository;

public class HistorialTarifaRepository implements HistorialTarifaServices {

    @Override
    public void crearHistorialTarifa(HistorialTarifa historialTarifa) {
        String sql = "INSERT INTO historial_tarifas(tarifa_id, fecha_inicio, valor) VALUES (?, ?, ?)";
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            statement.setLong(1, historialTarifa.getTarifa().getId());
            statement.setDate(2, new java.sql.Date(historialTarifa.getFechaInicio().getTime())); // Convertir a java.sql.Date
            statement.setBigDecimal(3, historialTarifa.getValor());
    
            int affectedRows = statement.executeUpdate();
    
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        historialTarifa.setId(generatedKeys.getLong(1));  // Establecer el ID generado
                    }
                }
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public List<HistorialTarifa> obtenerTodosLosHistorialesTarifa() {
        String sql = "SELECT id, tarifa_id, fecha_inicio, valor FROM historial_tarifas";
        List<HistorialTarifa> historiales = new ArrayList<>();
        TarifaRepository tarifaRepository = new TarifaRepository();
        
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                HistorialTarifa historial = new HistorialTarifa();
                historial.setId(resultSet.getLong("id"));
                historial.setTarifa(tarifaRepository.obtenerTarifaPorId(resultSet.getLong("Tarifa_id")));
                historial.setFechaInicio(resultSet.getDate("fecha_inicio"));
                historial.setValor(resultSet.getBigDecimal("valor"));
                historiales.add(historial);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return historiales;
    }

    @Override
    public HistorialTarifa obtenertHistorialTarifaPorId(Long id) {
        String sql = "SELECT id, tarifa_id, fecha_inicio, valor FROM historial_tarifas WHERE id = ?";
        HistorialTarifa historial = null;
        TarifaRepository tarifaRepository = new TarifaRepository();
        
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    historial = new HistorialTarifa();
                    historial.setId(resultSet.getLong("id"));
                    historial.setTarifa(tarifaRepository.obtenerTarifaPorId(resultSet.getLong("Tarifa_id")));
                    historial.setFechaInicio(resultSet.getDate("fecha_inicio"));
                    historial.setValor(resultSet.getBigDecimal("valor"));
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return historial;
    }

    @Override
    public void actualizarHistorialTarifa(HistorialTarifa historialTarifa) {
        String sql = "UPDATE historial_tarifas SET tarifa_id = ?, fecha_inicio = ?, valor = ? WHERE id = ?";
        
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setLong(1, historialTarifa.getTarifa().getId());
            statement.setDate(2, new java.sql.Date(historialTarifa.getFechaInicio().getTime())); // Convertir a java.sql.Date
            statement.setBigDecimal(3, historialTarifa.getValor());
            statement.setLong(4, historialTarifa.getId());
    
            statement.executeUpdate();
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarHistorialTarifa(Long id) {
        String sql = "DELETE FROM historial_tarifas WHERE id = ?";
        
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setLong(1, id);
    
            statement.executeUpdate();
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
