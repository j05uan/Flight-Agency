package fabricante.infraestructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fabricante.Domain.Entity.Fabricante;
import fabricante.Domain.Services.FabricanteServices;
import resource.ConfiguracionBaseDeDatos;

public class FabricanteRepository implements FabricanteServices {

    @Override
    public void CrearFabricante(Fabricante fabricante) {
        String sql = "INSERT INTO fabricantes(nombre) VALUES (?)";

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, fabricante.getNombre());
            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        fabricante.setId(generatedKeys.getLong(1));  // Set the generated ID
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Fabricante> ObtenerTodosLosFabricantes() {
        String sql = "SELECT * FROM fabricantes";

        List<Fabricante> fabricantes = new ArrayList<>();

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Fabricante fabricante = mapResultSetToFabricante(resultSet);
                fabricantes.add(fabricante);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fabricantes;
    }

    private Fabricante mapResultSetToFabricante(ResultSet resultSet) throws SQLException {
        Fabricante fabricante = new Fabricante();
        fabricante.setId(resultSet.getLong("id"));
        fabricante.setNombre(resultSet.getString("Nombre"));
        return fabricante;
    }

    @Override
    public Fabricante obtenerFabricantePorId(Long id) {
        String sql = "SELECT * FROM fabricantes WHERE id = ?";
        Fabricante fabricante = null;

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    fabricante = mapResultSetToFabricante(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fabricante;
    }

    @Override
    public void actualizarFabricante(Fabricante fabricante) {
        String sql = "UPDATE fabricantes SET nombre = ? WHERE id = ?";
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, fabricante.getNombre());
            statement.setLong(2, fabricante.getId());

            int filasActualizadas = statement.executeUpdate();

            if (filasActualizadas == 0) {
                System.out.println("No se encontró ninguna fabricante con el ID proporcionado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarFabricante(Long id) {
        String sql = "DELETE FROM fabricantes WHERE id = ?";
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            int filasEliminadas = statement.executeUpdate();

            if (filasEliminadas == 0) {
                System.out.println("No se encontró ninguna fabricante con el ID proporcionado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
