package com.example.actividad03_anibal_benedicto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;


public class InformeSituacionCliente {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nombre_de_tu_unidad_de_persistencia");
        EntityManager em = emf.createEntityManager();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Introduce el ID del cliente (0 para salir): ");
            int customerId = scanner.nextInt();
            if (customerId == 0) {
                break;
            }
            generarInformeCliente(em, customerId);
        }
        em.close();
        emf.close();
        scanner.close();
    }

    private static void generarInformeCliente(EntityManager em, int customerId) {
        em.getTransaction().begin();

        Customer customer = em.find(Customer.class, customerId);

        if (customer != null) {
            System.out.println("Datos del cliente:");
            System.out.println("ID: " + customer.getCustomer_id());
            System.out.println("Nombre: " + customer.getFirst_name() + " " + customer.getLast_name());
            System.out.println("Email: " + customer.getEmail());
            System.out.println("Activo: " + customer.getActive());
            System.out.println("Fecha de creación: " + customer.getCreate_date());
            System.out.println("Fecha de última modificación: " + customer.getLast_update());

            // Mostrar dirección del cliente
            Address customerAddress = em.find(Address.class, customer.getAddress_id());
            if (customerAddress != null) {
                System.out.println("\nDirección del cliente:");
                System.out.println("Dirección: " + customerAddress.getAddress());
                System.out.println("Ciudad: " + customerAddress.getCity().getCity());
                System.out.println("Provincia: " + customerAddress.getCountry().getCountry());
            }

            // Mostrar tienda asociada al cliente
            Store store = em.find(Store.class, customer.getStore_id());
            if (store != null) {
                System.out.println("\nTienda asociada:");
                System.out.println("ID de tienda: " + store.getStoreId());

                // Mostrar dirección de la tienda
                Address storeAddress = em.find(Address.class, store.getAddressId());
                if (storeAddress != null) {
                    System.out.println("Dirección de la tienda:");
                    System.out.println("Dirección: " + storeAddress.getAddress());
                    System.out.println("Ciudad: " + storeAddress.getCity().getCity());
                    System.out.println("Provincia: " + storeAddress.getCountry().getCountry());
                }
            }

            // Mostrar alquileres realizados por el cliente
            TypedQuery<Rental> rentalQuery = em.createQuery("SELECT r FROM Rental r WHERE r.customer.customerId = :customerId", Rental.class);
            rentalQuery.setParameter("customerId", customerId);
            List<Rental> rentals = rentalQuery.getResultList();

            System.out.println("\nAlquileres realizados por el cliente:");
            for (Rental rental : rentals) {
                Film film = em.find(Film.class, rental.getInventory().getFilm_id());
                System.out.println("ID de alquiler: " + rental.getRental_id());
                System.out.println("Fecha de alquiler: " + rental.getRental_date());
                System.out.println("Título de la película: " + film.getTitle());
                System.out.println("-------------------------");
            }

            // Mostrar pagos realizados por el cliente
            TypedQuery<Payment> paymentQuery = em.createQuery("SELECT p  FROM Payment p WHERE p.customer_id = :customerId", Payment.class);
            paymentQuery.setParameter("customerId", customerId);
            List<Payment> payments = paymentQuery.getResultList();

            System.out.println("\nPagos realizados por el cliente:");
            for (Payment payment : payments) {
                System.out.println("ID de pago: " + payment.getPayment_id());
                System.out.println("Fecha de pago: " + payment.getPayment_date());
                System.out.println("Monto: " + payment.getAmount());
                System.out.println("-------------------------");
            }

        } else {
            System.out.println("Cliente no encontrado.");
        }

        em.getTransaction().commit();
    }
}
