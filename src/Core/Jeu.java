package Core;

import java.util.LinkedList;

/**
 * Description de la classe
 * @author llaluque jlouvet
 * @version
 */
public class Jeu {

	private Personnage persoAttaquant;
	private LinkedList<Joueur> joueurs;
	private Joueur joueurCourant;
//	private Attaque attaqueCourante;
	
	/**
	 * @param persoAttaquant
	 * @param joueurs
	 */
	public Jeu(Personnage persoAttaquant, LinkedList<Joueur> joueurs) {
		super();
		this.persoAttaquant = persoAttaquant;
		this.joueurs = joueurs;
		this.joueurCourant = joueurs.getFirst();
//		this.attaqueCourante = null;
	}
	
	/**
	 * 
	 */
	public Jeu() {
		super();
		this.joueurs = new LinkedList<Joueur>();
	}

	/**
	 * @return the persoAttaquant
	 */
	public Personnage getPersoAttaquant() {
		return persoAttaquant;
	}
	/**
	 * @param persoAttaquant the persoAttaquant to set
	 */
	public void setPersoAttaquant(Personnage persoAttaquant) {
		this.persoAttaquant = persoAttaquant;
	}
	/**
	 * @return the joueurs
	 */
	public LinkedList<Joueur> getJoueurs() {
		return joueurs;
	}
	/**
	 * @param joueur joueur à ajouter
	 */
	public void addJoueur(Joueur joueur) {
		this.joueurs.add(joueur);
	}
	
	/**
	 * Indique le personnage choisi par la joueur et demande à l'ajouter à l'équipe du joueur
	 * @param nomType nom du type du personnage choisi 
	 * @param joueur indice du joueur dans la liste des joueurs
	 * @param nom nom du personnage
	 * @param age âge du personnage
	 */
	// TODO : changer l'exception par un nouveau type
	public void choixPersonnage(String nomType, int joueur, String nom, int age) throws Exception {
		joueurs.get(joueur).addPerso(nomType, nom, age);
	}
	
	/** Retourne le personnage choisi par le joueur sur le plateau, renvoie null si aucun personnage ne se trouve sur cette case
	 * @param x abscisse de la case sélectionnée
	 * @param y ordonnées de la case sélectionnée
	 * @return perso le personnage choisi par le joueur
	 */
	public Object choixCase(int x, int y){
		// Pour stocker les résultats
		LinkedList<Attaque> attaques;
		int deplacement;
		Object[] res = new Object[2];
		setPersoAttaquant(joueurCourant.getPerso(x,y));
		attaques = persoAttaquant.getAttaques();
		deplacement = persoAttaquant.getDeplacement();
		res[1] = attaques;
		res[2] = deplacement;
		return res;
	}
	
	/** Renvoie les cibles possibles du personnage attaquant
	 * @param nomAttaque nom de l'attaque lancée par le personnage attaquant
	 * @return la liste des personnages cibles
	 */
	public LinkedList<Personnage> getCibles(String nomAttaque){
		int portee;
		int x;
		int y;
		for(int i=0; i< persoAttaquant.getAttaques().size(); i++){
			if(persoAttaquant.getAttaques().get(i).getNom() == nomAttaque){ // mettre un .equals() ?
				portee = persoAttaquant.getAttaques().get(i).getPortee();
			}
		}
		x = persoAttaquant.getPositionX();
		y = persoAttaquant.getPositionY();
		return calculerCibles(x, y, portee);
	}
	
	/** Renvoie les personnages se trouvant dans la portée d'une attaque
	 * @param x l'abscisse de la source de l'attaque
	 * @param y l'ordonnée de la source de l'attaque
	 * @param portee la portée de l'attaque
	 * @return la liste de personnages se trouvant sous la portée de l'attaque 
	 */
	public LinkedList<Personnage> calculerCibles(int x, int y, int portee){
		// TODO: faire une fonction qui renvoie le personnage se trouvant sur la case (x,y), s'il existe, quel que soit le joueur
		LinkedList<Personnage> res; // stocke le résultat final
		// On inspecte la verticale et l'horizontale, en ne regardant pas la case où l'on se trouve
		// à gauche de la case
		for(int i=(x-portee); i<x; i++){
			if(getPerso(i, y) != null){
				res.add(getPerso(i,y));
			}
		}
		// à droite de la case
		for(int i=(x+1); i<(x+portee+1); i++){
			if(getPerso(i, y) != null){
				res.add(getPerso(i,y));
			}
		}
		// en haut de la case
		for(int j=(y+1); j<(y+portee+1); j++){
			if(getPerso(x, j) != null){
				res.add(getPerso(j,y));
			}
		}
	}
}
