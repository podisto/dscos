package uvs.sn.dscos.plainte;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryPlainteRepository implements PlainteRepository {

    private final Set<Plainte> plaintes = new LinkedHashSet<>();

    @Override
    public void enregistrerEnBase(Plainte plainte) {
        plaintes.add(plainte);
    }

	@Override
	public List<Plainte> listePlainte() {
		return new ArrayList<>(plaintes);
	}
}
