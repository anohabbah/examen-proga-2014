package fr.istic.proga;

public interface SpecifMatriceCarree {
    // Nombre de lignes ou de colonnes du carré, entre 1 et N
    // Le nombre de cases est égal à getDimension() * getDimension()
    int getDimension();

    // Vrai si et seulement si c>=1 et c<=getDimension()
    boolean coordCorrecte(int c);

    // Valeur de la case de ligne "ligne" et de colonne "colonne"
    // Précondition 1 : coordCorrecte(ligne)
    // Précondition 2 : coordCorrecte(colonne)
    int getCase(int ligne, int colonne);

    // Modification de la case de ligne "ligne" et de colonne "colonne"
    // Précondition 1 : coordCorrecte(ligne)
    // Précondition 2 : coordCorrecte(colonne)
    // Précondition 3 : valeur > 0
    // Postcondition 1 : getCase(ligne, colonne) == valeur
    // Postcondition 2 : le total de la ligne a été mis à jour
    // Postcondition 3 : le total de la colonne a été mis à jour
    void setCase(int ligne, int colonne, int valeur);

    // Total des cases la ligne "ligne"
    // Précondition : coordCorrecte(ligne)
    int getTotalLigne(int ligne);

    // Total des cases la colonne "colonne"
    // Précondition : coordCorrecte(ligne)
    int getTotalColonne(int colonne);
}
