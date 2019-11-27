package NNetworkCreator;

import java.util.ArrayList;


public class SynapticLayer {
    private ArrayList<Synapse> synapses = new ArrayList<>();

    public void addSynapse(Synapse synapse) {
        synapses.add(synapse);
    }

    public ArrayList<Synapse> getSynapses() {
        return synapses;
    }

    public ArrayList<Synapse> getListByNeuron(Neuron neuron) {
        ArrayList<Synapse> out = new ArrayList<>();
        for (Synapse synapse : synapses) {
            if (synapse.getIncomingNeuron().equals(neuron)) {
                if (synapse.getOutgoingNeuron().isEnabled()) out.add(synapse);
            }
            if (synapse.getOutgoingNeuron().equals(neuron)) {
                if (synapse.getIncomingNeuron().isEnabled()) out.add(synapse);
            }
        }
        return out;
    }


}
