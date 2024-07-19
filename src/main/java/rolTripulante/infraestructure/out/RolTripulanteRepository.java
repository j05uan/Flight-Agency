package rolTripulante.infraestructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import resource.ConfiguracionBaseDeDatos;
import rolTripulante.Domain.entity.RolTripulante;
import rolTripulante.Domain.services.RolTripulanteServices;

public class RolTripulanteRepository implements RolTripulanteServices {

    @Override
    public void crearRolTripulante(RolTripulante rolTripulante) {
        String sql = "INSERT INTO rol_tripulante(rol_tripulante) VALUES (?)";
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, rolTripulante.getRol());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        rolTripulante.setId(generatedKeys.getLong(1));  // Establecer el ID generado
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<RolTripulante> obtenerTodosLosRolTripulantes() {
        List<RolTripulante> roles = new ArrayList<>();
        String sql = "SELECT * FROM rol_tripulante";

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                RolTripulante rol = new RolTripulante();
                rol.setId(resultSet.getLong("id"));
                rol.setRol(resultSet.getString("rol_tripulante"));
                roles.add(rol);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roles;
    }

    @Override
    public RolTripulante obtenerRolTripulantePorId(Long id) {
        RolTripulante rolTripulante = null;
        String sql = "SELECT * FROM rol_tripulante WHERE id = ?";

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    rolTripulante = new RolTripulante();
                    rolTripulante.setId(resultSet.getLong("id"));
                    rolTripulante.setRol(resultSet.getString("rol_tripulante"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rolTripulante;
    }

    @Override
    public void actualizarRolTripulante(RolTripulante rolTripulante) {
        String sql = "UPDATE rol_tripulante SET rol_tripulante = ? WHERE id = ?";

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, rolTripulante.getRol());
            statement.setLong(2, rolTripulante.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminaRolTripulante(Long id) {
        String sql = "DELETE FROM rol_tripulante WHERE id = ?";

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
