import java.util.Random;

import OptimumWeb.MainToolkit;


public class Synapse {
private Neuron incomingNeuron;
private Neuron outcomingNeuron;
private double weight = 0.0;
private double delta = 0;
public MainToolkit tool = new MainToolkit();

public Synapse(Neuron input, Neuron output){
	setIncomingNeuron(input);
	setOutcomingNeuron(output);
	tool.sleep(1);
	Random r = new Random(System.currentTimeMillis());
	weight = (double)(r.nextInt(100)-50)/100;//(0.5 - -0.5) //standart range
}


public void setIncomingNeuron(Neuron neuron){
	incomingNeuron = neuron;
}
public void setOutcomingNeuron(Neuron neuron){
	outcomingNeuron = neuron;
}
public Neuron getIncomingNeuron(){
	return incomingNeuron;
}
public Neuron getOutcomingNeuron(){
	return outcomingNeuron;
}
public double getWeight(){
	return weight;
}
public void setWeight(double weight){
	this.weight = weight;
}
public double getDelta(){
	return delta;
}
public void setDelta(double delta){
	this.delta = delta;
}




public void makeCorrection(double ideal, double learningRate){
//		delta = outcomingNeuron.getOutputSignal()*(1 - outcomingNeuron.getOutputSignal())*(ideal * outcomingNeuron.getOutputSignal());
		delta = ideal - outcomingNeuron.getOutputSignal();
//		System.out.println("Ideal: "+ideal+" Outcoming: "+outcomingNeuron.getOutputSignal()+" Delta: "+delta);
		weight = weight + learningRate * delta * incomingNeuron.getOutputSignal();
}
public void makeCorrection(SynapticLayer nextLayer, double learningRate){
		delta = outcomingNeuron.getOutputSignal() * (1-outcomingNeuron.getOutputSignal()) * getSumm(nextLayer);		
		weight = weight + learningRate * delta * incomingNeuron.getOutputSignal();
}



private double getSumm(SynapticLayer sLayer){
	double out = 0;
	for(Synapse synapse:sLayer.getSynapses()){
		out+= synapse.getWeight()*synapse.getDelta();
	}	
	return out;
}


}
