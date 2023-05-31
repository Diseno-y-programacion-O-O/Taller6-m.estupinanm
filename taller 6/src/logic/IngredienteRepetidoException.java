package logic;

public class IngredienteRepetidoException extends HamburguesaException {
    private String nombreIngrediente;

    public IngredienteRepetidoException(String nombreIngrediente) {
        super("Ingrediente repetido: " + nombreIngrediente);
        this.nombreIngrediente = nombreIngrediente;
    }

    public String getNombreIngrediente() {
        return nombreIngrediente;
    }
}


