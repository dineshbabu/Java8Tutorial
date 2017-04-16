package interfacedemo;
public class PegasusInstanceMethodPrefereedOverInterfaceDefaultMethodsDemo extends Horse implements Flyer, Mythical {
    public static void main(String... args) {
        PegasusInstanceMethodPrefereedOverInterfaceDefaultMethodsDemo myApp = new PegasusInstanceMethodPrefereedOverInterfaceDefaultMethodsDemo();
        System.out.println(myApp.identifyMyself());
    }
}