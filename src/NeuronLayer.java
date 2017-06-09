import java.util.ArrayList;


public class NeuronLayer {
private ArrayList<Neuron> neurons = new ArrayList<>();
public double[] outputs;
public double[] calculatedOutputs;

public void addNeuron(Neuron neuron){
	neurons.add(neuron);
}
public void setNeurons(ArrayList<Neuron> neurons){
	this.neurons  = neurons;
}
public ArrayList<Neuron> getNeurons(){
	return neurons;
}
public Neuron getNeuron(int i){
	return neurons.get(i);
}


public double[] getAllOutputs(){
	outputs = new double[neurons.size()];
	for(int i=0; i<neurons.size(); i++){
		outputs[i] = neurons.get(i).getOutputSignal();
	}
	return outputs;
}






}
