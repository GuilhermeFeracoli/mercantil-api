package com.guilherme.mercantilapi.controller;
import com.guilherme.mercantilapi.model.Cliente;
import com.guilherme.mercantilapi.service.ClienteService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import jakarta.validation.Valid;
import com.guilherme.mercantilapi.dto.ClienteRequest;
import com.guilherme.mercantilapi.dto.ClienteResponse;

@RestController
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
    @GetMapping("/clientes")
    public List<ClienteResponse> listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        return clientes.stream()
                .map(cliente -> new ClienteResponse(
                        cliente.getId(),
                        cliente.getNome(),
                        cliente.getEndereco(),
                        cliente.getTelefone(),
                        cliente.getEmail(),
                        cliente.getCpf(),
                        cliente.getCnpj(),
                        cliente.getInscricaoEstadual()))
                .toList();
    }
    @PostMapping("/clientes")
    public ClienteResponse cadastrarCliente(
            @Valid @RequestBody ClienteRequest request) {
        Cliente cliente = new Cliente();
        cliente.setNome(request.getNome());
        cliente.setEndereco(request.getEndereco());
        cliente.setTelefone(request.getTelefone());
        cliente.setEmail(request.getEmail());
        cliente.setCpf(request.getCpf());
        cliente.setCnpj(request.getCnpj());
        cliente.setInscricaoEstadual(request.getInscricaoEstadual());
        Cliente clienteSalvo = clienteService.cadastrarCliente(cliente);
        return new ClienteResponse(
                clienteSalvo.getId(),
                clienteSalvo.getNome(),
                clienteSalvo.getEndereco(),
                clienteSalvo.getTelefone(),
                clienteSalvo.getEmail(),
                clienteSalvo.getCpf(),
                clienteSalvo.getCnpj(),
                clienteSalvo.getInscricaoEstadual());
    }
    @GetMapping("/clientes/{id}")
    public ClienteResponse buscarCliente(@PathVariable Integer id) {
        Cliente cliente = clienteService.buscarCliente(id);
        return new ClienteResponse(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEndereco(),
                cliente.getTelefone(),
                cliente.getEmail(),
                cliente.getCpf(),
                cliente.getCnpj(),
                cliente.getInscricaoEstadual());
    }

    @PutMapping("/clientes/{id}")
    public ClienteResponse alterarCliente(
            @PathVariable Integer id,
            @Valid @RequestBody ClienteRequest request) {
        Cliente cliente = new Cliente();
        cliente.setNome(request.getNome());
        cliente.setEndereco(request.getEndereco());
        cliente.setTelefone(request.getTelefone());
        cliente.setEmail(request.getEmail());
        cliente.setCpf(request.getCpf());
        cliente.setCnpj(request.getCnpj());
        cliente.setInscricaoEstadual(request.getInscricaoEstadual());
        Cliente clienteAtualizado = clienteService.alterarCliente(id, cliente);
        return new ClienteResponse(
                clienteAtualizado.getId(),
                clienteAtualizado.getNome(),
                clienteAtualizado.getEndereco(),
                clienteAtualizado.getTelefone(),
                clienteAtualizado.getEmail(),
                clienteAtualizado.getCpf(),
                clienteAtualizado.getCnpj(),
                clienteAtualizado.getInscricaoEstadual());
    }

    @DeleteMapping("/clientes/{id}")
    public ClienteResponse excluirCliente(@PathVariable Integer id) {
        Cliente clienteExcluido = clienteService.excluirCliente(id);
        return new ClienteResponse(
                clienteExcluido.getId(),
                clienteExcluido.getNome(),
                clienteExcluido.getEndereco(),
                clienteExcluido.getTelefone(),
                clienteExcluido.getEmail(),
                clienteExcluido.getCpf(),
                clienteExcluido.getCnpj(),
                clienteExcluido.getInscricaoEstadual());
    }
}