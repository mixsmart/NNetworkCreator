import NNetworkCreator.Network;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;


public class Entry {

    public static void main(String... args) {


        ArrayList<Integer> settings = new ArrayList<>();
        settings.add(2);
        settings.add(6);
//		settings.add(3);
        settings.add(1);

        Network network = new Network(settings, false);
        network.setSparseActivation(true);


        double learningRate = 0.0003;
        for (int man = 0; man < 1; man++) {

            network.setLearningRate(learningRate);
            int lenght = 2;

            double[] learningDataSet1 = new double[lenght];
            learningDataSet1[0] = 1.0;
            learningDataSet1[1] = 0.0;
            double[] learningDataSetResult1 = new double[1];
            learningDataSetResult1[0] = 1;

            double[] learningDataSet2 = new double[lenght];
            learningDataSet2[0] = 0.0;
            learningDataSet2[1] = 1.0;
            double[] learningDataSetResult2 = new double[1];
            learningDataSetResult2[0] = 1;

            double[] learningDataSet3 = new double[lenght];
            learningDataSet3[0] = 1.0;
            learningDataSet3[1] = 1.0;
            double[] learningDataSetResult3 = new double[1];
            learningDataSetResult3[0] = 0;

            double[] learningDataSet4 = new double[lenght];
            learningDataSet4[0] = 0.0;
            learningDataSet4[1] = 0.0;
            double[] learningDataSetResult4 = new double[1];
            learningDataSetResult4[0] = 0;

            show(network.usualWork(learningDataSet1));
            show(network.usualWork(learningDataSet2));
            show(network.usualWork(learningDataSet3));
            show(network.usualWork(learningDataSet4));
            show("========================");

            for (int i = 0; i < 2000000; i++) {
                network.train(learningDataSet1, learningDataSetResult1);
                network.train(learningDataSet2, learningDataSetResult2);
                network.train(learningDataSet3, learningDataSetResult3);
                network.train(learningDataSet4, learningDataSetResult4);

            }


            double[] testDataSet = new double[lenght];
            testDataSet[0] = 0.0;
            testDataSet[1] = 0.75;

            show(network.usualWork(learningDataSet1));
            show(network.usualWork(learningDataSet2));
            show(network.usualWork(learningDataSet3));
            show(network.usualWork(learningDataSet4));
            show(network.usualWork(testDataSet));
        }
    }

    private static void show(double[] inputArray) {
        for (double input : inputArray) {
            double rounded = new BigDecimal(input).setScale(3, RoundingMode.UP).doubleValue();
            show(input + "\t" + rounded);
        }
    }

    private static void show(Object o) {
        System.out.println(o);
    }
}
