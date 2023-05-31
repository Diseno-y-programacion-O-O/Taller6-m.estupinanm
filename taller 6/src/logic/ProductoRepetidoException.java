package logic;

class ProductoRepetidoException extends HamburguesaException {
    private String nombreProducto;

    public ProductoRepetidoException(String nombreProducto) {
        super("El producto " + nombreProducto + " está repetido en el menú.");
        this.nombreProducto = nombreProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }
}
