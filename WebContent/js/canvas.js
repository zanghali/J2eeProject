var urlroot = "/Projet_Majeur/";
//
var canvas = document.getElementById('canvas');
var context = canvas.getContext('2d');

// style de la ligne de parcour
context.lineWidth = 4;
context.lineJoin = 'round';
context.lineCap = 'round';
context.strokeStyle = 'red';

// chargement des images
var obstacle = new Image();
obstacle.src = '../img/obstacle.png';
var arbre = new Image();
arbre.src = '../img/arbre.png';
var free = new Image();
free.src = '../img/herbe.png';
var robotav = new Image();
robotav.src = '../img/bas.png';
var robotar = new Image();
robotar.src = '../img/haut.png';
var robotd = new Image();
robotd.src = '../img/droite.png';
var robotg = new Image();
robotg.src = '../img/gauche.png';

// taille des image 160x160
var sourceX = 0, sourceY = 0;
var sourceWidth = 160, sourceHeight = 160;
var ecart;
var xx, yy;
var destX, destY;

function MAJ() {

	$.get(urlroot + "rest/cmd/env", function(data, status) {

		// console.log(data);
		context.clearRect(0, 0, canvas.height, canvas.width);
		context.fillRect(0, 0, canvas.height, canvas.width);
		var j = 0;

		var ecart = canvas.height / data.environnement.X;
		var destWidth = ecart;
		var destHeight = ecart;
		while (j < data.environnement.taille) {
			xx = data.carte[j].x;
			yy = data.carte[j].y;
			destX = xx * ecart;
			destY = yy * ecart;
			if (document.getElementById("Obstacles").checked == true) {
				if ((data.carte[j].val) == "OBSTACLE") {
					if (((xx + yy) % 2) == 0) {
						context.drawImage(obstacle, sourceX, sourceY,
								sourceWidth, sourceHeight, destY, destX,
								destWidth, destHeight);
					} else {
						context.drawImage(arbre, sourceX, sourceY, sourceWidth,
								sourceHeight, destY, destX, destWidth,
								destHeight);
					}
				}
			} else if ((data.carte[j].val) == "OBSTACLE") {
				context.drawImage(free, sourceX, sourceY, sourceWidth,
						sourceHeight, destY, destX, destWidth, destHeight);
			}

			if ((data.carte[j].val) == "FREE"
					|| (data.carte[j].val) == "VISITE") {
				context.drawImage(free, sourceX, sourceY, sourceWidth,
						sourceHeight, destY, destX, destWidth, destHeight);
			}

			if (document.getElementById("Robot").checked == true) {
				if ((data.carte[j].val) == "ROBOT") {
					if (data.environnement.orientation == "S") {
						context.drawImage(robotav, sourceX, sourceY,
								sourceWidth, sourceHeight, destY, destX,
								destWidth, destHeight);
					}
					if (data.environnement.orientation == "N") {
						context.drawImage(robotar, sourceX, sourceY,
								sourceWidth, sourceHeight, destY, destX,
								destWidth, destHeight);
					}
					if (data.environnement.orientation == "E") {
						context.drawImage(robotd, sourceX, sourceY,
								sourceWidth, sourceHeight, destY, destX,
								destWidth, destHeight);
					}
					if (data.environnement.orientation == "O") {
						context.drawImage(robotg, sourceX, sourceY,
								sourceWidth, sourceHeight, destY, destX,
								destWidth, destHeight);
					}
				}
			} else if ((data.carte[j].val) == "ROBOT") {
				context.drawImage(free, sourceX, sourceY, sourceWidth,
						sourceHeight, destY, destX, destWidth, destHeight);
			}
			j = j + 1;
		}
		
		var taille_liste = data.historique.length - 1;
		
		var i=0;
		context.beginPath();
		for (i=0; i<taille_liste; i++){
			
			context.moveTo(((data.historique[i].y) * ecart)
					+ (ecart / 2),
					((data.historique[i].x) * ecart)
							+ (ecart / 2));
			context.lineTo(((data.historique[i+1].y) * ecart)
					+ (ecart /2), ((data.historique[i+1].x) * ecart)
					+ (ecart /2));
			
		}
		context.closePath();

		// condition pour l'affichage du chemin
		if (document.getElementById("Path").checked == true) {
			context.stroke();
		}

	});
}