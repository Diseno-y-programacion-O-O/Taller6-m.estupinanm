package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PedidoTest {

    private Pedido pedido;
    private Producto producto1;
    private Producto producto2;

    @Before
    public void setUp() {
        pedido = new Pedido("1", "Cliente", "Dirección");
        producto1 = new Producto("Producto 1", 10);
        producto2 = new Producto("Producto 2", 20);
    }

    @Test
    public void testAgregarProducto() throws ValorTotalExcedido {
        pedido.agregarProducto(producto1);
        pedido.agregarProducto(producto2);

        assertEquals(2, pedido.getNumeroProductos());
        assertTrue(pedido.contieneProducto(producto1));
        assertTrue(pedido.contieneProducto(producto2));
    }

    @Test
    public void testEliminarProducto() throws ValorTotalExcedido {
        pedido.agregarProducto(producto1);
        pedido.agregarProducto(producto2);

        pedido.eliminarProducto(producto1);

        assertEquals(1, pedido.getNumeroProductos());
        assertFalse(pedido.contieneProducto(producto1));
        assertTrue(pedido.contieneProducto(producto2));
    }

    @Test
    public void testCalcularTotal() throws ValorTotalExcedido {
        pedido.agregarProducto(producto1);
        pedido.agregarProducto(producto2);

        double total = pedido.calcularTotal();

        assertEquals(30.0, total, 0.001);
    }

    @Test(expected = ValorTotalExcedido.class)
    public void testAgregarProductoExcedeLimite() throws ValorTotalExcedido {
        pedido.setLimiteTotal(30);
        pedido.agregarProducto(producto1);
        pedido.agregarProducto(producto2);
    }
}
