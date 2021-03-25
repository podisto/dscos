package uvs.sn.dscos.plainte;

import java.util.List;

public interface PlainteRepository {
    void enregistrerEnBase(Plainte plainte);

	List<Plainte> listePlainte();

	boolean estCeQueLeNumeroExiste(String numeroSaisie);
}

