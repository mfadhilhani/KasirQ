public class Person {
    String name;
    String address;
    final String country = "Indonesia";

    // Konstruktor (Constructor)
    Person(String paraName, String paraAddress){
    name = paraName;
    address = paraAddress;
    }

    void sayHello(String paramNama){
        System.out.println("Hello " + paramNama + ", Nama Saya "+ name);
    }
}
