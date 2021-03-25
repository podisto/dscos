package uvs.sn.dscos.plainte;

import java.util.Date;

import lombok.Data;

@Data
public class Plainte {
	private String numero;
	private Date dateArrivee;
	private String provenance;
	private String type;

}
