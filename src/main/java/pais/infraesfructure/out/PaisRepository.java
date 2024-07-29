package pais.infraesfructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pais.Domain.Entity.Pais;
import pais.Domain.Services.PaisServices;
import resource.ConfiguracionBaseDeDatos;

public class PaisRepository implements PaisServices {

    @Override
    public void crearPais(Pais pais) {
        String sql = "INSERT INTO paises(nombre) VALUES (?)";
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, pais.getNombre());
            int filasInsertadas = statement.executeUpdate();

            if (filasInsertadas > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        pais.setId(generatedKeys.getLong(1));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Pais> obtenerTodosLosPaises() {
        List<Pais> paises = new ArrayList<>();
        String sql = "SELECT * FROM paises";
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Pais pais = new Pais();
                pais.setId(resultSet.getLong("id"));
                pais.setNombre(resultSet.getString("nombre"));
                paises.add(pais);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paises;
    }

    @Override
    public Pais obtenerPaisPorId(Long id) {
        Pais pais = null;
        String sql = "SELECT * FROM paises WHERE id = ?";
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    pais = new Pais();
                    pais.setId(resultSet.getLong("id"));
                    pais.setNombre(resultSet.getString("nombre"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pais;
    }

    @Override
    public void actualizarPais(Pais pais) {
        String sql = "UPDATE paises SET nombre = ? WHERE id = ?";
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, pais.getNombre());
            statement.setLong(2, pais.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarPais(Long id) {
        String sql = "DELETE FROM paises WHERE id = ?";
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
