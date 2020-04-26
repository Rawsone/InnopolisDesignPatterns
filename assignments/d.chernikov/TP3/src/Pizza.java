import java.util.ArrayList;

/**
 * = Asciidoclet
 * <p>
 * Sample comments that include `source code` by mailto:jbruel@gmail.com[JMB].
 * <p>
 * [source,java]
 * --
 * include::src/java/Pizzeria/src/Pizza.java[lines=14..34]
 * --
 *
 * @author bruel
 */
public abstract class Pizza {
    String nom;
    String pate;
    String sauce;
    ArrayList<String> garnitures = new ArrayList<String>();

    void preparer() {
        System.out.println("Préparation de " + nom);
        System.out.println("Étalage de la pâte...");
        System.out.println("Ajout de la sauce...");
        System.out.println("Ajout des garnitures: ");

        for (String garniture : garnitures) {
            System.out.println(" " + garniture);
        }
    }

    void cuire() {
        System.out.println("Cuisson 25 minutes à 180°");
    }

    void couper() {
        System.out.println("Découpage en parts triangulaires");
    }

    void emballer() {
        System.out.println("Emballage dans une boîte officielle");
    }

    public String getNom() {
        return nom;
    }
}