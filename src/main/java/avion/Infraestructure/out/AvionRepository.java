package avion.Infraestructure.out;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aerolinea.Domain.Entity.Aerolinea;
import aerolinea.Infraestructure.out.AerolineaRepository;
import avion.Domain.Entity.Avion;
import avion.Domain.Services.AvionServices;
import modelo.Domain.entity.Modelo;
import modelo.Infraestructure.out.ModeloRepository;
import resource.ConfiguracionBaseDeDatos;

public class AvionRepository implements AvionServices {

    @Override
    public void CrearAvion(Avion avion) {
        String sql = "INSERT INTO aviones (matricula, capacidad, fecha_fabricacion, aerolinea_id, modelo_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, avion.getMatricula());
            statement.setInt(2, avion.getCapacidad());  // La capacidad es un entero
            statement.setDate(3, new Date(avion.getFechaFabricacion().getTime()));  // Convertir java.util.Date a java.sql.Date
            statement.setLong(4, avion.getAerolinea().getId());  // ID de Aerolinea
            statement.setLong(5, avion.getModelo().getId());  // ID de Modelo

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        avion.setId(generatedKeys.getLong(1));  // Establecer el ID generado
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Avion> obtenerTodosLosAviones() {
        String sql = "SELECT * FROM aviones";
        List<Avion> aviones = new ArrayList<>();

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Avion avion = mapResultSetToAvion(resultSet);
                aviones.add(avion);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return aviones;
    }

    // Método para obtener aviones por matrícula
    public Avion obtenerAvionesPorMatricula (String matricula) {
        String sql = "SELECT * FROM aviones WHERE matricula = ?";
        List<Avion> aviones = new ArrayList<>();

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, matricula);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Avion avion = mapResultSetToAvion(resultSet);
                    aviones.add(avion);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (Avion) aviones;
    }
    public Avion obtenerAvionPorId(Long id) {
        String sql = "SELECT * FROM aviones WHERE id = ?";
        Avion avion = null;

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    avion = mapResultSetToAvion(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return avion;
    }

    public void actualizarAvion(Avion avion) {
        String sql = "UPDATE aviones SET matricula = ?, capacidad = ?, fecha_fabricacion = ?, aerolinea_id = ?, modelo_id = ? WHERE id = ?";

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, avion.getMatricula());
            statement.setInt(2, avion.getCapacidad());  
            statement.setDate(3, new Date(avion.getFechaFabricacion().getTime()));
            statement.setLong(4, avion.getAerolinea().getId());  
            statement.setLong(5, avion.getModelo().getId());  
            statement.setLong(6, avion.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarAvion(Long id) {
        String sql = "DELETE FROM aviones WHERE id = ?";

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para mapear el ResultSet a un objeto Avion
    private Avion mapResultSetToAvion(ResultSet resultSet) throws SQLException {
        Avion avion = new Avion();
        avion.setId(resultSet.getLong("id"));
        avion.setMatricula(resultSet.getString("matricula"));
        avion.setCapacidad(resultSet.getInt("capacidad"));
        avion.setFechaFabricacion(resultSet.getDate("fecha_fabricacion"));  // Convertir java.sql.Date a java.util.Date
        Aerolinea aerolinea = new AerolineaRepository().obtenerAerolineaPorId(resultSet.getLong("aerolinea_id"));
        Modelo modelo = new ModeloRepository().obtenerModeloPorId(resultSet.getLong("modelo_id"));
        avion.setAerolinea(aerolinea);
        avion.setModelo(modelo);
        return avion;
    }


 

 


 


    
}
