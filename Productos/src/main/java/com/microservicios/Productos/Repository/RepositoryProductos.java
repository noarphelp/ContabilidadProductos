package com.microservicios.Productos.Repository;

import com.microservicios.Productos.Model.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryProductos extends JpaRepository<Productos ,Integer> {
}
