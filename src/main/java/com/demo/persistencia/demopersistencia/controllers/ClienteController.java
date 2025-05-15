package com.demo.persistencia.demopersistencia.controllers;


import com.demo.persistencia.demopersistencia.entidades.Cliente;

import com.demo.persistencia.demopersistencia.services.ClienteServicio;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;



import java.util.List;



@RestController

@RequestMapping("/api/clientes")

@CrossOrigin(origins = "*") 

public class ClienteController {



    @Autowired

    private ClienteServicio clienteServicio;



    @PostMapping("/registrar")

    public ResponseEntity<Cliente> registrarCliente(@RequestBody Cliente cliente) {

        Cliente guardado = clienteServicio.guardarCliente(cliente);

        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);

    }



    @GetMapping("/listar")

    public List<Cliente> listarClientes() {

        return clienteServicio.obtenerClientes();

}

}
