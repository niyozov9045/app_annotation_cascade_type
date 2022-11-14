package uz.pdp.appannotationcascade.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties(value = {"birthDate", "fullName"})
@Where(clause = "birth_date is not null") // bizni har bir querimizga uzini ichidagi shartni qo'shib beradi
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //@JsonIgnore  // manashu feltni frontetga berib yuborishni oldini olish uchun ishlatiladi
    @OrderBy(value = "city asc, street asc ") //qachonki bir fieltni ichida boshqa bir list fild keladigan bulsa ushani sartirovka qiberadi
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)  //mappeBy:  alohida table ochishni oldini oladi
    private List<Address> addresses;                            //Remove: bir biriga bo g'langan tabllarni o'chiradi

    @Transient    // malumotlar omboriga saqlanmagan holatda, hohlagan paytda filtlarni tashishda xizmat qilyapti
    private Integer countFullNameLetters;


    private String fullName;
    private LocalDate birthDate;
    @Transient
    private Integer age;


    public Integer getAge() {
        if (birthDate == null)
            return 0;
        return Period.between(birthDate, LocalDate.now()).getYears(); // yoshi nechchidaligini aniqlab beruvchi method

    }

    public Integer getCountFullNameLetters() {
        return fullName != null ? fullName.length() : 0;
    }
}
