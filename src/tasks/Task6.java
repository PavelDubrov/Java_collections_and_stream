package tasks;

import common.Area;
import common.Person;
import common.Task;

import java.time.Instant;
import java.util.*;

/*
Имеются
- коллекция персон Collection<Person>
- словарь Map<Integer, Set<Integer>>, сопоставляющий каждой персоне множество id регионов
- коллекция всех регионов Collection<Area>
На выходе хочется получить множество строк вида "Имя - регион". Если у персон регионов несколько, таких строк так же будет несколько
 */
public class Task6 implements Task {

  private Set<String> getPersonDescriptions(Collection<Person> persons,
                                            Map<Integer, Set<Integer>> personAreaIds,
                                            Collection<Area> areas) {

    Set<String> nameArea = new HashSet<>();
    Map<Integer, String> areasMap = new HashMap<>();

    for (Area area : areas) {
      areasMap.put(area.getId(), area.getName());
    }

    Set<Integer> set = new HashSet<>();
    for (Person person : persons) {
      set = personAreaIds.get(person.getId());

      for (Integer areaId : set) {
        nameArea.add(person.getFirstName() + " - " + areasMap.get(areaId));
      }
    }
    // Я подозреваю что тут асимптотика O(N*M)
    // где N - размер коллекции Персон, а M - средниее количество городов на 1 персону.
    return nameArea;
  }

  @Override
  public boolean check() {
    List<Person> persons = List.of(
        new Person(1, "Oleg", Instant.now()),
        new Person(2, "Vasya", Instant.now())
    );
    Map<Integer, Set<Integer>> personAreaIds = Map.of(1, Set.of(1, 2), 2, Set.of(2, 3));
    List<Area> areas = List.of(new Area(1, "Moscow"), new Area(2, "Spb"), new Area(3, "Ivanovo"));
    return getPersonDescriptions(persons, personAreaIds, areas)
        .equals(Set.of("Oleg - Moscow", "Oleg - Spb", "Vasya - Spb", "Vasya - Ivanovo"));
  }
}
