package nttdata.bootcamp.quarkus.audit.entity;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
public class PersonType {

    public String key;
    public int value;

    public PersonType(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public PersonType() {
    }

}
