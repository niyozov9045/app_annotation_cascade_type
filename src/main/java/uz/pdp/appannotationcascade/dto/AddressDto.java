package uz.pdp.appannotationcascade.dto;

import com.sun.istack.NotNull;
import lombok.Data;
import uz.pdp.appannotationcascade.entity.Person;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

@Data
public class AddressDto {

    @NotNull
    @Size(min = 3,max = 50)
    private String city;

    @NotNull
    @Size(min = 4,max = 55)
    private String street;

    private Integer personId;


}

