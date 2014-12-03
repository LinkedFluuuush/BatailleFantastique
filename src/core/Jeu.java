package core;

import exception.ClassePersonnageManquanteException;

import java.util.LinkedList;

import exception.AttaqueManquanteException;
import exception.DeplacementException;

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
	public void choixPersonnage(String nomType, int joueur, String nom, int age) throws ClassePersonnageManquanteException {
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
	public LinkedList<Personnage> getCibles(String nomAttaque) throws AttaqueManquanteException {
		int portee = -1;
		int x;
		int y;
		for(int i=0; i< persoAttaquant.getAttaques().size(); i++){
			if(persoAttaquant.getAttaques().get(i).getNom() == nomAttaque){ // mettre un .equals() ?
				portee = persoAttaquant.getAttaques().get(i).getPortee();
			}
		}
		if(portee < 0){
			throw new AttaqueManquanteException();
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
		LinkedList<Personnage> res = new LinkedList<Personnage>(); // stocke le résultat final
		// On inspecte la verticale et l'horizontale, en ne regardant pas la case où l'on se trouve
		// à gauche de la case
		for(int i=(x-portee); i<x; i++){
			if(joueurCourant.getPerso(i, y) != null){
				res.add(joueurCourant.getPerso(i,y));
			}
		}
		// à droite de la case
		for(int i=(x+1); i<(x+portee+1); i++){
			if(joueurCourant.getPerso(i, y) != null){
				res.add(joueurCourant.getPerso(i,y));
			}
		}
		// en haut de la case
		for(int j=(y+1); j<(y+portee+1); j++){
			if(joueurCourant.getPerso(x, j) != null){
				res.add(joueurCourant.getPerso(j,y));
			}
		}
		// en bas de la case
		for(int j=(y-portee); j<y; j++){
			if(joueurCourant.getPerso(x, j) != null){
				res.add(joueurCourant.getPerso(j,y));
			}
		}
		return res;
	}
	
	/** Renvoie le personnage se trouvant sur la case (x,y)
	 * @param x abscisse de la case
	 * @param y ordonnée de la case
	 * @return personnage se trouvant sur la case (x,y) (null s'il n'y en a pas)
	 */
	public Personnage getPerso(int x, int y){
		Personnage temp = null; // variable temporaire
		for(Joueur j : joueurs){
			temp = j.getPerso(x,y);
			if(temp != null){
				return temp;
			}
		}
		return null;
	}
	
	
	/** Applique les effets de l'attaque sélectionnée 
	 * @param attaque attaque sélectionnée précédemment
	 * @param persoCible personnage cible de l'attaquée
	 */
	public void attaquerCible(Attaque attaque, Personnage persoCible) {
		int effet; // effets de l'attaque
		effet = attaque.getEffet();
		persoCible.appliquerEffets(effet);
		
	}
	
	/** Déplace le personnage sélectionné (persoAttaquant)
	 * @param x abscisse de la case cible
	 * @param y ordonnée de la case cible
	 * @param deplacement déplacement du personnage
	 */
	public void deplacer(int x, int y, int deplacement) throws DeplacementException { // deplacement est récupéré dans choixCase
		if(verifDeplacementValide(x, y, deplacement)){
			persoAttaquant.deplacerPerso(x,y);
		} else {
			throw new DeplacementException();
		}
	}
	
//	/** Vérifie si le déplacement voulu est valide
//	 * @param x abscisse de la case cible
//	 * @param y ordonnée de la case cible
//	 * @param deplacement déplacement du personnage
//	 * @return validité du déplacement
//	 */
//	public boolean verifDeplacementValide(int x, int y, int deplacement){
//		int nbCasesParcourues = 0; // nombre de cases nécessaires à parcourir pour arriver à la case cible
//		int deltaX = Math.abs(x - persoAttaquant.getPositionX()); // x - persoAttaquant.getPositionX() représente l'écart en abscisse de la position courante à la cible
//		int deltaY = Math.abs(y - persoAttaquant.getPositionY()); // y - persoAttaquant.getPositionY() représente l'écart en ordonnée de la position courante à la cible
//		nbCasesParcourues += deltaX;
//		nbCasesParcourues += deltaY;
//		return((nbCasesParcourues > deplacement) || getPerso(x,y) == null);
//	}
	
	
}