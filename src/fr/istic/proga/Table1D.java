package fr.istic.proga;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Table1D implements TableMots {
    /**
     * Le tableau de mots
     */
    private final String[] tab;

    public Table1D() {
        tab = new String[26];
    }

    public static void main(String[] args) {
        Table1D t1d = new Table1D();

        System.out.println("t1d.present(\"toto\") == " + t1d.present("toto"));
        System.out.println("Ajout de toto : " + t1d.enregistrer("toto"));
        System.out.println("t1d.present(\"toto\") == " + t1d.present("toto"));
        System.out.println("Ajout de titi : " + t1d.enregistrer("titi"));
        System.out.println("t1d.present(\"toto\") == " + t1d.present("toto"));
        System.out.println("t1d.present(\"titi\") == " + t1d.present("titi"));
        System.out.println("Ajout de \"\" : " + t1d.enregistrer(""));
        System.out.println("t1d.present(\"\") == " + t1d.present(""));
        System.out.println("Ajout de évoé : " + t1d.enregistrer("évoé"));
        System.out.println("t1d.present(\"évoé\") == " + t1d.present("évoé"));

        System.out.println("Ajout de stuv : " + t1d.enregistrer("stuv")) ;
        System.out.println("Ajout de Rstu : " + t1d.enregistrer("Rstu")) ;
        System.out.println("Ajout de abcd : " + t1d.enregistrer("abcd") + "\n") ;

        System.out.println("Itérateur interne : ") ;
        java.util.Iterator<String> iter = t1d.iterateurExterne() ;
        while(iter.hasNext())
        {
            System.out.print(iter.next() + ";") ;
        }
    }

    @Override
    public boolean enregistrer(String mot) {
        // Si mot est null, ou vide, ou s’il commence par un caractère non
        // alphabétique (ou même un caractère accentué), la méthode
        // renvoie false, et la table reste inchangée
        if (!motCorrect(mot)) {
            return false;
        }

        // Si mot est non vide et commence par une lettre non accentuée (A à Z ainsi que a à z)
        int indicePremiereLettre = indiceLettreInitiale(mot);
        tab[indicePremiereLettre] = mot;

        return true;
    }

    @Override
    public boolean present(String mot) {
        return motCorrect(mot) && Arrays.asList(tab).contains(mot);
    }

    public Iterator<String> iterateurInterne() {
//        return Arrays.stream(tab)
//                .filter(Objects::nonNull) // Prendre les valeurs no null
//                .collect(Collectors.toList()) // convertir en list
//                .iterator(); // retourner l'itérateur sur la liste obtenue

        List<String> list = new ArrayList<>();
        for (String mot : tab) {
            if (mot != null) {
                list.add(mot);
            }
        }

        return list.iterator();
    }

    public Iterateur1D iterateurExterne() {
        return new Iterateur1D(tab);
    }

    // Les deux peuvent être déclarées en static parce qu'elles travaillent directement sur les propriétés de la classe.
    // L'intégrité des variables d'instances n'est pas affectée.

    public boolean motCorrect(String mot) {
        return mot != null && !mot.isEmpty() && String.valueOf(mot.charAt(0)).toUpperCase().matches("[A-Z]");
    }

    public int indiceLettreInitiale(String mot) {
        if (!motCorrect(mot)) return -1;
        return (Character.getNumericValue(mot.toUpperCase().charAt(0)))
                - Character.getNumericValue('A');
    }

    static void ecrireTable(Table1D tab, Connection conn, String tableName) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT  INTO " + tableName + " VALUE(?)");
        StringBuilder builder = new StringBuilder();
        java.util.Iterator<String> iter = tab.iterateurExterne() ;
        while(iter.hasNext())
        {
            builder.append(iter.next() + ";") ;
        }
        stmt.setString(1, builder.toString());
        stmt.execute();
    }

    private class Iterateur1D implements Iterator<String> {
        private final int size;
        private String[] tab;
        private int cursor = 0;

        public Iterateur1D(String[] tab) {
            this.tab = tab;
            this.size = tab.length;
        }

        /**
         * @return
         */
        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public String next() {
            assert hasNext();

            String next = null;
            int i = this.cursor;

            if (i < tab.length && tab[i] != null) {
                next = tab[i];
                i++;
            }

            while (i < tab.length && tab[i] == null) {
                i++;
            }

            this.cursor = i;
            return next;
        }
    }
}
