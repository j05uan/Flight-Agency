package com.aerolinea;

import java.sql.SQLException;

import aerolinea.Domain.Services.AerolineaServices;
import aerolinea.Infraestructure.in.AerolineaControlador;
import aerolinea.Infraestructure.out.AerolineaRepository;
import aerolinea.application.AerolineaUseCase;
import resource.ConfiguracionBaseDeDatos;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("--- Agencia Vuelos Globales---");
        System.out.println("Bienvenidos");
        AerolineaServices aerolineaServices = new AerolineaRepository();
        ConfiguracionBaseDeDatos configuracionBaseDeDatos = new  ConfiguracionBaseDeDatos();
        ConfiguracionBaseDeDatos.getConnection();
        AerolineaUseCase aerolineaUseCase = new AerolineaUseCase(aerolineaServices);

        AerolineaControlador consolaAdaptador= new AerolineaControlador(aerolineaUseCase);
        
        consolaAdaptador.crearAerolinea();
    }
}