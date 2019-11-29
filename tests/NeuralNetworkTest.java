import NNetworkCreator.Network;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static utils.Util.show;

public class NeuralNetworkTest {


    @Test
    public void testOneHiddenLayerSparseHighLearningRate() {
        //neural network settings
        double learningRate = 0.003;
        double epochs = 2_000_000;
        List<Integer> layerSettings = new ArrayList<>(Arrays.asList(2, 6, 1));
        Network network = new Network(layerSettings);
        network.setSparseActivation(true);
        network.setLearningRate(learningRate);

        //data set
        double[] learningDataSet1 = {1.0, 0.0};
        double[] learningDataSetResult1 = {1};
        double[] learningDataSet2 = {0.0, 1.0};
        double[] learningDataSetResult2 = {1};
        double[] learningDataSet3 = {1.0, 1.0};
        double[] learningDataSetResult3 = {0};
        double[] learningDataSet4 = {0.0, 0.0};
        double[] learningDataSetResult4 = {0};
        double[] testDataSet = {0.0, 0.75};

        //checkout initial network
        show("Random (not trained) network shows:");
        show(network.usualWork(learningDataSet1));
        show(network.usualWork(learningDataSet2));
        show(network.usualWork(learningDataSet3));
        show(network.usualWork(learningDataSet4));
        show(network.usualWork(testDataSet));

        show("\r\nTraining...");
        //training model
        long time = System.currentTimeMillis();
        for (int i = 0; i < 2_000_000; i++) {
            network.train(learningDataSet1, learningDataSetResult1);
            network.train(learningDataSet2, learningDataSetResult2);
            network.train(learningDataSet3, learningDataSetResult3);
            network.train(learningDataSet4, learningDataSetResult4);
        }
        time = System.currentTimeMillis() - time;
        show("Training is done. Elapsed time: "+time+" msec\r\n");

        double[] resultDataSet1 = network.usualWork(learningDataSet1);
        double[] resultDataSet2 = network.usualWork(learningDataSet2);
        double[] resultDataSet3 = network.usualWork(learningDataSet3);
        double[] resultDataSet4 = network.usualWork(learningDataSet4);
        show("Trained network shows:");
        show(resultDataSet1);
        show(resultDataSet2);
        show(resultDataSet3);
        show(resultDataSet4);

        assertTrue(resultDataSet1[0] >= 0.5);
        assertTrue(resultDataSet2[0] >= 0.5);
        assertTrue(resultDataSet3[0] < 0.5);
        assertTrue(resultDataSet4[0] < 0.5);

        show("\r\nTest array: ");
        double[] testResult = network.usualWork(testDataSet);
        show(testResult);
        assertTrue(testResult[0] >= 0.5);
    }

