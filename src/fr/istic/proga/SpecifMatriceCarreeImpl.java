package fr.istic.proga;

public class SpecifMatriceCarreeImpl implements SpecifMatriceCarree {
    @Override
    public int getDimension() {
        return 0;
    }

    @Override
    public boolean coordCorrecte(int c) {
        return false;
    }

    @Override
    public int getCase(int ligne, int colonne) {
        return 0;
    }

    @Override
    public void setCase(int ligne, int colonne, int valeur) {
        coordCorrecte(ligne);
        coordCorrecte(colonne);
        assert valeur > 0;
    }

    @Override
    public int getTotalLigne(int ligne) {
        return 0;
    }

    @Override
    public int getTotalColonne(int colonne) {
        return 0;
    }
}
