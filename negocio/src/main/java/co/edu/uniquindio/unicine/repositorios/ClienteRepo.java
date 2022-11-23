package co.edu.uniquindio.unicine.repositorios;

import co.edu.uniquindio.unicine.entidades.Cliente;
import co.edu.uniquindio.unicine.entidades.Compra;
import co.edu.uniquindio.unicine.entidades.Cupon;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepo extends JpaRepository<Cliente, String> {

    @Query("select c from Cliente c where c.email = :email")
    Cliente obtenerPorCorreo(String email);

    @Query("select c from Cliente c where c.cedula = :cedula and c.contrasenia = :contrasenia")
    Cliente comprobarAutenticacion(String cedula, String contrasenia);

    @Query("select cup.cupon from Cliente cli left join cli.clienteCupones cup where cup.cliente.cedula = :cedula")
    List<Cupon> obtenerCuponesCliente(String cedula);

    @Query("select c from Compra c where c.cliente.cedula = :cedulaCliente")
    List<Compra> obtenerCompras(String cedulaCliente);

    @Query("select c from Cliente c where c.email = :correo")
    Cliente obtenerClientePorCorreo (String correo);

    /*
    @Query("select comp from Cliente cli, in (cli.compras) comp where cli.email = :correoCliente")
    List<Compra> obtenerComprasPorCorreoCliente(String correoCliente);
     */

    /*
    @Query("select cli.nombreCompleto, count (cup) from Cliente cli left join cli.clienteCupones cup where cup.cupon.estado = 'REDIMIDO' group by cli")
    List<Object[]> obtenerNumeroCuponeRedimidosPorCadaCliente();
     */
}
