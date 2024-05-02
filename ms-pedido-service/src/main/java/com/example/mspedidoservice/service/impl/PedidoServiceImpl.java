package com.example.mspedidoservice.service.impl;

import com.example.mspedidoservice.entity.Pedido;
import com.example.mspedidoservice.entity.PedidoDetalle;
import com.example.mspedidoservice.feing.ClienteFeing;
import com.example.mspedidoservice.feing.ProductoFeing;
import com.example.mspedidoservice.repository.PedidoRepository;
import com.example.mspedidoservice.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository ;
    @Autowired
    private ClienteFeing clienteFeing;
    @Autowired
    private ProductoFeing productoFeing;


    @Override
    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido guardar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido buscarPorId(Integer id) {

        Pedido pedido = pedidoRepository.findById(id).get();
        pedido.setClienteDto(clienteFeing.busacarPorId(pedido.getClienteId()).getBody());
       /* for (PedidoDetalle pedidoDetalle: pedido.getDetalle()){
            pedidoDetalle.setProducto(productoFeing.busacarPorId(pedidoDetalle.getProductoId()).getBody());
        }*/

        List<PedidoDetalle>pedidoDetalles = pedido.getDetalle().stream().map(pedidoDetalle -> {
            pedidoDetalle.setProductoDto(productoFeing.busacarPorId(pedidoDetalle.getProductoId()).getBody());
            return pedidoDetalle;
    }).toList();

        return pedido;
    }

    @Override
    public Pedido editar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public void eliminar(Integer id) {
        pedidoRepository.deleteById(id);
    }
}
