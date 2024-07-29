package rutaEscala.interfaces.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aeropuerto.domain.entity.Aeropuerto;
import aeropuerto.infraestructure.out.AeropuertoRepository;
import avion.Domain.Entity.Avion;
import avion.Infraestructure.out.AvionRepository;
import resource.ConfiguracionBaseDeDatos;
import ruta.Domain.Entity.Ruta;
import ruta.Infraestructure.out.RutaRepository;
import rutaEscala.Domain.entity.RutaEscala;
import rutaEscala.Domain.services.RutaEscalaServices;

public class RutaEscalaRepository implements RutaEscalaServices {

    @Override
    public void crearRutaEscala(RutaEscala rutaEscala) {
        String sql = "INSERT INTO RutaEscala (idAeropuertoOrigen, idAeropuertoDestino, idVuelo, idAvion, horaLlegada, horaSalida) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            statement.setLong(1, rutaEscala.getAeropuertoOrigen().getId());
            statement.setLong(2, rutaEscala.getAeropuertoDestino().getId());
            statement.setLong(3, rutaEscala.getRuta().getId());
            statement.setLong(4, rutaEscala.getAvion().getId());
            statement.setString(5, rutaEscala.getHoraLlegada());
            statement.setString(6, rutaEscala.getHoraSalida());
            
            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        rutaEscala.setId(generatedKeys.getLong(1));  
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<RutaEscala> obtenerTodasRutaEscalas() {
        List<RutaEscala> rutasEscala = new ArrayList<>();
        String sql = "SELECT * FROM RutaEscala";
        
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                RutaEscala rutaEscala = mapResultSetToRutaEscala(resultSet);
                rutasEscala.add(rutaEscala);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return rutasEscala;
    }

    @Override
    public RutaEscala obtenerEscalaPorId(Long id) {
        RutaEscala rutaEscala = null;
        String sql = "SELECT * FROM RutaEscala WHERE id = ?";
        
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    rutaEscala = mapResultSetToRutaEscala(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return rutaEscala;
    }

    @Override
    public void actualizarEscala(RutaEscala rutaEscala) {
        String sql = "UPDATE RutaEscala SET idAeropuertoOrigen = ?, idAeropuertoDestino = ?, idVuelo = ?, idAvion = ?, horaLlegada = ?, horaSalida = ? WHERE id = ?";
        
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setLong(1, rutaEscala.getAeropuertoOrigen().getId());
            statement.setLong(2, rutaEscala.getAeropuertoDestino().getId());
            statement.setLong(3, rutaEscala.getRuta().getId());
            statement.setLong(4, rutaEscala.getAvion().getId());
            statement.setString(5, rutaEscala.getHoraLlegada());
            statement.setString(6, rutaEscala.getHoraSalida());
            statement.setLong(7, rutaEscala.getId());
            
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarEscala(Long id) {
        String sql = "DELETE FROM RutaEscala WHERE id = ?";
        
        try (Connection connection = ConfiguracionBaseDeDatos.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private RutaEscala mapResultSetToRutaEscala(ResultSet resultSet) throws SQLException {
        RutaEscala rutaEscala = new RutaEscala();
        rutaEscala.setId(resultSet.getLong("id"));
        
        AeropuertoRepository aeropuertoRepo = new AeropuertoRepository();
        RutaRepository rutaRepository = new RutaRepository();
        AvionRepository avionRepository = new AvionRepository();
        Aeropuerto aeropuerto = aeropuertoRepo.obtenerAeropuertoPorId((resultSet.getLong("idAeropuertoOrigen")));
        Aeropuerto aeropuerto2 = aeropuertoRepo.obtenerAeropuertoPorId((resultSet.getLong("idAeropuertoDestino")));
        Ruta ruta = rutaRepository.obtenerRutaPorId((resultSet.getLong("idVuelo"))); 
        Avion avion = avionRepository.obtenerAvionPorId(resultSet.getLong("idAvion"));
        rutaEscala.setAeropuertoDestino(aeropuerto2);
        rutaEscala.setAeropuertoOrigen(aeropuerto);
        rutaEscala.setAvion(avion);
        rutaEscala.setRuta(ruta);
        rutaEscala.setHoraLlegada(resultSet.getString("horaLlegada"));
        rutaEscala.setHoraSalida(resultSet.getString("horaSalida"));
        return rutaEscala;
    }
}
