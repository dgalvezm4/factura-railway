package com.demo.persistencia.demopersistencia.controllers;

//import java.time.LocalDate;
//import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.demo.persistencia.demopersistencia.dto.DetalleFacturaRequest;
import com.demo.persistencia.demopersistencia.dto.FacturaDTO;
import com.demo.persistencia.demopersistencia.dto.FacturaRequest;
//import com.demo.persistencia.demopersistencia.dto.FacturaRequest;
//import com.demo.persistencia.demopersistencia.entidades.DetalleFactura;
//import com.demo.persistencia.demopersistencia.entidades.Factura;
import com.demo.persistencia.demopersistencia.services.FacturaService;

@RestController
@CrossOrigin(origins = "*") //pruebas locales.
@RequestMapping("/api/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;


    //busca clientes en la db por medio de un idCliente.
    @GetMapping("/cliente/{clienteId}")
    public List<FacturaDTO> getFacturasCliente(@PathVariable Long clienteId) {
        return facturaService.getFacturasCliente(clienteId);
    }


    @PostMapping("/registrar")

    public ResponseEntity<String> registrarFactura(@RequestBody FacturaRequest facturaRequest) {
    
        facturaService.registrarFacturaCompleta(facturaRequest); // <-- este
    
        return ResponseEntity.status(HttpStatus.CREATED).body("Factura registrada correctamente");
    
    }

}
