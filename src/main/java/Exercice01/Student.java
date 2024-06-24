package Exercice01;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    private int id;
    private String lastname;
    private String firstname;
    private int classNumber;
    private LocalDate diploma_date;
}
