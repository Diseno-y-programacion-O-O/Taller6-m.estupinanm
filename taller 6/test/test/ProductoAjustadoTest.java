package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class ProductoAjustadoTest {

    private ProductoMenu productoBase;
    private ProductoAjustado productoAjustado;

    @Before
    public void setUp() {
        productoBase = new ProductoMenu("Hamburguesa", 1000, 500);
        productoAjustado = new ProductoAjustado(productoBase);
    }

    @Test
    public void testCalcularCaloriasAjustadasSinIngredientes() {
        int caloriasAjustadas = productoAjustado.calcularCaloriasAjustadas();
        assertEquals(500, caloriasAjustadas);
    }

    @Test
    public void testCalcularCaloriasAjustadasConIngredientes() {
        Ingrediente ingrediente1 = new Ingrediente("Queso", 200, 50);
        Ingrediente ingrediente2 = new Ingrediente("Tomate", 50, 10);
        productoAjustado.agregarIngrediente(ingrediente1);
        productoAjustado.agregarIngrediente(ingrediente2);

        int caloriasAjustadas = productoAjustado.calcularCaloriasAjustadas();
        assertEquals(560, caloriasAjustadas);
    }

    @Test
    public void testCalcularPrecioAjustadoSinIngredientes() {
        double precioAjustado = productoAjustado.calcularPrecioAjustado();
        assertEquals(1000, precioAjustado, 0.001);
    }

    @Test
    public void testCalcularPrecioAjustadoConIngredientes() {
        Ingrediente ingrediente1 = new Ingrediente("Queso", 200, 50);
        Ingrediente ingrediente2 = new Ingrediente("Tomate", 50, 10);
        productoAjustado.agregarIngrediente(ingrediente1);
        productoAjustado.agregarIngrediente(ingrediente2);

        double precioAjustado = productoAjustado.calcularPrecioAjustado();
        assertEquals(1200, precioAjustado, 0.001);
    }

    @Test
    public void testGetPrecio() {
        Ingrediente ingrediente1 = new Ingrediente("Queso", 200, 50);
        Ingrediente ingrediente2 = new Ingrediente("Tomate", 50, 10);
        productoAjustado.agregarIngrediente(ingrediente1);
        productoAjustado.agregarIngrediente(ingrediente2);

        double precio = productoAjustado.getPrecio();
        assertEquals(1200, precio, 0.001);
    }

    @Test
    public void testAgregarIngrediente() {
        Ingrediente ingrediente = new Ingrediente("Queso", 200, 50);
        productoAjustado.agregarIngrediente(ingrediente);

        assertTrue(productoAjustado.contieneIngrediente(ingrediente));
    }

}
