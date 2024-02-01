package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.repository;

import com.pragma.plazoletamicroservicio.domain.model.EstadoPedido;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IPedidoRepository extends JpaRepository<PedidoEntity, Long> {

    boolean existsByIdClienteAndEstadoPedidoIn(Long idCliente, List<EstadoPedido> estados);
    List<PedidoEntity> findByIdAndEstadoPedido(Long id, EstadoPedido estadoPedido);

    @Query("SELECT p FROM PedidoEntity p WHERE p.estadoPedido = :estado")
    List<PedidoEntity> findByEstadoPedido(EstadoPedido estado);
}
