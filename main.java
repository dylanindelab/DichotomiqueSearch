
import java.util.Random;

public class Main {
	
	public static void afficher(int[] t) {
		for(int i : t) {
			System.out.println(i);
		}
	}
	
	public static int[] creerTableau(int tailleTableau) {
		int[] t = new int[tailleTableau];
		for(int i = 0; i < t.length; i++) {
			t[i] = i+1;
		}
		
		Random r = new Random();
		
		for(int i = 0; i < t.length; i++) {
			int random = r.nextInt(t.length);
			int temp = t[i];
			t[i] = t[random];
			t[random] = temp;
	}
		return t;
	}
	
	public static long rechercheSequentielle(int[] t, int random) {
		int indice = 0;
		//Début que la recherche
		long debut = System.nanoTime();
		while( indice < t.length && t[indice] != random) {
			indice++;
		}
		
		long fin = System.nanoTime();
		// Fin de la recherhe
		
		return fin-debut;
	}
	
	public static void trier(int[] t) {
		for(int i = 0; i < t.length; i++) {
			int indice = i;
			int minimal = t[i];
			for(int j = i; j < t.length; j++) {
				if(t[j] < minimal) {
					minimal = t[j];
					indice = j;
				} 
			}
			int temp = t[indice];
			t[indice] = t[i];
			t[i] = temp;
		}
	}
	
	
	public static long rechercheDichotomique(int[] t, int random) {

		int indiceMinimal = 0;
		int indiceMaximal = t.length-1;
		int moyenne = 0; 		
		long debut = System.nanoTime();
		
		while(t[moyenne = (indiceMaximal-indiceMinimal) / 2+indiceMinimal] != random) {
			if(t[moyenne] < random) {
				indiceMinimal = moyenne+1;
			} else {
				indiceMaximal = moyenne;
			}
		}
		long fin = System.nanoTime();
		return fin-debut;
	}
	public static void main(String args[]) {
		int[] t = creerTableau(10000);
		
		int nombreDeTest = 100;
		
		long resultat = 0;

		for(int i=0; i < nombreDeTest; i++) {
			int random = (int) (Math.random() * 10000);
			resultat += rechercheSequentielle(t, random);
		}
		
		System.out.println("Les valeurs ont été en trouvées en "+resultat/nombreDeTest+" nanosecondes en moyenne grâce a une recherche sequentielle");
		
		Main.trier(t);
		
		resultat = 0;

		for(int i = 0; i < nombreDeTest; i++) {
			int random = (int) (Math.random() * 10000);
			resultat+= rechercheDichotomique(t, random);
		}
		
		System.out.println("Les valeurs ont été en trouvées en "+resultat/nombreDeTest+" nanosecondes  en moyenne grâce a une recherche dichotomique");
	}
}