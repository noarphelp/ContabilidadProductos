package com.Contabilidad.EnvioMails.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Pedidos {

    private Long pedidosId;
    private int codProducto;
    private double total;
    private int unidades;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime fechaPedido;
}
