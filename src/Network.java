import java.util.ArrayList;


public class Network {
	private ArrayList<Integer> layersNumInfo;
	
	private ArrayList<SynapticLayer> synapticLayers = new ArrayList<>();
	private ArrayList<NeuronLayer> neuronLayers = new ArrayList<>();
	
	//Settings
	private boolean sparseLearningActive = false;
	private boolean disintegrationActive = false;
//	private int epochs = 0;
	private double learningRate = 0.7;
	private int networkActivity = 0;
	private Controller controller = new Controller();
	
	
	public Network(ArrayList<Integer> layersNumInfo){
		this.layersNumInfo = layersNumInfo;
	
		neuronLayers = controller.getInitNeurons(layersNumInfo);
		synapticLayers = controller.getStandartInitSynapses(neuronLayers);
//		//ONLY WATCH
//		for(int i=0; i<synapticLayers.size(); i++){
//			System.out.println("Synaptic Layer: "+i+"; Synapses: "+synapticLayers.get(i).getSynapses().size());
//		}
	}
	
	
	
	
	public void setSparseActivation(boolean active){
		sparseLearningActive = active;
	}
	public void setDisintegrationActive(boolean active){
		disintegrationActive = active;
	}
//	public void setEpochs(int value){
//		epochs = value;
//	}
	public void setLearningRate(double value){
		learningRate = value;
	}
	public boolean hasSparse(){
		return sparseLearningActive;
	}
	public boolean hasDisintegration(){
		return disintegrationActive;
	}
//	public int getEpochs(){
//		return epochs;
//	}
	public double getLeraningRate(){
		return learningRate;
	}
	
	
	
	
	
	public double[] usualWork(double[] primaryValues){
		NeuronLayer exit;		
			exit = 	controller.forward(primaryValues, neuronLayers, synapticLayers);
//			exit = 	controller.sparseForward(primaryValues, neuronLayers, synapticLayers);
		showActive();
		double[] out = new double[exit.getNeurons().size()];
		for(int i=0; i<out.length; i++){
			out[i] = exit.getNeuron(i).getOutputSignal();
		}
		return out;
	}
	
	/**
	 * Need call this function in cycle: each iteration is one epoch
	 * @param primaryValues
	 * @param goodResult
	 */
	public void train(double[] primaryValues, double[] goodResult){
		trainfwd(primaryValues,goodResult);
		controller.backward(goodResult, neuronLayers, synapticLayers, learningRate);	

	}
	
	
	
	private double[] trainfwd(double[] primaryValues, double[] goodResult){
		NeuronLayer exit;		
		if(hasSparse())exit = 	controller.forward(primaryValues, neuronLayers, synapticLayers);
		else exit = 	controller.sparseForward(primaryValues, neuronLayers, synapticLayers);
		double[] out = new double[exit.getNeurons().size()];
		for(int i=0; i<out.length; i++){
			out[i] = exit.getNeuron(i).getOutputSignal();
		}
		return out;
	}
	
	
	private boolean aproximEquals(double[] a, double[] b, double ammountDifference){
		
		for(int i=0; i<a.length; i++){
		if(razn(a[i], b[i])>ammountDifference) return false;	
		}
		return true;
	}
	
	private  double razn(double a, double b){
		double c = a  - b;
		if(c<0)c = c*(-1);
		return c;
	}
	
	private void showActive(){
		int countActiveNeurons=0;
		for(NeuronLayer nLayer:neuronLayers){
			for(Neuron neuron:nLayer.getNeurons()){
				if(neuron.isEnabled())countActiveNeurons++;
			}
		}
		System.out.println("Total Active neurons: "+countActiveNeurons);
	}
	
}
