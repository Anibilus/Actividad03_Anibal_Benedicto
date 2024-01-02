package com.example.actividad03_anibal_benedicto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class InformeEmpleado {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("tu_unidad_de_persistencia");
        EntityManager em = emf.createEntityManager();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Introduce el ID del empleado (0 para salir): ");
            int empleadoId = scanner.nextInt();

            if (empleadoId == 0) {
                break;
            }
            mostrarInformeEmpleado(em, empleadoId);
        }
        em.close();
        emf.close();
    }
    private static void mostrarInformeEmpleado(EntityManager em, int empleadoId) {

        // Obtener el empleado por su ID
        Staff empleado = em.find(Staff.class, empleadoId);
        if (empleado != null) {
            System.out.println("\nDatos del empleado:");
            System.out.println("Staff ID: " + empleado.getStaffId());
            System.out.println("Nombre: " + empleado.getFirstName() + " " + empleado.getLastName());
            System.out.println("Email: " + empleado.getEmail());
            System.out.println("Activo: " + empleado.isActive());
            System.out.println("Username: " + empleado.getUsername());
            System.out.println("Password: " + empleado.getPassword());
            System.out.println("Last Update: " + empleado.getLastUpdate());

            // Mostrar dirección del empleado
            Address direccionEmpleado = empleado.getAddress();
            if (direccionEmpleado != null) {
                System.out.println("\nDirección del empleado:");
                System.out.println("Dirección: " + direccionEmpleado.getAddress());
                System.out.println("Ciudad: " + direccionEmpleado.getCity().getCity());
                System.out.println("Provincia: " + direccionEmpleado.getCountry().getCountry());
            }

            // Mostrar tienda asociada
            Store tiendaAsociada = empleado.getStore();
            if (tiendaAsociada != null) {
                Address direccionTienda = tiendaAsociada.getAddress();

                System.out.println("\nTienda asociada:");
                System.out.println("ID de la tienda: " + tiendaAsociada.getStoreId());
                System.out.println("Dirección de la tienda:");
                System.out.println("Dirección: " + direccionTienda.getAddress());
                System.out.println("Ciudad: " + direccionTienda.getCity().getCity());
                System.out.println("Provincia: " + direccionTienda.getCountry().getCountry());
            }

            // Mostrar alquileres realizados por el empleado
            List<Rental> alquileres = empleado.getRentals();
            if (!alquileres.isEmpty()) {
                System.out.println("\nAlquileres realizados por el empleado:");
                for (Rental alquiler : alquileres) {
                    Film peliculaAlquilada = alquiler.getInventory().getFilm();

                    System.out.println(" - Título de la película: " + peliculaAlquilada.getTitle());
                }
            } else {
                System.out.println("\nEl empleado no ha realizado alquileres.");
            }
        } else {
            System.out.println("No se encontró ningún empleado con el ID proporcionado.");
        }
    }
}
