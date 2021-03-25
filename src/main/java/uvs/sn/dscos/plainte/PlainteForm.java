package uvs.sn.dscos.plainte;

import java.util.Date;

import lombok.Data;

@Data
public class PlainteForm {
	
	private String numeroPlainte;
	private Date dateArrivee;
	private String provenance;
	private String type;

}
