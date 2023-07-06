package com.fvukic.webshop.order;

import com.fvukic.webshop.order.model.OrderRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderControllerTest {
    @InjectMocks // Oznaka za klasu koju testiramo
    OrderController orderController;

    @Mock // Oznaka za klase koje oponašamo
    OrderService orderService;

    private MockMvc mockMvc; // Za slanje zahtjeva

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Inicijalizacija Mockito anotacija
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build(); // Postavljanje MockMvc instance
    }

    @Test
    public void getAllOrdersTest() throws Exception {
        Order order = new Order();
        List<Order> orders = Collections.singletonList(order);
        when(orderService.getAllOrders()).thenReturn(orders); // Definiramo očekivano ponašanje

        // Simuliramo GET zahtjev i provjeravamo status odgovora
        mockMvc.perform(get("/order"))
                .andExpect(status().isOk());

        verify(orderService, times(1)).getAllOrders(); // Verificiramo da je metoda pozvana točno jednom
    }

    @Test
    public void saveNewOrderRequestTest() throws Exception {
        OrderRequest request = new OrderRequest();
        doNothing().when(orderService).saveNewOrderRequest(any(OrderRequest.class)); // Definiramo očekivano ponašanje

        // Simuliramo POST zahtjev, provjeravamo status odgovora
        mockMvc.perform(post("/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}")) // You should use some JSON content here that represents the OrderRequest
                .andExpect(status().isOk());

        verify(orderService, times(1)).saveNewOrderRequest(any(OrderRequest.class)); // Verificiramo da je metoda pozvana točno jednom
    }

    @Test
    public void updateOrderRequestTest() throws Exception {
        OrderRequest request = new OrderRequest();
        doNothing().when(orderService).updateOrderRequest(any(OrderRequest.class), anyInt()); // Definiramo očekivano ponašanje

        // Simuliramo PUT zahtjev, provjeravamo status odgovora
        mockMvc.perform(put("/order/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}")) // You should use some JSON content here that represents the OrderRequest
                .andExpect(status().isOk());

        verify(orderService, times(1)).updateOrderRequest(any(OrderRequest.class), anyInt()); // Verificiramo da je metoda pozvana točno jednom
    }

    @Test
    public void deleteOrderRequestTest() throws Exception {
        doNothing().when(orderService).deleteOrderRequest(anyInt());// Definiramo očekivano ponašanje

        // Simuliramo DELETE zahtjev, provjeravamo status odgovora
        mockMvc.perform(delete("/order/1"))
                .andExpect(status().isOk());

        verify(orderService, times(1)).deleteOrderRequest(anyInt()); // Verificiramo da je metoda pozvana točno jednom
    }
}

