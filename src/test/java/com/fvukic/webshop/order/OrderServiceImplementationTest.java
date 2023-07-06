package com.fvukic.webshop.order;

import com.fvukic.webshop.article.ArticleRepository;
import com.fvukic.webshop.exception.EntityWithIdNotFoundException;
import com.fvukic.webshop.order.model.OrderRequest;
import com.fvukic.webshop.payment.PaymentRepository;
import com.fvukic.webshop.util.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.validation.Validator;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OrderServiceImplementationTest {

    // Klasu koju testiramo oznacavamo s @InjectMocks. Mockito ce automatski "ubrizgati" lažne ("mock") objekte u ovu klasu.
    @InjectMocks // Oznaka za klasu koju testiramo
    OrderServiceImplementation orderService;

    // Klase koje se koriste u klasi koju testiramo oznacavamo kao @Mock.
// Ovi objekti imaju isto sučelje kao prave klase, ali njihovo ponašanje se može prilagoditi za potrebe testiranja.
    @Mock // Oznaka za klase koje oponašamo
    OrderRepository orderRepository;

    @Mock
    ArticleRepository articleRepository;

    @Mock
    PaymentRepository paymentRepository;

    @Mock
    Validator validator;

    // Ova metoda se izvrsava prije svakog testa kako bi se inicijalizirale anotacije za Mockito.
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this); // Inicijalizacija Mockito anotacija
    }

    // Testira metodu saveNewOrderRequest().
    // Prvo definiramo očekivano ponašanje metode save() koja se nalazi unutar metode koju testiramo.
    // Zatim pozivamo metodu koju testiramo.
    // Na kraju provjeravamo je li metoda save() pozvana točno jednom.
    @Test
    public void saveNewOrderRequestTest() {
        // Kreiranje zahtjeva
        OrderRequest request = new OrderRequest();
        // Definiramo očekivano ponašanje metoda koje oponašamo
        when(orderRepository.save(any(Order.class))).thenAnswer(i -> i.getArguments()[0]);
        orderService.saveNewOrderRequest(request);
        // Verificiramo da je metoda pozvana točno jednom
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    // Testira metodu getAllOrders().
    // Prvo definiramo očekivano ponašanje metode findAll() koja se nalazi unutar metode koju testiramo.
    // Zatim pozivamo metodu koju testiramo.
    // Na kraju provjeravamo jesu li povratne vrijednosti ispravne.
    @Test
    public void getAllOrdersTest() {
        Order order = new Order();
        // Definiramo očekivano ponašanje metoda koje oponašamo
        when(orderRepository.findAll()).thenReturn(Collections.singletonList(order));
        List<Order> orders = orderService.getAllOrders();
        // Provjeravamo ispravnost povratnih vrijednosti
        assertEquals(1, orders.size());
        assertEquals(order, orders.get(0));
    }

    // Testira metodu deleteOrderRequest().
    // Prvo definiramo očekivano ponašanje metoda existsById() i deleteById() koje se nalaze unutar metode koju testiramo.
    // Zatim pozivamo metodu koju testiramo.
    // Na kraju provjeravamo je li metoda deleteById() pozvana točno jednom.
    @Test
    public void deleteOrderRequestTest() {
        // Definiramo očekivano ponašanje metoda koje oponašamo
        when(orderRepository.existsById(anyInt())).thenReturn(true);
        doNothing().when(orderRepository).deleteById(anyInt());
        orderService.deleteOrderRequest(1);
        // Verificiramo da je metoda pozvana točno jednom
        verify(orderRepository, times(1)).deleteById(anyInt());
    }

    @Test
    public void deleteOrderRequest_NotFoundException_Test() {
        when(orderRepository.existsById(anyInt())).thenReturn(false);
        try {
            orderService.deleteOrderRequest(1);
        } catch (EntityWithIdNotFoundException e) {
            assertEquals(ErrorResponse.ERROR_WRONG_ID, e.getMessage());
        }
        verify(orderRepository, times(0)).deleteById(anyInt());
    }

}
