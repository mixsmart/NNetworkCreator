package NNetworkCreator;

import OptimumWeb.MainToolkit;

import java.util.Random;


public class Synapse {
    private Neuron incomingNeuron;
    private Neuron outgoingNeuron;
    private double weight = 0.0;
    private double delta = 0;
    public MainToolkit tool = new MainToolkit();
    private Random rand = new Random();

    public Synapse(Neuron input, Neuron output) {
        setIncomingNeuron(input);
        setOutgoingNeuron(output);
        tool.sleep(1);
        weight = getRandomValueInRange();
        if (weight == 0) {
            weight = 0.1;
        }
    }


    public void setIncomingNeuron(Neuron neuron) {
        incomingNeuron = neuron;
    }

    public void setOutgoingNeuron(Neuron neuron) {
        outgoingNeuron = neuron;
    }

    public Neuron getIncomingNeuron() {
        return incomingNeuron;
    }

    public Neuron getOutgoingNeuron() {
        return outgoingNeuron;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getDelta() {
        return delta;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }

    public void makeCorrection(double ideal, double learningRate) {
        delta = ideal - outgoingNeuron.getOutputSignal();
        weight = weight + learningRate * delta * incomingNeuron.getOutputSignal();
    }

    public void makeCorrection(SynapticLayer nextLayer, double learningRate) {
        delta = outgoingNeuron.getOutputSignal() * (1 - outgoingNeuron.getOutputSignal()) * getSum(nextLayer);
        weight = weight + learningRate * delta * incomingNeuron.getOutputSignal();
    }

    private double getSum(SynapticLayer sLayer) {
        double out = 0;
        for (Synapse synapse : sLayer.getSynapses()) {
            out += synapse.getWeight() * synapse.getDelta();
        }
        return out;
    }

    /*
     * (0.5 - -0.5) //standard range
     */
    private double getRandomValueInRange() {
        return (double) (rand.nextInt(100) - 50) / 100;
    }

}
