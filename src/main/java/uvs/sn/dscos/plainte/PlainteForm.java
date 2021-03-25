package uvs.sn.dscos.plainte;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class PlainteForm {
	
	@NotEmpty(message = "Le numéro est obligatoire")
	private String numero;
	private String dateArrivee;
	private String provenance;
}
