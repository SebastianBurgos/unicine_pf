package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.Entrada;
import co.edu.uniquindio.unicine.repositorios.EntradaRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EntradaServicioImpl implements EntradaServicio {

    private final EntradaRepo entradaRepo;

    public EntradaServicioImpl(EntradaRepo entradaRepo) {
        this.entradaRepo = entradaRepo;
    }

    @Override
    public Entrada obtenerEntrada(Integer codigoEntrada) throws Exception {
        Entrada entrada = entradaRepo.obtenerEntrada(codigoEntrada);

        if (entrada == null) throw new Exception("La entrada no existe");

        return entrada;
    }
}
