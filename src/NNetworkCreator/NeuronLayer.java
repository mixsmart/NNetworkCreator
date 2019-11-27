package NNetworkCreator;

import java.util.ArrayList;
import java.util.List;


public class NeuronLayer {
    private List<Neuron> neurons = new ArrayList<>();

    public void addNeuron(Neuron neuron) {
        neurons.add(neuron);
    }

    public List<Neuron> getNeurons() {
        return neurons;
    }

    public Neuron getNeuron(int i) {
        return neurons.get(i);
    }
}
