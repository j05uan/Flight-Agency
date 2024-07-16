package ciudad.infraestructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ciudad.Domain.Entity.Ciudad;
import ciudad.Domain.Services.CiudadServices;
import resource.ConfiguracionBaseDeDatos;

public class CiudadRepository implements CiudadServices {
    
     private final List<Ciudad> ciudades = new ArrayList<>();

    @Override
    public void crearCiudad(Ciudad ciudad) {
        ciudades.add(ciudad);
    }



    @Override
    public Ciudad obtenerCiudadPorId(Long id) {
        return ciudades.stream()
                .filter(ciudad -> ciudad.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void actualizarCiudad(Ciudad ciudad) {
        Ciudad ciudadExistente = obtenerCiudadPorId(ciudad.getId());
        if (ciudadExistente != null) {
            ciudadExistente.setNombre(ciudad.getNombre());
            ciudadExistente.setPais(ciudad.getPais());
        }
    }

    @Override
    public void eliminarCiudad(Long id) {
        ciudades.removeIf(ciudad -> ciudad.getId().equals(id));
    }



    @Override
    public List<Ciudad> obtenerTodasLasCiudades() {
        List<Ciudad> ciudades = new ArrayList<>();
        String sql = "SELECT * FROM ciudades";
    
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
    
            while (resultSet.next()) {
                Ciudad ciudad = new Ciudad();
                ciudad.setId(resultSet.getLong("id"));
                ciudad.setNombre(resultSet.getString("nombre"));  // Asegúrate de que el nombre de la columna es "nombre" y no "Nombre"
                ciudades.add(ciudad);  // Agregar ciudad a la lista
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return ciudades;  // Devolver la lista después de cerrar los recursos
    }
    
}
