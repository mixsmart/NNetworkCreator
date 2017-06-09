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

public double forwardSignal(ArrayList<Synapse> assignedSynapses){
//	this.inputSignals = inputSignals;
//	if(enabled){
	this.outputSignal = sigmoid.value(getSumm(assignedSynapses));
	return this.outputSignal;
//	}
//	else return -1;
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
	double out = 1;
	for(Synapse synapse:assignedSynapses){
//		if(synapse.getIncomingNeuron().isEnabled()&&synapse.getOutcomingNeuron().isEnabled()){
			out+=synapse.getIncomingNeuron().getOutputSignal()*synapse.getWeight();
//		}
	}
	return out;
}

}
