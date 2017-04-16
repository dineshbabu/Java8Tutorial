package interfacedemo;
public class DragonMethodsAlreadyOverridenIgnoredDemo implements EggLayer, FireBreather {
    public static void main (String... args) {
        DragonMethodsAlreadyOverridenIgnoredDemo myApp = new DragonMethodsAlreadyOverridenIgnoredDemo();
        System.out.println(myApp.identifyMyself());
    }
}