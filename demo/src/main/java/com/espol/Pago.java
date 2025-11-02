package com.espol;

public class Pago implements IPagable {
    private int idPago;
    private float monto;
    private String metodo;

    public Pago(int idPago, float monto, String metodo) {
        this.idPago = idPago;
        this.monto = monto;
        this.metodo = metodo;
    }

    @Override
    public boolean procesarPago() {
        // Simulación de validación de pago
        if (monto > 0 && metodo != null && !metodo.isEmpty()) {
            System.out.println("Procesando pago de $" + monto + " con " + metodo);
            System.out.println("Pago exitoso.");
            return true;
        } else {
            System.out.println("Error en el pago: monto o método inválido.");
            return false;
        }
    }

}
