package nttdata.bootcamp.quarkus.multichannel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nttdata.bootcamp.quarkus.multichannel.entity.Multichannel;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MultichannelResponse extends ResponseBase {

    private List<Multichannel> multichannels;

}
