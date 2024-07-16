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

public class FabricanteRepository implements FabricanteServices{

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
                        fabricante.setId(generatedKeys.getLong(1));  // Establecer el ID generado
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Fabricante> ObtenerTodosLosFabricantes() {
        String sql= "SELECT * FROM fabricantes";

        List<Fabricante> fabricantes = new ArrayList<>();
        
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Fabricante fabricante = mapResultSetToAvion(resultSet);
                fabricantes.add(fabricante);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }
    
    private Fabricante mapResultSetToAvion(ResultSet resultSet) throws SQLException {
        Fabricante fabricante = new Fabricante();
        fabricante.setId(resultSet.getLong("id"));
        fabricante.setNombre(resultSet.getString("Nombre"));
        return fabricante;
    }
}
