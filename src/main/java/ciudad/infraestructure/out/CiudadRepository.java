package ciudad.infraestructure.out;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ciudad.Domain.Entity.Ciudad;
import ciudad.Domain.Services.CiudadServices;
import pais.Domain.Entity.Pais;
import resource.ConfiguracionBaseDeDatos;

public class CiudadRepository implements CiudadServices {
    
     private final List<Ciudad> ciudades = new ArrayList<>();

    @Override
    public void crearCiudad(Ciudad ciudad) {
        String sql = "INSERT INTO ciudades (nombre,pais_id) VALUES (?,?)";
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, ciudad.getNombre());
            statement.setLong(4, ciudad.getPais().getId());  

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        ciudad.setId(generatedKeys.getLong(1));  // Establecer el ID generado
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



@Override
public Ciudad obtenerCiudadPorId(Long id) {
    Ciudad ciudad = null;
    String sql = "SELECT * FROM ciudades WHERE id = ?";

    try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setLong(1, id);

        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                ciudad = new Ciudad();
                ciudad.setId(resultSet.getLong("id"));
                ciudad.setNombre(resultSet.getString("nombre"));
                // Aquí asumimos que ciudad.getPais() devuelve un objeto Pais
                // Asegúrate de ajustar esto según la estructura de tu código y base de datos
                Pais pais = obtenerPaisPorId(resultSet.getLong("pais_id"));
                ciudad.setPais(pais);
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return ciudad;
}

private Pais obtenerPaisPorId(Long id) throws SQLException {
    String sql = "SELECT * FROM paises WHERE id = ?";
    try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setLong(1, id);

        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                Pais pais = new Pais();
                pais.setId(resultSet.getLong("id"));
                pais.setNombre(resultSet.getString("nombre"));
                // Otros atributos del país si los hay
                return pais;
            }
        }
    }
    return null;
}


@Override
public void actualizarCiudad(Ciudad ciudad) {
    String sql = "UPDATE ciudades SET nombre = ?, pais_id = ? WHERE id = ?";

    try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setString(1, ciudad.getNombre());
        statement.setLong(2, ciudad.getPais().getId());
        statement.setLong(3, ciudad.getId());

        statement.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    }
}


@Override
public void eliminarCiudad(Long id) {
    String sql = "DELETE FROM ciudades WHERE id = ?";

    try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setLong(1, id);

        statement.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    }
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
            ciudad.setNombre(resultSet.getString("nombre"));
            Pais pais = obtenerPaisPorId(resultSet.getLong("pais_id"));
            ciudad.setPais(pais);
            ciudades.add(ciudad);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return ciudades;
}
}
