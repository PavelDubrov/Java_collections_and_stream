package tasks;

import common.Person;
import common.PersonService;
import common.Task;

import java.util.*;
import java.util.stream.Collectors;

/*
Задача 1
Метод на входе принимает List<Integer> id людей, ходит за ними в сервис
(он выдает несортированный Set<Person>, внутренняя работа сервиса неизвестна)
нужно их отсортировать в том же порядке, что и переданные id.
Оценить асимпотику работы
 */
public class Task1 implements Task {

  // !!! Редактируйте этот метод !!!
  private List<Person> findOrderedPersons(List<Integer> personIds) {
    Set<Person> persons = PersonService.findPersons(personIds);

    // Создаю пустую мапу и лист. capacity нам заранее известен
    Map<Integer, Person> mapOfPersons = new HashMap<>(personIds.size());
    List<Person> personList = new ArrayList<>(personIds.size());

    // Бежим по сету, закидывая в мапу: ключ - id, значение - Персона
    // асимптотика O(n)
    for (Person person : persons) {
      mapOfPersons.put(person.getId(), person);
    }
    // бежим по нашему листу personIds, заполняя наш mapOfPersons
    // асимптотика O(n)
    for (Integer id : personIds) {
      personList.add(mapOfPersons.get(id));
    }
    // в итоге асимптотика = 2 O(n).
    // на сколько я помню - константа не учитывается, поэтому получается O(n)
    return personList;
  }

  @Override
  public boolean check() {
    List<Integer> ids = List.of(1, 2, 3);

    return findOrderedPersons(ids).stream()
        .map(Person::getId)
        .collect(Collectors.toList())
        .equals(ids);
  }

}
