package fr.istic.proga;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpecifMatriceCarreeTest {
    private SpecifMatriceCarree matrice;

    @org.junit.jupiter.api.BeforeAll
    void setUp() {
        matrice = new SpecifMatriceCarreeImpl();
    }

    @org.junit.jupiter.api.Test
    void setCase() {
        int valeur = 15;
        matrice.setCase(0, 0, valeur);
        assertEquals(valeur, matrice.getCase(0, 0));
//        assertEquals(valeur, matrice.getTotalLigne(0));
    }

    @org.junit.jupiter.api.Test
    void getTotalLigne() {
        matrice.setCase(0, 0, 6);
        matrice.setCase(0, 1, 2);
        matrice.setCase(0, 2, 7);

        assertEquals(15, matrice.getTotalLigne(0));
    }
}
