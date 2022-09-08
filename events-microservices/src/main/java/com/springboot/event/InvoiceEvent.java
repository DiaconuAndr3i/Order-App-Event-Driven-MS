package com.springboot.event;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceEvent {
    private OrderEvent orderEvent;

    private String pathInvoice;

    private String nameInvoice;
}
