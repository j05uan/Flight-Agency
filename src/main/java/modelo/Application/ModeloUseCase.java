package modelo.Application;

import java.util.List;
import modelo.Domain.entity.Modelo;
import modelo.Domain.services.ModeloServices;

public class ModeloUseCase {

    private final ModeloServices modeloServices;

    public ModeloUseCase(ModeloServices modeloServices) {
        this.modeloServices = modeloServices;
    }


    public void crearModelo(Modelo modelo) {
        modeloServices.CrearModelo(modelo);
    }

   
    public List<Modelo> obtenerTodosLosModelos() {
        return modeloServices.obtenerTodosLosModelos();
    }


    public Modelo obtenerModeloPorId(Long id) {
        return modeloServices.obtenerModeloPorId(id);
    }


    public void listarModelos() {
        List<Modelo> modelos = obtenerTodosLosModelos();
        if (modelos.isEmpty()) {
            System.out.println("No hay modelos disponibles.");
        } else {
            System.out.println("Modelos disponibles:");
            for (Modelo modelo : modelos) {
                System.out.printf("ID: %d, Nombre: %s%n", modelo.getId(), modelo.getNombre());
            }
        }
    }
}
