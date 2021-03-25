package uvs.sn.dscos.plainte;

import java.text.ParseException;
import java.util.List;

public interface PlainteService {
	
	void ajouterUnePlainte(PlainteForm form) throws ParseException;

	List<Plainte> afficherListePlainte();
	
	boolean estCeQueLeNumeroExisteDeja(String numero);

}
