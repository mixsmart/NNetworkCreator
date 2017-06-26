package NNetworkCreator;
import java.util.ArrayList;
import java.util.Random;

import OptimumWeb.MainToolkit;


public class Controller {
MainToolkit tool = new MainToolkit();
	public ArrayList<NeuronLayer> getInitNeurons(ArrayList<Integer> layersNumInfo){
		ArrayList<NeuronLayer> neuronLayers = new ArrayList<>();
		for(int i=0; i<layersNumInfo.size(); i++){
			//Adding Neurons
			NeuronLayer nLayer = new NeuronLayer();
			for(int y=0; y<layersNumInfo.get(i); y++){
				Neuron neuron = new Neuron();
				nLayer.addNeuron(neuron);
				
				if(y==layersNumInfo.get(i)-1){
					if(i<layersNumInfo.size()-1){
					Neuron biasNeuron = new Neuron();
					biasNeuron.setBias();
					tool.k("BIAS SET UP... Layer: "+i);
					nLayer.addNeuron(biasNeuron);
					}
				}
				
			}
			neuronLayers.add(nLayer);
		}
		return neuronLayers;
	}
	
	
	public ArrayList<SynapticLayer> getStandartInitSynapses(ArrayList<NeuronLayer> neuronLayers){
		ArrayList<SynapticLayer> synapticLayers = new ArrayList<>();		
		for(int i=0; i<neuronLayers.size(); i++){
			if(i<(neuronLayers.size()-1)){
				NeuronLayer neuronLayerI = neuronLayers.get(i);
				NeuronLayer neuronLayerY = neuronLayers.get(i+1);
				SynapticLayer synLayer = new SynapticLayer();
					for(Neuron neuronI:neuronLayerI.getNeurons()){
						for(Neuron neuronY:neuronLayerY.getNeurons()){
							if(neuronY.isBias())continue;
							Synapse synapse = new Synapse(neuronI, neuronY);
							synLayer.addSynapse(synapse);
						}
					}
				synapticLayers.add(synLayer);	
			}
		}
		return synapticLayers;
	}
	
	
	
	public ArrayList<SynapticLayer> getDisintegrationInitSynapses(ArrayList<NeuronLayer> neuronLayers){
		ArrayList<SynapticLayer> synapticLayers = new ArrayList<>();		
		for(int i=0; i<neuronLayers.size(); i++){
			if(i<(neuronLayers.size()-1)){
				NeuronLayer neuronLayerI = neuronLayers.get(i);
				NeuronLayer neuronLayerY = neuronLayers.get(i+1);
				SynapticLayer synLayer = new SynapticLayer();
					for(Neuron neuronI:neuronLayerI.getNeurons()){
						for(Neuron neuronY:neuronLayerY.getNeurons()){
							Synapse synapse = new Synapse(neuronI, neuronY);
							synLayer.addSynapse(synapse);
						}
					}
				synapticLayers.add(synLayer);	
			}
		}
		return synapticLayers;
	}
	
	
	
	public NeuronLayer forward(double[] input, ArrayList<NeuronLayer> neuronLayers, ArrayList<SynapticLayer> synapticLayers){		
		for(int i=0; i<neuronLayers.size(); i++){ // послойно
			NeuronLayer nLayer = neuronLayers.get(i); 
			if(i==0){		
				for(int n=0; n<nLayer.getNeurons().size(); n++){ //понейронно
					Neuron neuron = nLayer.getNeuron(n);
					if(!neuron.isBias())neuron.forwardSignal(input[n]);
					else neuron.forwardSignal(1);
				}
			}else{
				SynapticLayer synLayer = synapticLayers.get(i-1);
				for(int n=0; n<nLayer.getNeurons().size(); n++){ //понейронно
					Neuron neuron = nLayer.getNeuron(n);
					ArrayList<Synapse> assignedSynapses = synLayer.getListByNeuron(neuron);
					if(!neuron.isBias())neuron.forwardSignal(assignedSynapses);
					else neuron.forwardSignal(1);
					
				}
			}	
		}
		return neuronLayers.get(neuronLayers.size()-1);
	}
	
	
	
