package com.demo.persistencia.demopersistencia.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.persistencia.demopersistencia.dto.DetalleFacturaRequest;
import com.demo.persistencia.demopersistencia.dto.FacturaDTO;
import com.demo.persistencia.demopersistencia.dto.FacturaRequest;
import com.demo.persistencia.demopersistencia.entidades.Cliente;
import com.demo.persistencia.demopersistencia.entidades.DetalleFactura;
import com.demo.persistencia.demopersistencia.entidades.Factura;
import com.demo.persistencia.demopersistencia.entidades.Producto;
import com.demo.persistencia.demopersistencia.repositorio.ClienteRepository;
import com.demo.persistencia.demopersistencia.repositorio.FacturaCustomRepository;
import com.demo.persistencia.demopersistencia.repositorio.FacturaRepository;
import com.demo.persistencia.demopersistencia.repositorio.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepo;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private FacturaCustomRepository facturaCustomRepo;

    @Transactional
    public void registrarFacturaCompleta(FacturaRequest facturaRequest) {
        Cliente cliente = clienteRepository.findById(facturaRequest.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Factura factura = new Factura();
        factura.setCliente(cliente);
        factura.setFechaFactura(facturaRequest.getFechaFactura());
        factura.setTotal(facturaRequest.getTotal());

        List<DetalleFactura> detalles = new ArrayList<>();

        for (DetalleFacturaRequest d : facturaRequest.getDetalles()) {
            Producto producto = productoRepository.findById(d.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado (ID: " + d.getProductoId() + ")"));

            DetalleFactura detalle = new DetalleFactura();
            detalle.setFactura(factura); // Relación bidireccional importante
            detalle.setProducto(producto);
            detalle.setCantidad(d.getCantidad());
            detalle.setPrecioUnitario(d.getPrecioUnitario());

            detalles.add(detalle);
        }

        // Establecer los detalles en la factura
        factura.setDetalles(detalles);

        // Guardar la factura y en cascada los detalles
        facturaRepo.save(factura);
    }

    public List<FacturaDTO> getFacturasCliente(Long clienteId) {
        List<Object[]> results = facturaCustomRepo.getFacturasCliente(clienteId);
        List<FacturaDTO> facturas = new ArrayList<>();

        for (Object[] row : results) {
            FacturaDTO facturaDTO = new FacturaDTO();
            facturaDTO.setFacturaId((Long) row[0]);
            facturaDTO.setFechaFactura((LocalDate) row[1]);
            facturaDTO.setNombreCliente((String) row[2]);
            facturaDTO.setTotal((BigDecimal) row[3]);
            facturaDTO.setCantidadProducto((int) row[4]);
            facturaDTO.setPrecioUnitario((BigDecimal) row[5]);
            facturas.add(facturaDTO);
        }

        return facturas;
}
}
