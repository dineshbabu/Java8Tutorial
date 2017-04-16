package java8.streamsdemo;

import java.util.function.IntConsumer;

public class Averager implements IntConsumer{

    	int total = 0;
    	int count = 0;
		
    	@Override
		public void accept(int value) {
			total += value;
			count++;			
		}
    	
    	public double average() {
			return total / count;
		}

		public void combine(Averager other){
			count  = other.count;
    		total = other.total;
    	}
}