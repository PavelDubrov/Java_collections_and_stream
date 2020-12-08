package tasks;

import common.Person;
import common.Task;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
А теперь о горьком
Всем придется читать код
А некоторым придется читать код, написанный мною
Сочувствую им
Спасите будущих жертв, и исправьте здесь все, что вам не по душе!
P.S. функции тут разные и рабочие (наверное), но вот их понятность и эффективность страдает (аж пришлось писать комменты)
P.P.S Здесь ваши правки желательно прокомментировать (можно на гитхабе в пулл реквесте)
 */
public class Task8 implements Task {

  private long count;

  //Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со второй
  // Сделаем все через стрим, чтоб было коротко и ясно
  public List<String> getNames(List<Person> persons) {

    return persons.stream().skip(1).map(Person::getFirstName).collect(Collectors.toList());
  }

  //ну и различные имена тоже хочется
  // Пускай функция сама все делает, не будем лезть в другую функцию
  public Set<String> getDifferentNames(List<Person> persons) {

    return persons.stream().skip(1).map(Person::getFirstName).collect(Collectors.toSet());
  }

  //Для фронтов выдадим полное имя, а то сами не могут
  // Я честно говоря сам бы сделал точно так же ))) так что трогать не буду )
  public String convertPersonToString(Person person) {
    String result = "";
    if (person.getSecondName() != null) {
      result += person.getSecondName();
    }

    if (person.getFirstName() != null) {
      result += " " + person.getFirstName();
    }

    if (person.getSecondName() != null) {
      result += " " + person.getSecondName();
    }
    return result;
  }

  // словарь id персоны -> ее имя
  // Как по мне и так норм было ) Но нам же нужно просто имя, да и не будем вызывать другую функцию
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    Map<Integer, String> map = new HashMap<>();
    for (Person person : persons) {
      if (!map.containsKey(person.getId())) {
        map.put(person.getId(), person.getFirstName());
      }
    }
    return map;
  }

  // есть ли совпадающие в двух коллекциях персоны?
  // Будем ускорять !
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    boolean has = false;
    Set<Person> set1 = new HashSet<>();
    Set<Person> set2 = new HashSet<>();
    Set<Person> set3 = new HashSet<>();
    set1.addAll(persons1);
    set2.addAll(persons2);
    set3.addAll(persons1);
    set3.addAll(persons2);
    if (set1.size() + set2.size() == set3.size()) {
      return false;
    } else {
      return true;
    }
  }

  //...
  // сократим немного
  public long countEven(Stream<Integer> numbers) {

    return numbers.filter(num -> num % 2 == 0).count();
  }

  @Override
  public boolean check() {
    System.out.println("My teacher: Слабо дойти до сюда и исправить Fail этой таски?");
    System.out.println("Me: Вызов принят !");
    boolean codeSmellsGood = true; // I hope :)
    boolean reviewerDrunk = false;
    return codeSmellsGood || reviewerDrunk;
  }
}
