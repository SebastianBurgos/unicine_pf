package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.ClienteCupon;
import co.edu.uniquindio.unicine.entidades.Cupon;
import co.edu.uniquindio.unicine.repositorios.CuponRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CuponServicioImpl implements CuponServicio {

    private final CuponRepo cuponRepo;

    public CuponServicioImpl(CuponRepo cuponRepo) {
        this.cuponRepo = cuponRepo;
    }

    @Override
    public Cupon obtenerCupon(Integer codigo) throws Exception {
        Optional<Cupon> guardado = cuponRepo.findById(codigo);

        if (!guardado.isPresent()) throw new Exception("El cupón no existe.");

        return guardado.get();
    }

    @Override
    public ClienteCupon obtenerClienteCupon(Integer codigoClienteCupon) throws Exception {

        ClienteCupon guardado = cuponRepo.obtenerClienteCupon(codigoClienteCupon);

        if (guardado == null) throw new Exception("El cupón no existe.");

        return guardado;
    }
}
