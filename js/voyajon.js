/**
 * fichier contenant nos fonctions
 */

/*
 * Fonction permettant de tester la validité du format d'une adresse email
 */
function testformatemail(str) {
	// Définition des caractères non autorisés dans une adresse email
	var autorisation = /[^a-zA-Z0-9.@_-]/;

	if (autorisation.test(str)) {
		// s'il y'a un caratère non autorisé, on retourne 0 et le test s'arrête
		return 0;
	} else {
		// Tous les caractères sont autorisés, on continu le test
		// vérification du format de l'adresse email

		// premier caractère de la chaine
		var format1 = /^[-@._]{1}/;

		// dernier caractère de la chaine
		var format2 = /[_.@-]$/;
		if (format1.test(str) || format2.test(str)) {
			// si le premier ou de le dernier caractère de la chaine est
			// incorrecte on renvoit 0
			return 0;
		} else {
			// si le premier ou de le dernier caractère de la chaine est
			// correcte on teste la chaine
			var format = /[a-z0-9]+@+[a-z]+\.[a-z]/;
			if (format.test(str)) {
				// Si le format est valide on renvoit 1
				return 1;
			} else {
				// Si le format est invalide on renvoit 0
				return 0;
			}
		}

	}
}
/*
 * fonction permettant de tester la disponiblité d'une adresse email
 */

function testdisponibiliteemail(email) {
	var a = testformatemail(email);
	if (a == 1) {
		$.ajax({
			type : "post",
			url : "testdiponibiliteemail",
			cache : false,
			data : 'email=' + email,
			success : function(response) {

				return response;
			},
			error : function() {
				alert('Error while request..');
			}
		});
	} else {
		// retourne 0 si l'adresse email est invalide
		return 0;
	}

}
/*
 * Fonction permettant de masquer un element
 */
function masque(element, vitesse) {
	if (vitesse == "") {
		$(element).fadeOut("fast");
	} else {
		$(element).fadeOut(vitesse);
	}

}
/*
 * Fonction permettant d'afficher un élément
 */
function affiche(element, vitesse) {
	if (vitesse == "") {
		$(element).fadeIn("fast");
	} else {
		$(element).fadeIn(vitesse);
	}

}
/*
 * Transformer le curseur de la souris
 */
function transformecurseur(element, modele, decoration) {
	$(element).hover(function() {
		$(element).css('cursor', modele).css('text-decoration', decoration);
	});
}

/*
 * Fonction permettant de tester la validité d'un mot de passe
 */
function testpassword(str) {
	// Test du premier caractère de la chaine
	var first = /^[A-Z]{1}/;

	if (!first.test(str)) {
		// Si le premier caractère est invalide, on retourne 0
		return 0;
	} else {
		// Si le caractère est autorisé
		// on teste si le mot de passe contient un chiffre
		var chiffre = /[0-9]/;
		if (!chiffre.test(str)) {
			// Si la chaine ne contient aucun chiffre on retourne 0
			return 0;
		} else {
			// si la chaine contient au moins un chiffre
			// on teste la longueur de la chaine
			var longueur = 8;
			if (str.length < longueur) {
				// Si la longueur de la chaine n'est pas valide, on retourne 0
				return 0;
			} else {
				// dans le cas contraire on retourne 1
				return 1;
			}
		}
	}
}
/*
 * fonction permettant de tester la longueur d'une chaine de caractère
 */
function longueur(str, min, max) {

	if (min <= str.length && str.length <= max) {
		// Si la chaine de caractère est comprise entre le min et le max requis,
		// on retourne 1
		return 1;
	} else {
		// Dans le cas contraire, on retourne 0
		return 0;
	}

}