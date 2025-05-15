package com.demo.persistencia.demopersistencia.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.persistencia.demopersistencia.entidades.Factura;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
}