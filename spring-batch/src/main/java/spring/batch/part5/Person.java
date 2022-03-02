package spring.batch.part5;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private int id;
    private String name;
    private String age;
    private String address;
}
