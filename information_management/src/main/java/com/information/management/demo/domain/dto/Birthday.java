package com.information.management.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
@NoArgsConstructor
@Data
public class Birthday {

    private int yearOfBirthday;

    private int monthOfBirthday;

    private int dayOfBirthday;

    public Birthday(LocalDate birthday) {
        this.yearOfBirthday = birthday.getYear();
        this.monthOfBirthday = birthday.getMonthValue();
        this.monthOfBirthday = birthday.getDayOfMonth();
    }
}
