package uvs.sn.dscos.plainte;

import java.util.HashMap;
import java.util.Map;

public class ConstantesPlainte {
	
	public static Map<String, String> getProvenance() {
		Map<String, String> provenance = new HashMap<>();
		provenance.put("BZR", "Brigade Zonale de Rufisque");
		provenance.put("DGDSCOS", "Direction Générale de DSCOS");
		provenance.put("TGID", "Tribunal de Grande Instance De Dakar");
		provenance.put("TIR", "Tribunal d'Instance De Rufisque");	
		return provenance;
	}
	
    public static String getLibelleProvenance(String codeProvenance) {
    	return ConstantesPlainte.getProvenance().get(codeProvenance);
    }

}
