package java8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class OptionalTest {

    @Test
    public void test(){
        Optional<Person> optionalPerson = Optional.of(
                new Person(Optional.of(new Car(Optional.of(new Insurance("***保险"))))));
/**
 * 1. Person::getCar -> Optional<Car>
 * 2. optionalPerson.map(Person::getCar) -> Optional<Optional<Car>>
 * 3. Optional<Optional<Car>> 显然不能调用 map(Car::getInsurance)
 * 4. 只有Optional<Car>.map(Car::getInsurance)才行，所以多套了一层Optional
 * 5. 为了去掉多层Optional(本例只用一层),得使用flatMap
 */
//        Optional<String> optionalStr = optionalPerson.map(Person::getCar).map(Car::getInsurance).map(Insurance::getName);

        String insuranceName = optionalPerson.
                flatMap(Person::getCar).
                flatMap(Car::getInsurance).
                map(Insurance::getName).
                orElse("null value");
        System.out.println(insuranceName);  //***保险
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Person{
    private Optional<Car> car;
}
@Data
@AllArgsConstructor
@NoArgsConstructor
class Car{
    private Optional<Insurance> insurance;
}
@Data
@AllArgsConstructor
@NoArgsConstructor
class Insurance{
    private String name;
}



