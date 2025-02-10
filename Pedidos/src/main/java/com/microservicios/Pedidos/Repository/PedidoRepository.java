package com.microservicios.Pedidos.Repository;

import com.microservicios.Pedidos.Model.Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedidos ,Long> {
}
