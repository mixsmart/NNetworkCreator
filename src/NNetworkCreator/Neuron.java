package NNetworkCreator;
import java.util.ArrayList;

import org.apache.commons.math3.analysis.function.Sigmoid;

import OptimumWeb.MainToolkit;


public class Neuron {
private double[] inputSignals;
private double inputSignal;
private double outputSignal=0.0;
private	Sigmoid sigmoid = new Sigmoid();
private boolean enabled = true;
public MainToolkit tool = new MainToolkit();
private boolean isBias = false;

public boolean isBias(){
	return isBias;
}
public void setBias(){
	isBias = true;
	outputSignal = 1;
	inputSignal = 1;
}

public double forwardSignal(ArrayList<Synapse> assignedSynapses){
//	this.inputSignals = inputSignals;
//	if(enabled){
	double x = getSumm(assignedSynapses);
   this.outputSignal = sigmoid.value(x);

	return this.outputSignal;

//	}
//	else return -1;
}

public double forwardBioSignal(ArrayList<Synapse> assignedSynapses){
	this.outputSignal = sigmoid.value(getBioSumm(assignedSynapses));
	return this.outputSignal;
}


public double forwardSignal(double inputSignal){
	this.inputSignal = inputSignal;
	this.outputSignal = inputSignal;
	return this.outputSignal;
}


public void enable(boolean enabled){
	this.enabled = enabled;
}
public boolean isEnabled(){
	return enabled;
}
public void setInputSignal(double[] value){
	inputSignals = value;
}
public void setOutputSignal(double value){
	outputSignal  = value;
}
public double[] getInputSignals(){
	return inputSignals;
}
public double getOutputSignal(){
	return outputSignal;
}
public double getInputSignal(){
	return inputSignal;
}






private double getSumm(ArrayList<Synapse> assignedSynapses){
	double out = 0; //not bias
	for(Synapse synapse:assignedSynapses){
			out+=synapse.getIncomingNeuron().getOutputSignal()*synapse.getWeight();
	}
	return out;
}



private double getBioSumm(ArrayList<Synapse> assignedSynapses){
	double out = 0;
	for(Synapse synapse:assignedSynapses){
			
		    out+=synapse.getIncomingNeuron().getOutputSignal()*synapse.getWeight();
		    
		    
		    double delta = synapse.getIncomingNeuron().getOutputSignal();
			synapse.setWeight(synapse.getWeight()+(synapse.getIncomingNeuron().getOutputSignal()*synapse.getOutcomingNeuron().getOutputSignal()*0.03));
			if(delta<0.5){
//				tool.k("delta: "+delta+" Weight "+synapse.getWeight());
				synapse.setWeight(synapse.getWeight()-sigmoid.value(delta));
//				tool.k("delta: "+delta+" Weight "+synapse.getWeight()+"\r\n");
			}
			if(delta>0.5) synapse.setWeight(synapse.getWeight()+sigmoid.value(delta));
			
	}
	return out;
}

}
