public class PersonApp {
    public static void main(String[] args) {
      var person1 = new Person("Eko","Bunyu");
        

        System.out.println(person1.name);
        System.out.println(person1.address);
        System.out.println(person1.country);

        person1.sayHello("Fadhil");

        Person person2 = new Person("Hani","Bunyu");
        System.out.println(person2.name);
        System.out.println(person2.address);
        System.out.println(person2.country);

        Person person3;
        person3 = new Person("agus","Bunyu");
       person3.sayHello("Hani");

    }
}

