package target2023.LLD.LowLevelDesign.DesignPatterns.AdapterDesignPattern.Client;


import target2023.LLD.LowLevelDesign.DesignPatterns.AdapterDesignPattern.Adaptee.WeightMachineForBabies;
import target2023.LLD.LowLevelDesign.DesignPatterns.AdapterDesignPattern.Adapter.WeightMachineAdapter;
import target2023.LLD.LowLevelDesign.DesignPatterns.AdapterDesignPattern.Adapter.WeightMachineAdapterImpl;

public class Main {

    public static void main(String args[]){

        WeightMachineAdapter weightMachineAdapter = new WeightMachineAdapterImpl(new WeightMachineForBabies());
        System.out.println(weightMachineAdapter.getWeightInKg());
    }
}
