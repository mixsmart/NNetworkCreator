package NNetworkCreator;
import java.util.ArrayList;


public class SynapticLayer {
private ArrayList<Synapse> synapses = new ArrayList<>();



public void addSynapse(Synapse synapse){
	synapses.add(synapse);
}
public void setSynapses(ArrayList<Synapse> synapses){
	this.synapses = synapses;
}
public ArrayList<Synapse> getSynapses(){
	return synapses;
}
public Synapse getSynapse(int i){
	return synapses.get(i);
}




public ArrayList<Synapse> getListByNeuron(Neuron neuron){
	ArrayList<Synapse> out = new ArrayList<>();
	for(Synapse synapse:synapses){
		if(synapse.getIncomingNeuron().equals(neuron)){
			if(synapse.getOutcomingNeuron().isEnabled())out.add(synapse);
		}
		if(synapse.getOutcomingNeuron().equals(neuron)){
			if(synapse.getIncomingNeuron().isEnabled()) out.add(synapse);
		}
	}
	return out;
}
	
	
}
