package com.demo.persistencia.demopersistencia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.persistencia.demopersistencia.entidades.Cliente;
import com.demo.persistencia.demopersistencia.repositorio.ClienteRepository;

import java.util.List;

@Service
public class ClienteServicio {

    @Autowired
    private ClienteRepository clienteRepositorio;

    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepositorio.save(cliente);
    }

    public List<Cliente> obtenerClientes() {
        return clienteRepositorio.findAll();
}
}fgfdhfhgf