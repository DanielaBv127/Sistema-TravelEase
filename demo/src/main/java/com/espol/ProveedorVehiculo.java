// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package com.espol;

import com.espol.estados.VehiculoReservado;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProveedorVehiculo extends Proveedor implements IProveedor {
   private List<Vehiculo> vehiculosOfertados = new ArrayList();

   public ProveedorVehiculo(int var1, String var2) {
      super(var1, var2, "Vehiculo");
   }

   public void agregarVehiculo(Vehiculo var1) {
      this.vehiculosOfertados.add(var1);
   }

   public void obtenerDisponibilidad() {
      System.out.println("--- Disponibilidad de " + this.getNombre() + " ---");
      Iterator var1 = this.vehiculosOfertados.iterator();

      while(var1.hasNext()) {
         Vehiculo var2 = (Vehiculo)var1.next();
         var2.verificarDisponibilidad();
      }

   }

   public void confirmarReserva() {
      System.out.println("Proveedor " + this.getNombre() + " confirmando todas las reservas pendientes.");
      Iterator var1 = this.vehiculosOfertados.iterator();

      while(var1.hasNext()) {
         Vehiculo var2 = (Vehiculo)var1.next();
         if (var2.getEstado() instanceof VehiculoReservado) {
            var2.confirmarReserva();
         }
      }

   }
}

