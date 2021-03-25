package uvs.sn.dscos.plainte;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlainteServiceImpl implements PlainteService {

    public static final String PLAINTE_DIRECTE = "DIRECTE";
    public static final String PLAINTE_INDIRECTE = "INDIRECTE";

    private final PlainteRepository plainteRepository;

    @Override
    public void ajouterUnePlainte(PlainteForm form) throws ParseException {
        Plainte plainte = new Plainte();
        plainte.setNumero(form.getNumero());
        Date dateArrivee = convertirDateStringAuFormatAnneeMoisJourEnDate(form.getDateArrivee());
        plainte.setDateArrivee(dateArrivee);
        if (form.getProvenance().equals("BZR")) {
            plainte.setType(PLAINTE_DIRECTE);
        } else {
            plainte.setType(PLAINTE_INDIRECTE);
        }
        plainte.setProvenance(ConstantesPlainte.getLibelleProvenance(form.getProvenance()));
        
        plainteRepository.enregistrerEnBase(plainte);
    }

    private Date convertirDateStringAuFormatAnneeMoisJourEnDate(String dateArrivee) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(dateArrivee);
    }

	@Override
	public List<Plainte> afficherListePlainte() {
		return plainteRepository.listePlainte();
	}

	@Override
	public boolean estCeQueLeNumeroExisteDeja(String numero) {
		return plainteRepository.estCeQueLeNumeroExiste(numero);
	}
}
