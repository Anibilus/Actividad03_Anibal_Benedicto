package com.example.actividad03_anibal_benedicto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class InformePelicula {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("tu_unidad_de_persistencia");
        EntityManager em = emf.createEntityManager();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Introduce el ID de la película (0 para salir): ");
            int peliculaId = scanner.nextInt();

            if (peliculaId == 0) {
                break;
            }
            mostrarInformePelicula(em, peliculaId);
        }
        em.close();
        emf.close();
    }

    private static void mostrarInformePelicula(EntityManager em, int peliculaId) {

        // Obtener la película por su ID
        Film pelicula = em.find(Film.class, peliculaId);
        if (pelicula != null) {
            System.out.println("\nDatos de la película:");
            System.out.println("Título: " + pelicula.getTitle());
            System.out.println("Descripción: " + pelicula.getDescription());
            // Mostrar la categoría
            Category categoria = pelicula.getCategory();
            if (categoria != null) {
                System.out.println("Categoría: " + categoria.getName());
            }

            // Mostrar idiomas
            Language idiomaOriginal = pelicula.getOriginalLanguage();
            Language idiomaPelicula = pelicula.getLanguage();
            if (idiomaOriginal != null && idiomaPelicula != null) {
                System.out.println("Idioma Original: " + idiomaOriginal.getName());
                System.out.println("Idioma de la Película: " + idiomaPelicula.getName());
            }

            // Mostrar actores
            List<Actor> actores = pelicula.getActors();
            if (!actores.isEmpty()) {
                System.out.println("\nActores que participan en la película:");
                for (Actor actor : actores) {
                    System.out.println(" - " + actor.getFirstName() + " " + actor.getLastName());
                }
            }

            // Mostrar copias disponibles en el inventario
            List<Inventory> inventarios = pelicula.getInventories();
            if (!inventarios.isEmpty()) {
                System.out.println("\nCopias disponibles en el inventario:");
                for (Inventory inventario : inventarios) {
                    Store tienda = inventario.getStore();
                    Address direccion = tienda.getAddress();
                    System.out.println(" - Tienda: " + tienda.getStoreId());
                    System.out.println("   Dirección: " + direccion.getAddress());
                    System.out.println("   Ciudad: " + direccion.getCity().getCity());
                    System.out.println("   Provincia: " + direccion.getCountry().getCountry());
                }
            } else {
                System.out.println("\nNo hay copias disponibles en el inventario.");
            }
        } else {
            System.out.println("No se encontró ninguna película con el ID proporcionado.");
        }
    }
}
