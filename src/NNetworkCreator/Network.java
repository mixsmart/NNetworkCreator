package NNetworkCreator;

import java.util.ArrayList;
import java.util.List;


public class Network {
    private List<Integer> layersNumInfo;

    private List<SynapticLayer> synapticLayers = new ArrayList<>();
    private List<NeuronLayer> neuronLayers = new ArrayList<>();

    //Settings
    private boolean sparseLearningActive = false;
    private double learningRate = 0.7;
    private int thresholdNetworkActivity = 0;
    private static Controller controller;


    public Network(List<Integer> layersNumInfo) {
        controller = new Controller();
        this.layersNumInfo = layersNumInfo;
        neuronLayers = controller.getInitNeurons(layersNumInfo);

        synapticLayers = controller.getStandardInitSynapses(neuronLayers);


    }

    public void setSparseActivation(boolean active) {
        sparseLearningActive = active;
    }

    public void setLearningRate(double value) {
        learningRate = value;
    }

    public boolean hasSparse() {
        return sparseLearningActive;
    }

    public double[] usualWork(double[] primaryValues) {
        NeuronLayer exit;
        exit = controller.forward(primaryValues, neuronLayers, synapticLayers);
        double[] out = new double[exit.getNeurons().size()];
        for (int i = 0; i < out.length; i++) {
            out[i] = exit.getNeuron(i).getOutputSignal();
        }
        return out;
    }

    /**
     * Need call this function in cycle: each iteration is one epoch
     *
     * @param primaryValues
     * @param goodResult
     */
    public void train(double[] primaryValues, double[] goodResult) {
        forward(primaryValues); //new results remains in output neurons
        controller.backward(goodResult, neuronLayers, synapticLayers, learningRate);

    }

    private double[] forward(double[] primaryValues) {
        NeuronLayer exit;
        if (!hasSparse()) exit = controller.forward(primaryValues, neuronLayers, synapticLayers);
        else exit = controller.sparseForward(primaryValues, neuronLayers, synapticLayers);
        double[] out = new double[exit.getNeurons().size()];
        for (int i = 0; i < out.length; i++) {
            out[i] = exit.getNeuron(i).getOutputSignal();
        }
        return out;
    }

    private double diff(double a, double b) {
        double c = a - b;
        if (c < 0) c = c * (-1);
        return c;
    }


}