    @Test
    public void testTwoHiddenLayerSparseHighLearningRate() {
        //neural network settings
        double learningRate = 0.003;
        double epochs = 2_000_000;
        List<Integer> layerSettings = new ArrayList<>(Arrays.asList(2, 6, 6, 1));
        Network network = new Network(layerSettings);
        network.setSparseActivation(true);
        network.setLearningRate(learningRate);

        //data set
        double[] learningDataSet1 = {1.0, 0.0};
        double[] learningDataSetResult1 = {1};
        double[] learningDataSet2 = {0.0, 1.0};
        double[] learningDataSetResult2 = {1};
        double[] learningDataSet3 = {1.0, 1.0};
        double[] learningDataSetResult3 = {0};
        double[] learningDataSet4 = {0.0, 0.0};
        double[] learningDataSetResult4 = {0};
        double[] testDataSet = {0.0, 0.75};

        //checkout initial network
        show("Random (not trained) network shows:");
        show(network.usualWork(learningDataSet1));
        show(network.usualWork(learningDataSet2));
        show(network.usualWork(learningDataSet3));
        show(network.usualWork(learningDataSet4));
        show(network.usualWork(testDataSet));

        show("\r\nTraining...");
        //training model
        long time = System.currentTimeMillis();
        for (int i = 0; i < epochs; i++) {
            network.train(learningDataSet1, learningDataSetResult1);
            network.train(learningDataSet2, learningDataSetResult2);
            network.train(learningDataSet3, learningDataSetResult3);
            network.train(learningDataSet4, learningDataSetResult4);
        }
        time = System.currentTimeMillis() - time;
        show("Training is done. Elapsed time: "+time+" msec\r\n");

        double[] resultDataSet1 = network.usualWork(learningDataSet1);
        double[] resultDataSet2 = network.usualWork(learningDataSet2);
        double[] resultDataSet3 = network.usualWork(learningDataSet3);
        double[] resultDataSet4 = network.usualWork(learningDataSet4);
        show("Trained network shows:");
        show(resultDataSet1);
        show(resultDataSet2);
        show(resultDataSet3);
        show(resultDataSet4);

        assertTrue(resultDataSet1[0] >= 0.5);
        assertTrue(resultDataSet2[0] >= 0.5);
        assertTrue(resultDataSet3[0] < 0.5);
        assertTrue(resultDataSet4[0] < 0.5);

        show("\r\nTest array: ");
        double[] testResult = network.usualWork(testDataSet);
        show(testResult);
        assertTrue(testResult[0] >= 0.5);
    }

    @Test
    public void testTwoHiddenLayerHighLearningRate() {
        //neural network settings
        double learningRate = 0.003;
        double epochs = 2_000_000;
        List<Integer> layerSettings = new ArrayList<>(Arrays.asList(2, 6, 6, 1));
        Network network = new Network(layerSettings);
        network.setSparseActivation(false);
        network.setLearningRate(learningRate);

        //data set
        double[] learningDataSet1 = {1.0, 0.0};
        double[] learningDataSetResult1 = {1};
        double[] learningDataSet2 = {0.0, 1.0};
        double[] learningDataSetResult2 = {1};
        double[] learningDataSet3 = {1.0, 1.0};
        double[] learningDataSetResult3 = {0};
        double[] learningDataSet4 = {0.0, 0.0};
        double[] learningDataSetResult4 = {0};
        double[] testDataSet = {0.0, 0.75};

        //checkout initial network
        show("Random (not trained) network shows:");
        show(network.usualWork(learningDataSet1));
        show(network.usualWork(learningDataSet2));
        show(network.usualWork(learningDataSet3));
        show(network.usualWork(learningDataSet4));
        show(network.usualWork(testDataSet));

        show("\r\nTraining...");
        //training model
        long time = System.currentTimeMillis();
        for (int i = 0; i < epochs; i++) {
            network.train(learningDataSet1, learningDataSetResult1);
            network.train(learningDataSet2, learningDataSetResult2);
            network.train(learningDataSet3, learningDataSetResult3);
            network.train(learningDataSet4, learningDataSetResult4);
        }
        time = System.currentTimeMillis() - time;
        show("Training is done. Elapsed time: "+time+" msec\r\n");

        double[] resultDataSet1 = network.usualWork(learningDataSet1);
        double[] resultDataSet2 = network.usualWork(learningDataSet2);
        double[] resultDataSet3 = network.usualWork(learningDataSet3);
        double[] resultDataSet4 = network.usualWork(learningDataSet4);
        show("Trained network shows:");
        show(resultDataSet1);
        show(resultDataSet2);
        show(resultDataSet3);
        show(resultDataSet4);

        assertTrue(resultDataSet1[0] >= 0.5);
        assertTrue(resultDataSet2[0] >= 0.5);
        assertTrue(resultDataSet3[0] < 0.5);
        assertTrue(resultDataSet4[0] < 0.5);

        show("\r\nTest array: ");
        double[] testResult = network.usualWork(testDataSet);
        show(testResult);
        assertTrue(testResult[0] >= 0.5);
    }
}
