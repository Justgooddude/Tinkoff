package edu.hw7.cacheServ;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RWCacheDBTest {

    private RWCacheDB cacheService=new RWCacheDB();

    @Test
    void testFindByName() {
        var person = new Person(1, "name", "address", "phone");
        var person2 = new Person(2, "name", "address2", "phone2");
        var person3 = new Person(3, "name1", "address", "phone2");
        cacheService.add(person);
        cacheService.add(person2);
        cacheService.add(person3);
        List<Person> result = cacheService.findByName("name");
        assertThat(result.containsAll(List.of(person2,person))).isTrue();
    }

    @Test
    void testFindByAddress() {
        var person = new Person(1, "name", "address", "phone");
        var person2 = new Person(2, "name", "address2", "phone22");
        var person3 = new Person(3, "name1", "address", "phone");
        cacheService.add(person);
        cacheService.add(person2);
        cacheService.add(person3);
        List<Person> result = cacheService.findByAddress("address");
        assertThat(result.containsAll(List.of(person,person3))).isTrue();
    }

    @Test
    void testFindByPhone() {
        var person = new Person(1, "name", "address", "phone");
        var person2 = new Person(2, "name", "address2", "phone2");
        var person3 = new Person(3, "name1", "address", "phone2");
        cacheService.add(person);
        cacheService.add(person2);
        cacheService.add(person3);
        List<Person> result = cacheService.findByPhone("phone2");
        assertThat(result.containsAll(List.of(person2,person3))).isTrue();
    }

    @Test
    void testAddPerson() {
        var person = new Person(1, "name", "address", "phone");
        cacheService.add(person);
        assertThat(cacheService.findByName("name")).isEqualTo(List.of(person));
        assertThat(cacheService.findByAddress("address")).isEqualTo(List.of(person));
        assertThat(cacheService.findByPhone("phone")).isEqualTo(List.of(person));
    }

    @Test
    void testDeletePerson() {
        var person = new Person(1, "name", "address", "phone");
        cacheService.add(person);
        cacheService.delete(1);
        assertThat(cacheService.findByName("name")).isEmpty();
        assertThat(cacheService.findByAddress("address")).isEmpty();
        assertThat(cacheService.findByPhone("phone")).isEmpty();
    }

}
