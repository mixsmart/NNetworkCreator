package NNetworkCreator;

import org.apache.commons.math3.analysis.function.Sigmoid;

import java.util.List;


public class Neuron {
    private double outputSignal = 0.0;
    private Sigmoid sigmoid = new Sigmoid();
    private boolean enabled = true;
    private boolean isBias = false;

    public boolean isBias() {
        return isBias;
    }

    public void setBias() {
        isBias = true;
        outputSignal = 1;
    }

    public double forwardSignal(List<Synapse> assignedSynapses) {
        double x = getSum(assignedSynapses);
        this.outputSignal = sigmoid.value(x);
        return this.outputSignal;
    }

    public double forwardSignal(double inputSignal) {
        this.outputSignal = inputSignal;
        return this.outputSignal;
    }

    public void enable(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public double getOutputSignal() {
        return outputSignal;
    }

    private double getSum(List<Synapse> assignedSynapses) {
        double out = 0; //not bias
        for (Synapse synapse : assignedSynapses) {
            out += synapse.getIncomingNeuron().getOutputSignal() * synapse.getWeight();
        }
        return out;
    }
}
