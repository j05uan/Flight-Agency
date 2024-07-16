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

public class ModeloRepository implements ModeloServices{


    public void CrearModelo (Modelo modelo){
        String sql ="";
    }

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
   
}
