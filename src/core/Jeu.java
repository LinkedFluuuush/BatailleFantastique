package core;

import exception.ClassePersonnageManquanteException;

import java.util.LinkedList;

import exception.AttaqueManquanteException;
import exception.DeplacementException;

/**
 * Description de la classe
 * @author llaluque jlouvet
 * @version 1.5
 */
public class Jeu {
	private Personnage persoAttaquant;
	private LinkedList<Joueur> joueurs;
    private Joueur joueurCourant;
	private Attaque attaqueCourante;


    /**
     * Définit l'état du jeu. Permet à l'interface de savoir ou en est la partie
     * SELECTION : Sélection des personnages en cours
     * PLACEMENT : Placement des personnages sur la carte en cours
     * ENCOURS : Partie en déroulement normal
     */
    public enum Etat {
        SELECTION, ENCOURS, PLACEMENT;
    }

    private Etat etatCourant;

    /**
     * Définit le nombre de personnages par équipe
     */
    private int nPersonnages;

    /**
     * Définit le nombre de colonne de la carte
     */
    private int nColonnes;

    /**
     * Définit le nombre de lignes de la carte
     */
    private int nLignes;
    
    /**
     * Définit la taille de la zone de départ en nombre de colonnes
     */
    private int tailleZoneDepart;

    /**
	 * @param persoAttaquant
	 * @param joueurs
	 */
	public Jeu(Personnage persoAttaquant, LinkedList<Joueur> joueurs, Etat etatCourant) {
		super();
		this.persoAttaquant = persoAttaquant;
		this.joueurs = joueurs;
		this.joueurCourant = joueurs.getFirst();
//		this.attaqueCourante = null;
        this.etatCourant = etatCourant;
	}
	
	/**
	 * 
	 */
	public Jeu() {
		super();
		this.joueurs = new LinkedList<Joueur>();
        this.etatCourant = Etat.SELECTION;
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
     *
     * @return Le joueur dont c'est le tour de jouer
     */
    public Joueur getJoueurCourant() {
        return joueurCourant;
    }

    /**
     *
     * @param joueurCourant Le nouveau joueur qui doit jouer
     */
    public void setJoueurCourant(Joueur joueurCourant) {
        this.joueurCourant = joueurCourant;
    }

    /**
     *
     * @return l'état en cours du jeu
     */
    public Etat getEtatCourant() {
        return etatCourant;
    }

    /**
     *
     * @param etatCourant Le nouvel état du jeu
     */
    public void setEtatCourant(Etat etatCourant) {
        this.etatCourant = etatCourant;
    }

    /**
     *
     * @return la taille de chaque équipe
     */
    public int getnPersonnages() {
        return nPersonnages;
    }

    /**
     *
     * @param nPersonnages la nouvelle taille d'équipe
     */
    public void setnPersonnages(int nPersonnages) {
        this.nPersonnages = nPersonnages;
    }

    /**
     *
     * @return le nombre de colonnes
     */
    public int getnColonnes() {
        return nColonnes;
    }

    /**
     *
     * @param nColonnes Le nouveau nombre de colonnes
     */
    public void setnColonnes(int nColonnes) {
        this.nColonnes = nColonnes;
    }

    /**
     *
     * @return Le nombre de lignes
     */
    public int getnLignes() {
        return nLignes;
    }

    /**
     *
     * @param nLignes Le nouveau nombre de lignes
     */
    public void setnLignes(int nLignes) {
        this.nLignes = nLignes;
    }

    /**
    *
    * @return La taille de la zone
    */
   public int getTailleZoneDepart() {
       return tailleZoneDepart;
   }

   /**
    *
    * @param tailleZoneDepart la nouvelle taille de zone de départ
    */
   public void setTailleZoneDepart(int tailleZoneDepart) {
       this.tailleZoneDepart = tailleZoneDepart;
   }
    
	public Attaque getAttaqueCourante() {
		return attaqueCourante;
	}

	public void setAttaqueCourante(Attaque attaqueCourante) {
		this.attaqueCourante = attaqueCourante;
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
	 *//*
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
	}*/
	
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
        persoCible.appliquerEffets(attaque);

        if(persoCible.getPv() <= 0){
            for(Joueur j : this.getJoueurs()){
                if(j.getPersonnages().contains(persoCible)){
                    j.getPersonnages().remove(persoCible);

                    if(j.getPersonnages().size() == 0){
                        this.getJoueurs().remove(j);
                    }

                    break;
                }
            }
        }
	}
	
	/** Déplace le personnage sélectionné (persoAttaquant)
	 * @param x abscisse de la case cible
	 * @param y ordonnée de la case cible
	 * @param deplacement déplacement du personnage
	 */
	public void deplacer(int x, int y, int deplacement) throws DeplacementException { // deplacement est récupéré dans choixCase
        if(persoAttaquant.verifDeplacementValide(x, y, this)){
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
	
	/** Sélectionne un personnage
	 * @param i position du personnage dans la liste du joueur
	 */
    public Personnage choixPerso(int i) {
    	return joueurCourant.getPersonnages().get(i);
    }
    
    /** Teste si une position est dans la zone de placement initial des persoonages
	 * @param x abscisse de la case cible
	 * @param y ordonnée de la case cible
	 * @return estDansZone booléen valant vrai si le personnage est dans la zone de départ de son côté du plateau
	 */
    public boolean estDansZoneDepart(int x, int y){
    	if(joueurs.indexOf(joueurCourant) == 0) { // Si le joueur courant est le joueur 1, il doit passer ses personnages à gauche
    		return(x<=tailleZoneDepart - 1);
    	} else {
    		return(x>=(nColonnes - tailleZoneDepart)); // Si le joueur courant est le joueur 2, il doit passer ses personnages à droite
    	}
    }
    
    /** Place un personnage sur le plateau
	 * @param persoAPlacer personnage à placer
	 * @param x abscisse de la case cible
	 * @param y ordonnée de la case cible
	 */
    public boolean placePerso(Personnage persoAPlacer, int x, int y){
    	if((getPerso(x,y) == null) && estDansZoneDepart(x,y)){
    		persoAPlacer.nouvelleCase(x,y);
			return true;
    	} else {
			return false;
		}
    }
    
    /** Vérifie que la taille de l'équipe est bien valide
	 * @param n taille d'équipe proposée
	 */
    public boolean validerTailleEquipe(int n){
    	return((n-5)*(n-1)<=0); // Condition équivalente à "n<5 et n>1"
    }
    
    /** Vérifie que la taille du plateau est bien valide
	 * @param n nombre de ligne proposé
	 * @param m nombre de colonnes proposé
	 */
    public boolean validerTaillePlateau (int n, int m){
    	return((n>=5) && (m>=5));
    }
    
    /** Vérifie que la taille de la zone de départ est bien valide
	 * @param n nombre de rangées proposées pour le départ
	 */
    public boolean validerTailleZoneDepart(int n){
    	return((n>=2) && (n <= nColonnes/2));
    }
}
