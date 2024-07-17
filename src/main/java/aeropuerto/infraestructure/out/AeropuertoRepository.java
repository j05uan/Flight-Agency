package aeropuerto.infraestructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aeropuerto.domain.entity.Aeropuerto;
import aeropuerto.domain.services.AeropuertoServices;
import ciudad.Domain.Entity.Ciudad;
import ciudad.infraestructure.out.CiudadRepository;
import resource.ConfiguracionBaseDeDatos;

public class AeropuertoRepository implements AeropuertoServices{

    @Override
    public void crearAeropuerto(Aeropuerto aeropuerto) {
    String sql = "INSERT INTO aeropuertos(nombre, ciudad_id) VALUES(?, ?)";
    try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

        statement.setString(1, aeropuerto.getNombre());
        statement.setLong(2, aeropuerto.getCiudad().getId());  // Cambiado a Long

        int filasInsertadas = statement.executeUpdate();

        if (filasInsertadas > 0) {
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    // Establecer el ID en el objeto Aeropuerto
                    aeropuerto.setId(generatedKeys.getLong(1));
                }
            }
        } else {
            throw new SQLException("No se insertaron filas en la base de datos.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
        throw new RuntimeException("Error al crear el aeropuerto: " + e.getMessage(), e);
    }
    }   
    
    // @Override
    // public Aeropuerto encontrarAeropuerto(Long id) {
        
    // }

    @Override
    public void actualizarAeropuerto(Aeropuerto aeropuerto) {
        String sql =" UPDATE aeropuertos SET mombre = ? ciudad_id= ? ";

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, aeropuerto.getNombre());
                statement.setLong(2, aeropuerto.getCiudad().getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public void elimnarAeropuerto(Long id) {
        String sql = "DELETE FROM aeropuertos WHERE id = ?";
        try( Connection connection = ConfiguracionBaseDeDatos.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql) ){
            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public List<Aeropuerto> obtenerTodosLosAeropuertos() {
        List<Aeropuerto> aeropuertos = new  ArrayList<>();
        String sql = "SELECT * FROM aeropuertos";

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Aeropuerto aeropuerto = new Aeropuerto();
                aeropuerto.setId(resultSet.getLong("id"));
                aeropuerto.setNombre(resultSet.getString("nombre"));
                Ciudad ciudad = new CiudadRepository().obtenerCiudadPorId(resultSet.getLong("ciudad_id"));
                aeropuerto.setCiudad(ciudad);
                aeropuertos.add(aeropuerto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        

        return aeropuertos;
    }

private CiudadRepository ciudadRepository;  // Asegúrate de inicializar este repositorio en el constructor
@Override
public Aeropuerto obtenerAeropuertoPorId(long id) {
    String sql = "SELECT * FROM aeropuertos WHERE id = ?";
    Aeropuerto aeropuerto = null;
    
    try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setLong(1, id);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                aeropuerto = new Aeropuerto();
                aeropuerto.setId(resultSet.getLong("id"));
                aeropuerto.setNombre(resultSet.getString("nombre"));

                // Obtener la Ciudad asociada al Aeropuerto
                Long ciudadId = resultSet.getLong("ciudad_id");
                Ciudad ciudad = ciudadRepository.obtenerCiudadPorId(ciudadId);
                if (ciudad != null) {
                    aeropuerto.setCiudad(ciudad);
                } else {
                    System.out.println("Ciudad con ID " + ciudadId + " no encontrada.");
                }
            } else {
                System.out.println("No se encontró ningún aeropuerto con ID " + id + ".");
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
        // Manejo de errores más detallado
        throw new RuntimeException("Error al obtener el aeropuerto: " + e.getMessage(), e);
    }

    return aeropuerto;
}





    
}
