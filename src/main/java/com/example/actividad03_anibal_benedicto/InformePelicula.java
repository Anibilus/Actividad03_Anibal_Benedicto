package com.example.actividad03_anibal_benedicto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class InformePelicula {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sakila_PU");
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
            System.out.println("ID de la película: " + pelicula.getFilmId());
            System.out.println("Título: " + pelicula.getTitle());
            System.out.println("Descripción: " + pelicula.getDescription());
            System.out.println("Año de lanzamiento: " + pelicula.getReleaseYear());
            System.out.println("Rating: " + pelicula.getRating());
            System.out.println("Duración: " + pelicula.getLength() + " minutos");
            System.out.println("Características especiales: " + pelicula.getSpecialFeatures());
            System.out.println("Última actualización: " + pelicula.getLastUpdate());
            // Mostrar la categoría
            Category categoria = pelicula.getCategory();
            if (categoria != null) {
                System.out.println("Categoría: " + categoria.getName());
            }

            // Mostrar idiomas
            List<Language> idiomas = pelicula.getLanguages();
            if (!idiomas.isEmpty()) {
                System.out.println("idiomas de la película:");
                for (Language idioma : idiomas) {
                    System.out.println(" - " + idioma.getName());
                }
            }
            Language idiomaOriginal = pelicula.getOriginalLanguage();
            if (idiomaOriginal != null) {
                System.out.println("idioma original de la película: " + idiomaOriginal.getName());
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
