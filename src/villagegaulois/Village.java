package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;

	public Village(String nom, int nbVillageoisMaximum) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	class Marche {
		private Etal[] etals;
		
		public Marche(int  nbEtals) {
			this.etals = new Etal[nbEtals];
		}
		
		public void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			if (indiceEtal>0) {
				this.etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
			}
			else {
				System.out.println("Erreur sur l'indice du tableau");
			}
		}
		
		public int trouverEtalLibre() {
			int i=-1;
			while (this.etals[i]!=null && i<etals.length) {
				i++;
			}
			return i;
		}
		
		public Etal[] trouverEtals(String produit) {
			int nbEtals=0;
			int i=0;
			while (i<etals.length) {
				if (this.etals[i].contientProduit(produit)) {
					nbEtals++;
				}
				i++;
			}
			Etal[] nvEtals = new Etal[nbEtals];
			
			while (i<etals.length) {
				if (this.etals[i].contientProduit(produit)) {
					nvEtals[nbEtals]=etals[i];
					nbEtals++;
				}
				i++;
			}
			
			return nvEtals;
		}
		
		public Etal trouverVendeur(Gaulois gaulois) {
			Etal trouver=null;
			for (int i=0;i<etals.length;i++) {
				if (etals[i].isEtalOccupe()) {
					if (etals[i].getVendeur()==gaulois) {
						trouver=etals[i];
					}
				}
			}
			return trouver;
		}
		
		public StringBuilder afficherMarche() {
			int nbEtalsVides=0;
			int i;
			StringBuilder chaine = new StringBuilder();
			for (i=0;i<etals.length;i++) {
				if (etals[i].isEtalOccupe()) {
					chaine.append(etals[i].afficherEtal());
				}
				else {
					nbEtalsVides++;
				}
				
			}
			if (nbEtalsVides!=0) {
				chaine.append("Il reste " +nbEtalsVides + " étals non utilisés dans le marché.\n");
			}
			return chaine;
		}
	}
	
	
}