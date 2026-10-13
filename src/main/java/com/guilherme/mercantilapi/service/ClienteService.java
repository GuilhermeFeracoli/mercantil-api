package com.guilherme.mercantilapi.service;
import com.guilherme.mercantilapi.model.Cliente;
import com.guilherme.mercantilapi.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import com.guilherme.mercantilapi.exception.ClienteNaoEncontradoException;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente cadastrarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    public Cliente buscarCliente(Integer id) {
        return clienteRepository.findById(id)
                .orElseThrow(() ->
                        new ClienteNaoEncontradoException("Cliente não encontrado"));
    }
    public Cliente alterarCliente(Integer id, Cliente cliente) {

        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() ->
                        new ClienteNaoEncontradoException("Cliente não encontrado"));

        clienteExistente.setNome(cliente.getNome());
        clienteExistente.setEndereco(cliente.getEndereco());
        clienteExistente.setTelefone(cliente.getTelefone());
        clienteExistente.setEmail(cliente.getEmail());
        clienteExistente.setCpf(cliente.getCpf());
        clienteExistente.setCnpj(cliente.getCnpj());
        clienteExistente.setInscricaoEstadual(cliente.getInscricaoEstadual());

        return clienteRepository.save(clienteExistente);
    }
    public Cliente excluirCliente(Integer id) {

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() ->
                        new ClienteNaoEncontradoException("Cliente não encontrado"));

        clienteRepository.delete(cliente);

        return cliente;
    }
}