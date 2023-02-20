package nttdata.bootcamp.quarkus.multichannel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

	private Long idClient;
    private String personType;
    private String documentType;
    private String documentNumber;
    private String completeName;
    private String surnames;
    private String sex;
    private String email;
    private String cellPhone;
	
}
