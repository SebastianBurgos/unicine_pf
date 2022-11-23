package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Funcion;
import co.edu.uniquindio.unicine.repositorios.FuncionRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FuncionServicioImpl implements FuncionServicio {

    private final FuncionRepo funcionRepo;

    public FuncionServicioImpl(FuncionRepo funcionRepo) {
        this.funcionRepo = funcionRepo;
    }

    @Override
    public Funcion obtenerFuncion(Integer codigo) throws Exception {

        Optional<Funcion> funcionExiste =  funcionRepo.findById(codigo);

        if (!funcionExiste.isPresent()) throw new Exception("La funcion no existe.");

        return funcionExiste.get();
    }
}
