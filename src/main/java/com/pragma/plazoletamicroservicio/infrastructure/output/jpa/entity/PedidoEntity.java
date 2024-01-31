package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity;

import com.pragma.plazoletamicroservicio.domain.model.EstadoPedido;
import com.pragma.plazoletamicroservicio.domain.model.Restaurante;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedidos")
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_cliente")
    private Long idCliente;

    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_pedido")
    private EstadoPedido estadoPedido;

    @Column(name = "id_chef")
    private Long idChef;

    @ManyToOne
    @JoinColumn(name = "id_restaurante")
    private RestauranteEntity restaurante;

    @OneToMany(mappedBy = "pedidoEntity")
    private List<PedidoPlatoEntity> pedidoPlatoEntity;

}
