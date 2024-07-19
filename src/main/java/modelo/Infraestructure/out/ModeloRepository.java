package modelo.Infraestructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Domain.entity.Modelo;
import modelo.Domain.services.ModeloServices;
import resource.ConfiguracionBaseDeDatos;

public class ModeloRepository implements ModeloServices {

    @Override
    public void CrearModelo(Modelo modelo) {
        String sql = "INSERT INTO modelos(nombre) VALUES (?)";

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, modelo.getNombre());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        modelo.setId(generatedKeys.getLong(1));  // Establecer el ID generado
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Modelo> obtenerTodosLosModelos() {
        String sql = "SELECT * FROM modelos";
        List<Modelo> modelos = new ArrayList<>();

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Modelo modelo = new Modelo();
                modelo.setId(resultSet.getLong("id"));
                modelo.setNombre(resultSet.getString("nombre"));  // Ajustar según el esquema de tu base de datos
                modelos.add(modelo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modelos;
    }

    @Override
    public Modelo obtenerModeloPorId(Long id) {
        String sql = "SELECT * FROM modelos WHERE id = ?";
        Modelo modelo = null;

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    modelo = new Modelo();
                    modelo.setId(resultSet.getLong("id"));
                    modelo.setNombre(resultSet.getString("nombre"));  // Ajustar según el esquema de tu base de datos
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modelo;
    }

    @Override
    public void actualizarModelo(Modelo modelo) {
        String sql = "UPDATE modelos SET nombre = ? WHERE id = ?";

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, modelo.getNombre());
            statement.setLong(2, modelo.getId());

            int filasActualizadas = statement.executeUpdate();

            if (filasActualizadas == 0) {
                System.out.println("No se encontró ningún modelo con el ID proporcionado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarModelo(Long id) {
        String sql = "DELETE FROM modelos WHERE id = ?";

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            int filasEliminadas = statement.executeUpdate();

            if (filasEliminadas == 0) {
                System.out.println("No se encontró ningún modelo con el ID proporcionado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
