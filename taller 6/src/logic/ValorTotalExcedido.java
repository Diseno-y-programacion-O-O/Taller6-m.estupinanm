package logic;

public class ValorTotalExcedido extends Exception {
    private double valorTotalExcedido;

    public ValorTotalExcedido(double valorTotalExcedido) {
        this.valorTotalExcedido = valorTotalExcedido;
    }

    @Override
    public String getMessage() {
        return "El valor total del pedido excede el límite permitido. Valor total excedido: $" + valorTotalExcedido;
    }
}
