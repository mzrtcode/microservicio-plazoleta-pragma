package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.repository;

import com.pragma.plazoletamicroservicio.domain.model.EstadoPedido;
import com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity.PedidoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IPedidoRepository extends JpaRepository<PedidoEntity, Long> {

    boolean existsByIdClienteAndEstadoPedidoIn(Long idCliente, List<EstadoPedido> estados);
    List<PedidoEntity> findByIdAndEstadoPedido(Long id, EstadoPedido estadoPedido);

    @Query("SELECT p FROM PedidoEntity p WHERE p.estadoPedido = :estadoPedido AND p.idChef = :idChef")
    Page<PedidoEntity> findByEstadoPedidoAndIdChef(Long idChef, EstadoPedido estadoPedido, Pageable pageable);

}