	public NeuronLayer biologicalForward(double[] input, ArrayList<NeuronLayer> neuronLayers, ArrayList<SynapticLayer> synapticLayers){		
		for(int i=0; i<neuronLayers.size(); i++){ // послойно
			NeuronLayer nLayer = neuronLayers.get(i); 
			if(i==0){		
				for(int n=0; n<nLayer.getNeurons().size(); n++){ //понейронно
					nLayer.getNeuron(n).forwardSignal(input[n]);
				}
			}else{
				SynapticLayer synLayer = synapticLayers.get(i-1);
				for(int n=0; n<nLayer.getNeurons().size(); n++){ //понейронно
					Neuron neuron = nLayer.getNeuron(n);
					ArrayList<Synapse> assignedSynapses = synLayer.getListByNeuron(neuron);
					neuron.forwardBioSignal(assignedSynapses);
					
				}
			}	
		}
		return neuronLayers.get(neuronLayers.size()-1);
	}
	
	
	
	public NeuronLayer sparseForward(double[] input, ArrayList<NeuronLayer> neuronLayers, ArrayList<SynapticLayer> synapticLayers){
		//deactivate some rand neurons
		for(int i=1; i<neuronLayers.size()-1; i++){
			NeuronLayer nLayer = neuronLayers.get(i);
			Random randP = new Random();
			int exceptPos = randP.nextInt(nLayer.getNeurons().size());
			for(int n=0; n<nLayer.getNeurons().size(); n++){
				if(n==exceptPos)continue;
				Neuron neuron = nLayer.getNeuron(n);
				if(neuron.isBias())continue;
				neuron.tool.sleep(1);
				Random rand = new Random();
				neuron.enable(rand.nextBoolean());
			}
		}

		for(int i=0; i<neuronLayers.size(); i++){ // послойно
			NeuronLayer nLayer = neuronLayers.get(i); 
			if(i==0){		
				for(int n=0; n<nLayer.getNeurons().size(); n++){ //понейронно
					Neuron buferredNeuron = nLayer.getNeuron(n);
					buferredNeuron.forwardSignal(input[n]);
				}
			}else{
				SynapticLayer synLayer = synapticLayers.get(i-1);
				for(int n=0; n<nLayer.getNeurons().size(); n++){ //понейронно
					Neuron neuron = nLayer.getNeuron(n);
					if(!neuron.isEnabled())continue;
					ArrayList<Synapse> assignedSynapses = synLayer.getListByNeuron(neuron);
					neuron.forwardSignal(assignedSynapses);
					
				}
			}	
		}
		return neuronLayers.get(neuronLayers.size()-1);
	}
	
	
	public void backward(double[] goodSignal, ArrayList<NeuronLayer> neuronLayers, ArrayList<SynapticLayer> synapticLayers, double learningRate){
		
		
		
		for(int nli=neuronLayers.size()-1; nli>0; nli--){
			NeuronLayer nLayer = neuronLayers.get(nli);
			SynapticLayer synLayer = synapticLayers.get(nli-1);
			
			
			for(int nei=0; nei<nLayer.getNeurons().size(); nei++){
				Neuron neuron = nLayer.getNeuron(nei);
				if(!neuron.isEnabled())continue;
				for(Synapse synapse:synLayer.getListByNeuron(neuron)){
					if(nli==neuronLayers.size()-1)synapse.makeCorrection(goodSignal[nei], learningRate);
					else{
						SynapticLayer nextSynLayer = synapticLayers.get(nli);
						synapse.makeCorrection(nextSynLayer, learningRate);
					}
				}
			}
		}		
		//enable all neurons
		for(int i=1; i<neuronLayers.size()-1; i++){
			NeuronLayer nLayer = neuronLayers.get(i);
			for(Neuron neuron:nLayer.getNeurons()){
				neuron.enable(true);
			}
		}
		
	}
	
}
