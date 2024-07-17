package asiento.infraestructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import asiento.Domain.Entity.Asiento;
import asiento.Domain.Services.AsientoServices;
import ciudad.Domain.Entity.Ciudad;
import ciudad.infraestructure.out.CiudadRepository;
import resource.ConfiguracionBaseDeDatos;

public class AsientoRepository implements AsientoServices{
    public void crearAsiento(Asiento asiento) {
        String sql = "INSERT INTO asientos(avion_id, fila, columna, disponible) VALUES(?, ?, ?, ?)";
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setLong(1, asiento.getAvion().getId());
            statement.setLong(2, asiento.getFila());
            statement.setString(3, asiento.getColumna());
            statement.setBoolean(4, asiento.isDisponible());

            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    asiento.setId(generatedKeys.getLong(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Asiento obtenerAsientoPorId(Long id) {
        Asiento asiento = null;
        String sql = "SELECT * FROM asientos WHERE id = ?";

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    asiento = new Asiento();
                    asiento.setId(resultSet.getLong("id"));
                    
                  
                    asiento.setFila(resultSet.getInt("fila"));
                    asiento.setColumna(resultSet.getString("columna"));
                    asiento.setDisponible(resultSet.getBoolean("disponible"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return asiento;
    }

    @Override
    public void actualizarAsiento(Asiento asiento) {
        String sql = "UPDATE asientos SET avion_id = ?, fila = ?, columna = ?, disponible = ? WHERE id = ?";
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, asiento.getAvion().getId());
            statement.setLong(2, asiento.getFila());
            statement.setString(3, asiento.getColumna());
            statement.setBoolean(4, asiento.isDisponible());
            statement.setLong(5, asiento.getId());

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
                asiento.setFila(resultSet.getInt("fila"));
                Ciudad ciudad = new CiudadRepository().obtenerCiudadPorId(resultSet.getLong("ciudad_id"));
        
                asientos.add(asiento);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        

        return asientos;
    }
}
