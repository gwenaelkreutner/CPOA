package testunitairemetier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

import metier.Tva;

public class MTvaTest {


	@Test
	public void testSimple() {
		try {
			Tva t = new Tva(1, "libelle_tva", 4.0);
		} catch (IllegalArgumentException e) {
			fail("Devrait renvoyer une erreur si problème de constructeur");
		}
		;
	}

	// id_tva

	@Test
	public void testGetId_tva() {
		Tva t = new Tva(1, "libelle_tva", 4.0);
		assertEquals(1, t.getId_tva());
	}

	@Test
	public void testSetId_tvaFAIL() {
		try {
			Tva t = new Tva(-1, "TvaTest", 4.0);

		} catch (IllegalArgumentException e) {
			fail("Devrait renvoyer une erreur car id_tva doit etre supérieur a 0");
		}
	}

	@Test
	public void testSetI_tvaOk() {
		try {
			Tva t = new Tva(1, "TvaTest", 4.0);

		} catch (IllegalArgumentException e) {
			fail("Renvoie pas d'erreur car id_client > 0");
		}
	}

	// libelle_tva

	@Test
	public void testGetLibelle_tva() {
		Tva t = new Tva(1, "libelle_tva", 4.0);
		assertEquals("libelle_tva", t.getLibelle_tva());
	}

	@Test
	public void testSetLibelle_tvaFAIL() {
		try {
			Tva t = new Tva(1, "", 4.0);

		} catch (IllegalArgumentException e) {
			fail("Envoie une erreur car le libelle doit pas etre vide");
		}
	}

	@Test
	public void testSetLibelle_tvaOk() {
		try {
			Tva t = new Tva(1, "TvaTest", 4.0);

		} catch (IllegalArgumentException e) {
			fail("Aucune erreur car tout est bon");
		}
	}

	// taux_tva


	@Test
	public void testSetTaux_tvaFAIL() {
		try {
			Tva t = new Tva(1, "TvaTest", -4.0);

		} catch (IllegalArgumentException e) {
			fail("Erreur car le taux ne doit pas etre inferieur a 0");
		}
	}

	@Test
	public void testSetTaux_tvaOK() {
		try {
			Tva t = new Tva(1, "TvaTest", 4.0);

		} catch (IllegalArgumentException e) {
			fail("Renvoie aucune erreur car le taux est bien positif");
		}
	}

}