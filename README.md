# NNetworkCreator
A java library, wich helps to create simply neural networks with any numbers of layers, neurons, have sparse-activation option. Without installing. Pure java. Training with a teacher. Sigmoid activation.
Require jdk8. Network are in develop mode.

In the plans: 
1. Simply business of creating recurrent networks, with any level recursion from any to any neuron.
2. Opportunity to create disintegration networks, with dynamic synapses creation, and synapses disintegration, as like biological brain. (this mode automaticly constuct/destruct synapses between neurons, without the developer's intervention)

****************************************************
Entry.java - is example
****************************************************


Examples to use:

```java
public static void Main(String... args){

ArrayList<Integer> settings = new ArrayList<>();

	settings.add(3); // first layer, 3 neurons/inputs

	settings.add(9); // second layer, 9 neurons

	settings.add(2); //last layer, 2 neurons/outputs

Network network = new Network(settings);

double[] input1 = new double[3]; // fill some values, length must be equals with network inputs in first layer;
double[] neededReaction1 = new double[2]; // reaction of network, you expect

//...

network.setSparseActivation(true); 
network.setLearningRate(0.03);

for(int i=0; i<250000; i++){ //250000 - is ammount of total  epochs
	network.train(input1, neededReaction1);
}

//Now, our network can work

for(double result:network.usualWork(inputN)){
	System.out.println(result); 
}


}
```
Developer: I. Daghi