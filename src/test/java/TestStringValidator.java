import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * Tests unitaires pour la classe `StringValidator`, couvrant la sûreté,
 * la robustesse et la fiabilité.
 */
public class TestStringValidator {

    // --- Tests de sûreté (estNomValide) ---

    @Test
    void testNomValide_NomSimple() {
        assertTrue(StringValidator.estNomValide("Jean Dupont"), 
            "Doit valider un nom simple.");
    }

    @Test
    void testNomValide_NomCompose() {
        assertTrue(StringValidator.estNomValide("Marie Claire"), 
            "Doit valider un nom composé avec espace.");
    }

    @Test
    void testNomInvalide_AvecChiffre() {
        assertFalse(StringValidator.estNomValide("Jean123"), 
            "Ne doit pas valider les noms avec des chiffres.");
    }

    @Test
    void testNomInvalide_AvecSymbole() {
        assertFalse(StringValidator.estNomValide("Jean@Dupont"), 
            "Ne doit pas valider les noms avec des symboles.");
    }
    
    // --- Tests de robustesse (defaultIfNull) ---

    @Test
    void testDefaultIfNull_InputEstNull() {
        assertEquals("Valeur par défaut", 
                     StringValidator.defaultIfNull(null, "Valeur par défaut"),
            "Doit retourner la valeur par défaut si l'entrée est null.");
    }

    @Test
    void testDefaultIfNull_InputEstNonNull() {
        assertEquals("Salut", 
                     StringValidator.defaultIfNull("Salut", "Par défaut"),
            "Doit retourner l'entrée si elle est non null.");
    }

    // --- Tests de fiabilité (calculerLongueur) ---

    @Test
    void testCalculerLongueur_DansLimite() {
        assertEquals(11, 
                     StringValidator.calculerLongueur("Dix lettres", 20),
            "Doit retourner la longueur correcte si dans la limite.");
    }
    
    @Test
    void testCalculerLongueur_DepasseLimite() {
        // assertThrows vérifie que la bonne exception est levée
        assertThrows(IllegalArgumentException.class, () -> {
            StringValidator.calculerLongueur("Trop long", 5);
        }, "Doit lever une `IllegalArgumentException` si la limite est dépassée.");
    }

    @Test
    void testCalculerLongueur_InputEstNull() {
        assertEquals(0, 
                     StringValidator.calculerLongueur(null, 10),
            "Doit retourner 0 si l'entrée est null (gestion d'un cas limite).");
    }
}
