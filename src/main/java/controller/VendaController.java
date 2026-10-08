package com.guilherme.mercantilapi.controller;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import com.guilherme.mercantilapi.dto.VendaRequest;
import com.guilherme.mercantilapi.model.Venda;
import com.guilherme.mercantilapi.service.VendaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
public class VendaController {

    private final VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @GetMapping("/vendas")
    public List<Venda> listarVendas() {
        return vendaService.listarVendas();
    }

    @PostMapping("/vendas")
    public Venda cadastrarVenda(@Valid @RequestBody VendaRequest vendaRequest) {
        return vendaService.cadastrarVenda(vendaRequest);
    }
    @GetMapping("/vendas/{id}")
    public Venda buscarVenda(@PathVariable Integer id) {
        return vendaService.buscarVenda(id);
    }
}