package com.pragma.plazoletamicroservicio.infrastructure.output.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pragma.plazoletamicroservicio.domain.model.EstadoPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


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
    @JsonIgnore
    private RestauranteEntity restaurante;

    @Column(name = "id_empleado_asignado")
    private Long idEmpleadoAsignado;

}
