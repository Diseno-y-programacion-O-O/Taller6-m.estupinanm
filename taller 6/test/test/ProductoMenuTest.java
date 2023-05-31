package test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class ProductoMenuTest {

    @Test
    public void testCalcularCaloriasAjustadas() {
        
        ProductoMenu producto = new ProductoMenu("Hamburguesa", 1000, 500);
        assertEquals(500, producto.calcularCaloriasAjustadas());
        Ingrediente ingrediente1 = new Ingrediente("Queso", 200, 50);
        Ingrediente ingrediente2 = new Ingrediente("Tomate", 50, 10);
        producto.agregarIngrediente(ingrediente1);
        producto.agregarIngrediente(ingrediente2);
        assertEquals(560, producto.calcularCaloriasAjustadas());
    }

    @Test
    public void testCalcularPrecioAjustado() {
       
        ProductoMenu producto = new ProductoMenu("Hamburguesa", 1000, 500);
        assertEquals(1000, producto.calcularPrecioAjustado());
        Ingrediente ingrediente1 = new Ingrediente("Queso", 200, 50);
        Ingrediente ingrediente2 = new Ingrediente("Tomate", 50, 10);
        producto.agregarIngrediente(ingrediente1);
        producto.agregarIngrediente(ingrediente2);
        assertEquals(1200, producto.calcularPrecioAjustado());
    }
}
