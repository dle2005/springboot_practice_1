package com.information.management.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDate;

@Embeddable
@NoArgsConstructor
@Data
public class Birthday {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer yearOfBirthday;

    private Integer monthOfBirthday;

    private Integer dayOfBirthday;

    private Birthday(LocalDate birthday) {
        this.yearOfBirthday = birthday.getYear();
        this.monthOfBirthday = birthday.getMonthValue();
        this.monthOfBirthday = birthday.getDayOfMonth();
    }



    public static Birthday of(LocalDate birthday) {
        return new Birthday(birthday);
    }
}
