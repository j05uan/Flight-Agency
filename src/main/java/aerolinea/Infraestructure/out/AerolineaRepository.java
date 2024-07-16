package aerolinea.Infraestructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aerolinea.Domain.Entity.Aerolinea;
import aerolinea.Domain.Services.AerolineaServices;
import resource.ConfiguracionBaseDeDatos;

public class AerolineaRepository implements AerolineaServices {

    @Override
    public void crearAerolinea(Aerolinea aerolinea) {
        String sql = "INSERT INTO aerolineas(nombre) VALUES(?)";
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, aerolinea.getNombre());

            int filasInsertadas = statement.executeUpdate();

            if (filasInsertadas > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        aerolinea.setId(generatedKeys.getLong(1));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Aerolinea> obtenerTodasLasAerolineas() {
        List<Aerolinea> aerolineas = new ArrayList<>();
        String sql = "SELECT * FROM aerolineas";

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Aerolinea aerolinea = new Aerolinea();
                aerolinea.setId(resultSet.getLong("id"));
                aerolinea.setNombre(resultSet.getString("nombre"));
                aerolineas.add(aerolinea);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return aerolineas;
    }

    @Override
    public Aerolinea obtenerAerolineaPorId(Long id) {
        String sql = "SELECT * FROM aerolineas WHERE id = ?";
        Aerolinea aerolinea = null;

        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    aerolinea = new Aerolinea();
                    aerolinea.setId(resultSet.getLong("id"));
                    aerolinea.setNombre(resultSet.getString("nombre"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return aerolinea;
    }

    @Override
    public void actualizarAerolinea(Aerolinea aerolinea) {
        String sql = "UPDATE aerolineas SET nombre = ? WHERE id = ?";
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, aerolinea.getNombre());
            statement.setLong(2, aerolinea.getId());

            int filasActualizadas = statement.executeUpdate();

            if (filasActualizadas == 0) {
                System.out.println("No se encontró ninguna aerolínea con el ID proporcionado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarAerolinea(Long id) {
        String sql = "DELETE FROM aerolineas WHERE id = ?";
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            int filasEliminadas = statement.executeUpdate();

            if (filasEliminadas == 0) {
                System.out.println("No se encontró ninguna aerolínea con el ID proporcionado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Este método no parece estar correctamente relacionado con la funcionalidad de listar aerolíneas, y ya está definido más arriba.
    // Si es necesario, elimina este método o reestructura según la lógica requerida.
    /*
    public List<Aerolinea> listarAerolineas() {
        List<Aerolinea> aerolineas = new ArrayList<>();
        String sql = "SELECT * FROM aerolineas";  // Consulta para seleccionar todas las aerolíneas

        try (
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
        ) {
            // Procesa los resultados
            while (rs.next()) {
                Aerolinea aerolinea = new Aerolinea();
                aerolinea.setId(rs.getLong("id"));
                aerolinea.setNombre(rs.getString("nombre"));
                aerolineas.add(aerolinea);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return aerolineas;
    }
    */

}
