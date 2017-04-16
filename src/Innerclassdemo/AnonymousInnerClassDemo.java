package Innerclassdemo;
abstract class AnonymousInner{
   public abstract void mymethod();
}

public class AnonymousInnerClassDemo {
   public static void main(String args[]){
      AnonymousInner inner = new AnonymousInner(){//instantiate and declare at the same time
         public void mymethod(){
            System.out.println("This is an example of anonymous inner class");    	  
         }	    
      };
      inner.mymethod();	
   }
}