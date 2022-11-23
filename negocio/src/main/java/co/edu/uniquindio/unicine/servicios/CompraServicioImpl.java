package co.edu.uniquindio.unicine.servicios;

import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.entidades.CompraConfiteria;
import co.edu.uniquindio.unicine.repositorios.CompraRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompraServicioImpl implements CompraServicio {

    private final CompraRepo compraRepo;

    public CompraServicioImpl(CompraRepo compraRepo) {
        this.compraRepo = compraRepo;
    }

    @Override
    public Compra obtenerCompra(Integer idCompra) throws Exception {

        Optional<Compra> compra = compraRepo.findById(idCompra);

        if(!compra.isPresent()) throw new Exception("La compra buscada no existe");

        return compra.get();
    }

    @Override
    public CompraConfiteria obtenerCompraConfiteria(Integer codigoCompraConfiteria) throws Exception {

        CompraConfiteria compraConfiteria = compraRepo.obtenerCompraConfiteria(codigoCompraConfiteria);

        if (compraConfiteria == null) throw new Exception("La compra en confiteria no existe");

        return compraConfiteria;
    }
}
