public abstract class Person {
    protected String name;
    protected int age;
    
    public String getName() { return name; }
    public int getAge() { return age; }


    public abstract void displayInfo();
    public abstract String toString();
}