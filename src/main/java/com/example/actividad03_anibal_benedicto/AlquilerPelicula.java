package com.example.actividad03_anibal_benedicto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class AlquilerPelicula {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sakila_PU");
        EntityManager em = emf.createEntityManager();
        Scanner scanner = new Scanner(System.in);

        // Pide al usuario los datos necesarios
        System.out.print("Introduce el ID del empleado que está realizando el alquiler: ");
        int empleadoId = scanner.nextInt();
        System.out.print("Introduce el ID de la película que se quiere alquilar: ");
        int peliculaId = scanner.nextInt();
        System.out.print("Introduce el ID del cliente que está haciendo el alquiler: ");
        int clienteId = scanner.nextInt();

        // Realiza las tareas requeridas
        realizarAlquiler(em, empleadoId, peliculaId, clienteId);
        em.close();
        emf.close();
    }
    private static void realizarAlquiler(EntityManager em, int empleadoId, int peliculaId, int clienteId) {
        // Comprobar que el empleado existe
        Staff empleado = em.find(Staff.class, empleadoId);
        if (empleado == null) {
            System.out.println("Error: El empleado con ID " + empleadoId + " no existe.");
            return;
        }

        // Obtener la tienda asociada al empleado
        Store tienda = empleado.getStore();

        // Comprobar que la película existe en el inventario de la tienda
        TypedQuery<Inventory> inventoryQuery = em.createQuery(
                        "SELECT i FROM Inventory i WHERE i.film.filmId = :peliculaId AND i.store = :tienda", Inventory.class)
                .setParameter("peliculaId", peliculaId)
                .setParameter("tienda", tienda);
        List<Inventory> inventarios = inventoryQuery.getResultList();
        if (inventarios.isEmpty()) {
            System.out.println("Error: La película con ID " + peliculaId + " no está disponible en la tienda.");
            return;
        }
        //Comprobar que haya copias disponibles
        long copiasDisponibles = inventarios.stream().filter(inventario -> inventario.getRentals().isEmpty()).count();
        if (copiasDisponibles == 0) {
            System.out.println("Error: No hay copias disponibles de la película con ID " + peliculaId + ".");
            return;
        }
        // Comprobar que el cliente existe
        Customer cliente = em.find(Customer.class, clienteId);
        if (cliente == null) {
            System.out.println("Error: El cliente con ID " + clienteId + " no existe.");
            return;
        }
        // Crear un nuevo alquiler (rental)
        Rental nuevoAlquiler = new Rental();
        nuevoAlquiler.setRental_date(LocalDateTime.now());
        nuevoAlquiler.setInventory(inventarios.get(0)); // Asumimos que seleccionamos la primera copia disponible
        nuevoAlquiler.setCustomer(cliente);
        nuevoAlquiler.setStaff(empleado);
        em.getTransaction().begin();
        em.persist(nuevoAlquiler);
        em.getTransaction().commit();
        System.out.println("Alquiler realizado con éxito. Detalles del alquiler:");
        System.out.println("ID del alquiler: " + nuevoAlquiler.getRental_id());
        System.out.println("Fecha del alquiler: " + nuevoAlquiler.getRental_date());
        System.out.println("Película alquilada: " + nuevoAlquiler.getInventory().getFilm().getTitle());
    }
}


