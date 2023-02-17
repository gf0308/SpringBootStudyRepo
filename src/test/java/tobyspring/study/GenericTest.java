package tobyspring.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class GenericTest {

    @Test
    void test() {
        // 인스턴스화해서 쓸 클래스가 필드로 어떠한 오브젝트 타입의 필드를 두고 있을 수 있다.
        // 위와 같이 내가 어떤 클래스를 만들어 쓸 때 클래스 멤버필드를 어떤 '특정한 객체 타입'으로 두고 쓰고자 할 때,
        // 이를 가능하게 제약하고 구현해주는 것이 자바 1.5 이후부터 나온 Generic 기능이다.

        // [Test1] Generic 사용 클래스 객체 만들어 사용 테스트
//        MyClass<LandAnimal> landAnimalMyClass = new MyClass<>();
//        landAnimalMyClass.setElement(new Dog());
//        landAnimalMyClass.setElement(new Cat());
//        landAnimalMyClass.setElement(new Sparrow());
//        landAnimalMyClass.getElement().crying();

        // [Test2]
        ElementList<LandAnimal> elements = new ElementList<>();
        elements.add(new Dog());
        elements.add(new Cat());
//                elements.add(new Sparrow()); // 'LandAnimal' 타입 클래스가 아니기 때문에 적용할 수 없음.

        assertThat(elements.get(0).getCrySound()).isEqualTo("멍멍");
        assertThat(elements.get(1).getCrySound()).isEqualTo("냐옹냐옹");
        assertThat(elements.size()).isEqualTo(2);
        elements.remove(1); // idx
//        elements.remove()  // obj
        assertThat(elements.size()).isEqualTo(1);

    }

    @Test
    void test2() {
        AnimalList<Dog> animalList = new AnimalList<Dog>();
//        Dog dog1 = new Dog();
//        animalList.add(dog1);
        animalList.add(new Dog());
        assertThat(animalList.get(0).getBloodType()).isEqualTo("Warm");
        assertThat(animalList.get(0).getCrySound()).isEqualTo("멍멍");
        assertThat(animalList.size()).isEqualTo(1);
//        animalList.remove(dog1);
        animalList.remove(0);
        assertThat(animalList.size()).isEqualTo(0);
    }

    @DisplayName("제네릭의 와일드카드 ? 를 테스트하는 테스트")
    @Test
    void test3() {
        // 와일드카드 ? : 어떤 타입도 다 받을 수 있게 허용. '모든 타입' / '메서드'에서만 사용가능?
        // ex)
        // class MyClass<?>(); => "모든 타입도 내부객체로 투입가능하다."
        // class MyClass<? extends MyClassParent>(); => "MyClassParent 클래스와 이를 상속한 자식 클래스에 해당하는 모든 타입이 내부객체로 투입가능하다."
        // class MyClass<? super MyClassAncestor>(); => "MyClassAncestor 클래스와 MyClassAncestor의 조상클래스에 해당하는 모든 타입이 내부객체로 투입가능하다."

        AnimalList<Dog> dogAnimalList = new AnimalList<>();
        dogAnimalList.add(new Dog());
        assertThat(WildcardTestMethodClass.cryingAnimalList(dogAnimalList)).isEqualTo("멍멍"); // true
    }

}



class WildcardTestMethodClass {
    public static String cryingAnimalList(AnimalList<? extends LandAnimal> al) {
        LandAnimal la = al.get(0);
        return la.getCrySound();
    }
}



interface WarmBlood { String getBloodType(); }

class AnimalList<T extends LandAnimal & WarmBlood> {
    ArrayList<T> al = new ArrayList<T>();

    void add(T animal) { al.add(animal); }
    T get(int index) { return al.get(index); }
    boolean remove(T animal) { return al.remove(animal); }
    void remove(int index) { al.remove(index); }
    int size() { return al.size(); }
}


class ElementList<T> {
    // 필드
    private List<T> elementList = new ArrayList<T>();

    // 메서드 기능 : add, get, remove, size
    public void add(T element) {
        elementList.add(element);
    }

    public T get(int index) {
        return elementList.get(index);
    }

    public T remove(int index) {
        return elementList.remove(index);
    }
    public boolean remove(T element) {
        return elementList.remove(element);
    }

    public int size() {
        return elementList.size();
    }

}

class MyClass<T> {
    private T element;

    public T getElement() {
        return this.element;
    }

    public void setElement(T element) {
        this.element = element;
    }
}

class LandAnimal {
    public String getCrySound() {
        return "육지동물";
    }
}

class Cat extends LandAnimal {
    @Override
    public String getCrySound() {
        return "냐옹냐옹";
    }
}

class Dog extends LandAnimal implements WarmBlood {
    @Override
    public String getBloodType() {
        return "Warm";
    }

    @Override
    public String getCrySound() {
        return "멍멍";
    }
}

class Sparrow {
    public String getCrySound() {
        return "짹짹";
    }
}