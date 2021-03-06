package fr.istic.proga;

import java.util.*;

public class Table2D implements TableMots {
    /**
     * Le tableau de mots
     */
    private final String[][] tab;
    private final int size;
    private final Map<Integer, Integer> positions = new HashMap<>();

    public Table2D(int size) {
        tab = new String[26][size];
        this.size = size;
    }

    public static void main(String[] args) {
        Table2D t2d = new Table2D(2) ;
        System.out.println("t2d.present(\"toto\") == " + t2d.present("toto")) ;
        System.out.println("Ajout de toto : " + t2d.enregistrer("toto")) ;
        System.out.println("t2d.present(\"toto\") == " + t2d.present("toto")) ;
        System.out.println("Ajout de titi : " + t2d.enregistrer("titi")) ;
        System.out.println("t2d.present(\"toto\") == " + t2d.present("toto")) ;
        System.out.println("t2d.present(\"titi\") == " + t2d.present("titi")) ;
        System.out.println("Ajout de tutu : " + t2d.enregistrer("tutu")) ;
        System.out.println("t2d.present(\"toto\") == " + t2d.present("toto")) ;
        System.out.println("t2d.present(\"titi\") == " + t2d.present("titi")) ;
        System.out.println("Ajout de \"\" : " + t2d.enregistrer("")) ;
        System.out.println("t2d.present(\"\") == " + t2d.present("")) ;
        System.out.println("Ajout de évoé : " + t2d.enregistrer("évoé")) ;
        System.out.println("t2d.present(\"évoé\") == " + t2d.present("évoé")) ;
        System.out.println("Ajout de stuv : " + t2d.enregistrer("stuv")) ;
        System.out.println("Ajout de Rstu : " + t2d.enregistrer("Rstu")) ;
        System.out.println("Ajout de abcd : " + t2d.enregistrer("abcd")) ;
        System.out.println("Itérateur interne : ") ;
        Iterator<String> iter = t2d.iterateurInterne() ;
        while(iter.hasNext())
        {
            System.out.print(iter.next() + ";") ;
        }
        System.out.println("\n") ;
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
        int position = positions.get(indicePremiereLettre) != null ? positions.get(indicePremiereLettre) : 0;
        if (position >= size) {
            return false;
        }
        tab[indicePremiereLettre][position] = mot;
        positions.put(indicePremiereLettre, position + 1);

        return true;
    }

    @Override
    public boolean present(String mot) {
        int indiceLettreInitiale = indiceLettreInitiale(mot);
        return motCorrect(mot) && Arrays.asList(tab[indiceLettreInitiale]).contains(mot);
    }

    public Iterator<String> iterateurInterne() {
//        return Arrays.stream(tab)
//                .filter(Objects::nonNull) // Prendre les valeurs no null
//                .collect(Collectors.toList()) // convertir en list
//                .iterator(); // retourner l'itérateur sur la liste obtenue

        List<String> list = new ArrayList<>();
        for (String[] ligneMots : tab) {
            for (String mot :
                    ligneMots) {
                if (mot != null) {
                    list.add(mot);
                }
            }
        }

        return list.iterator();
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
}
