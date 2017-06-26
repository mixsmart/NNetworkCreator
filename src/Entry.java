import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Random;

import org.apache.commons.math3.analysis.function.Sigmoid;

import NNetworkCreator.Network;
import OptimumWeb.MainToolkit;



public class Entry {

	static MainToolkit tool = new MainToolkit();

	public static void main(String... args){

	
		ArrayList<Integer> settings = new ArrayList<>();
		settings.add(2);
		settings.add(6);
//		settings.add(3);
		settings.add(1);
		
		Network network = new Network(settings, false);
		network.setSparseActivation(true);
		
		
		double learningRate = 0.0003;
		for(int man=0; man<1; man++){
//		tool.sleep(500);
//		learningRate = learningRate/10;
//		System.out.println("LearningRate: "+learningRate);
//		big_loop:
//			continue big_loop;
			
		
		network.setLearningRate(learningRate);
		int lenght = 2;
		
		double[] mass = new double[lenght];
		mass[0] = 1.0;
		mass[1] = 0.0;
		double[] good1 = new double[1];
		good1[0] = 1;
		
		double[] mass2 = new double[lenght];
		mass2[0] = 0.0;
		mass2[1] = 1.0;
		double[] good2 = new double[1];
		good2[0] = 1;
		
		double[] mass3 = new double[lenght];
		mass3[0] = 1.0;
		mass3[1] = 1.0;
		double[] good3 = new double[1];
		good3[0] = 0;
		
		double[] mass4 = new double[lenght];
		mass4[0] = 0.0;
		mass4[1] = 0.0;
		double[] good4 = new double[1];
		good4[0] = 0;
		
		k("\r\n");
		k(network.usualWork(mass));
//		k("\r\n")
		k(network.usualWork(mass2));
//		k("\r\n");
		k(network.usualWork(mass3));
//		k("\r\n");
		k(network.usualWork(mass4));
		k("\r\n");
		k("\r\n");
		k("========================");
	
		for(int i=0; i<2000000; i++){
			network.train(mass, good1);
			network.train(mass2, good2);
			network.train(mass3, good3);
			network.train(mass4, good4);

		}
	
		
		double[] mass5 = new double[lenght];
		mass5[0] = 0.0;
		mass5[1] = 0.75;
		
		k(network.usualWork(mass));
//		k("\r\n");
		k(network.usualWork(mass2));
//		k("\r\n");
		k(network.usualWork(mass3));
//		k("\r\n");
		k(network.usualWork(mass4));
		
		k(network.usualWork(mass5));
//		k("\r\n");
//		k("\r\n");
//		k("========================");
		
//		
//		mass2[0] = 0.5;
//		mass2[1] = 0.3;
//		k(network.usualWork(mass2));
//		
//		
//		mass2[0] = 1.0;
//		mass2[1] = 0.0;
//		k(network.usualWork(mass2));
		
		}
		

//		Resource.playStaticE("/home/mixsmart/workspace/Lybraries/Samples/Bankrupt-alarm.mp3");
		
	}
	
	public static void k(double[] input){
		for(double d:input){
			 double newDouble = new BigDecimal(d).setScale(3, RoundingMode.UP).doubleValue();
			k(d+"\t"+newDouble);
		}
	}
	
	public static void k(Object o){
		System.out.println(o);
	}
}
