package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ComboTest {

    private Combo combo;

    @Before
    public void setUp() {
        combo = new Combo("Combo 1");
    }

    @Test
    public void testAgregarProducto() {
        Producto producto1 = new Producto("Producto 1", 10);
        Producto producto2 = new Producto("Producto 2", 20);

        combo.agregarProducto(producto1);
        combo.agregarProducto(producto2);

        assertEquals(2, combo.getNumeroProductos());
        assertTrue(combo.contieneProducto(producto1));
        assertTrue(combo.contieneProducto(producto2));
    }

    @Test
    public void testEliminarProducto() {
        Producto producto1 = new Producto("Producto 1", 10);
        Producto producto2 = new Producto("Producto 2", 20);

        combo.agregarProducto(producto1);
        combo.agregarProducto(producto2);

        combo.eliminarProducto(producto1);

        assertEquals(1, combo.getNumeroProductos());
        assertFalse(combo.contieneProducto(producto1));
        assertTrue(combo.contieneProducto(producto2));
    }

    @Test
    public void testCalcularPrecioTotal() {
        Producto producto1 = new Producto("Producto 1", 10);
        Producto producto2 = new Producto("Producto 2", 20);

        combo.agregarProducto(producto1);
        combo.agregarProducto(producto2);

        double precioTotal = combo.calcularPrecioTotal();

        assertEquals(30.0, precioTotal, 0.001);
    }

    @Test
    public void testNumeroProductos() {
        Producto producto1 = new Producto("Producto 1", 10);
        Producto producto2 = new Producto("Producto 2", 20);

        combo.agregarProducto(producto1);
        combo.agregarProducto(producto2);

        assertEquals(2, combo.getNumeroProductos());
    }

    @Test
    public void testContieneProducto() {
        Producto producto1 = new Producto("Producto 1", 10);
        Producto producto2 = new Producto("Producto 2", 20);
        Producto producto3 = new Producto("Producto 3", 30);

        combo.agregarProducto(producto1);
        combo.agregarProducto(producto2);

        assertTrue(combo.contieneProducto(producto1));
        assertTrue(combo.contieneProducto(producto2));
        assertFalse(combo.contieneProducto(producto3));
    }
}
