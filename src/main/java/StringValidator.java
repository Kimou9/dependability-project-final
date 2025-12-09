import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe `StringValidator` : simule la logique de validation de chaînes de caractères.
 */
public class StringValidator {

    // Accepte toutes les lettres Unicode (y compris les lettres accentuées) et les espaces
    private static final Pattern VALID_NAME_PATTERN = Pattern.compile("^[\\p{L}\\s]+$");

    // 1. Fonction critique pour la sûreté (validation de nom)
    public static boolean estNomValide(String nom) {
        if (nom == null || nom.trim().isEmpty()) {
            return false;
        }
        Matcher matcher = VALID_NAME_PATTERN.matcher(nom.trim());
        return matcher.matches();
    }

    // 2. Fonction critique pour la robustesse (gérer le null)
    public static String defaultIfNull(String str, String defaultStr) {
        return str == null ? defaultStr : str;
    }

    // 3. Fonction pour la fiabilité (calcul de longueur)
    public static int calculerLongueur(String str, int limiteMax) {
        if (str == null) {
            return 0;
        }
        int length = str.length();
        if (length > limiteMax) {
            throw new IllegalArgumentException("La chaîne dépasse la limite de taille maximale.");
        }
        return length;
    }

    // --- Le point d'entrée pour Docker ---
    public static void main(String[] args) {
        System.out.println("Application StringValidator démarrée.");
        try {
            boolean valide = estNomValide("Jean Dupont");
            System.out.println("Nom 'Jean Dupont' valide : " + valide);
            String resultat = defaultIfNull(null, "Non défini");
            System.out.println("Test de nullité : " + resultat);
            calculerLongueur("Une très longue chaîne de caractères", 5);
        } catch (IllegalArgumentException e) {
            System.err.println("ERREUR GÉRÉE (robustesse testée) : " + e.getMessage());
        }
    }
}
