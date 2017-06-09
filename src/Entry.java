import java.util.ArrayList;


public class Entry {

	public static void main(String... args){
		ArrayList<Integer> settings = new ArrayList<>();
		settings.add(5);
		settings.add(21);
//		settings.add(5);
		settings.add(1);
		Network network = new Network(settings);
		
//		big_loop:
//			continue big_loop;
			
		network.setSparseActivation(true);
		network.setLearningRate(0.03);
		
		double[] mass = new double[5];
		mass[0] = 0.9;
		mass[1] = 0.8;
		mass[2] = 0.7;
		mass[3] = 0.6;
		mass[4] = 0.5;
		double[] good1 = new double[1];
		good1[0] = 0;
//		good1[1] = 0;
		

		
		
		double[] mass2 = new double[5];
		mass2[0] = 0.1;
		mass2[1] = 0.1;
		mass2[2] = 0.1;
		mass2[3] = 0.1;
		mass2[4] = 0.1;
		double[] good2 = new double[1];
		good2[0] = 1;
//		good2[1] = 1;

		for(int i=0; i<250000; i++){
			network.train(mass2, good2);
			network.train(mass, good1);

		}
	
		
		k(network.usualWork(mass));
		k(network.usualWork(mass2));
		k("\r\n");
		
		
		mass2[0] = 0.1;
		mass2[1] = 0.1;
		mass2[2] = 0.1;
		mass2[3] = 0.1;
		mass2[4] = 0.9;
		k(network.usualWork(mass2));
		
		
		
		mass2[0] = 0.1;
		mass2[1] = 0.1;
		mass2[2] = 0.1;
		mass2[3] = 0.9;
		mass2[4] = 0.1;
		k(network.usualWork(mass2));
		
		mass2[0] = 0.1;
		mass2[1] = 0.1;
		mass2[2] = 0.9;
		mass2[3] = 0.1;
		mass2[4] = 0.1;
		k(network.usualWork(mass2));
		
		mass2[0] = 0.9;
		mass2[1] = 0.1;
		mass2[2] = 0.1;
		mass2[3] = 0.1;
		mass2[4] = 0.0;
		k(network.usualWork(mass2));
		
		
	}
	
	public static void k(double[] input){
		for(double d:input){
			k(d);
		}
	}
	
	public static void k(Object o){
		System.out.println(o);
	}
}
