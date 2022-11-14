package uz.pdp.appannotationcascade.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String code;
    @OnDelete(action = OnDeleteAction.CASCADE)        //qachonki client uchsa bank accountam
    @OneToOne(fetch = FetchType.LAZY,optional = false)
    private Client client;

}
