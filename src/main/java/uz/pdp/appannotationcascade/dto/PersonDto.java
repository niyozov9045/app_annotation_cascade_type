package uz.pdp.appannotationcascade.dto;

import lombok.Data;

import java.util.List;

@Data
public class PersonDto {

    private String fullName;

    private List<AddressDto> addressDtoList;
}

