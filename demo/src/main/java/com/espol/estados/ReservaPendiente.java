package com.espol.estados;

import com.espol.IReservable;
import com.espol.Reserva;
import com.espol.ServicioAdicional;

public class ReservaPendiente implements EstadoReserva {

    @Override
    public String getNombre() {
        return "PENDIENTE";
    }

    @Override
    public void confirmarPago(Reserva reserva, String metodoPago) {
        System.out.println("Procesando pago para reserva " + reserva.getIdReserva() + "...");

        // 🔥 Extract Method aplicado: Reserva maneja el pago
        if (reserva.procesarPago(metodoPago)) {

            reserva.setEstado(new ReservaConfirmada());

            reserva.confirmarTodosLosReservables();

            String mensaje = "Tu reserva " + reserva.getIdReserva()
                    + " ha sido CONFIRMADA. Total pagado: $" + reserva.getTotal();

            reserva.notificarATodos(mensaje);

        } else {
            System.out.println("Error: El pago falló. La reserva sigue PENDIENTE.");
        }
    }

    @Override
    public void cancelar(Reserva reserva) {
        System.out.println("Cancelando reserva pendiente " + reserva.getIdReserva() + "...");

        reserva.setEstado(new ReservaCancelada());

        reserva.liberarTodosLosReservables();

        String mensaje = "Reserva " + reserva.getIdReserva()
                + " ha sido CANCELADA. Iniciando proceso de reembolso...";
        reserva.notificarATodos(mensaje);

        System.out.println("Aplicando política de cancelación y calculando reembolso...");
    }

    @Override
    public void agregarServicio(Reserva reserva, ServicioAdicional servicio) {
        reserva.agregarServicioInterno(servicio);
        System.out.println(
            "Servicio '" + servicio.getDescripcion() + "' agregado a reserva pendiente."
        );
    }

    @Override
    public void agregarReservable(Reserva reserva, IReservable reservable) {
        if (reservable == null) {
            System.out.println("Error: Servicio inválido.");
            return;
        }

        if (reservable.bloquearTemporalmente()) {
            reserva.agregarReservableInterno(reservable);
            System.out.println("Servicio agregado y bloqueado temporalmente.");
        } else {
            System.out.println("Error: El servicio no está disponible.");
        }
    }
}
