package career.lld.DesignPatterns.AdapterDesignPattern.Client;


import career.lld.DesignPatterns.AdapterDesignPattern.Adaptee.WeightMachineForBabies;
import career.lld.DesignPatterns.AdapterDesignPattern.Adapter.WeightMachineAdapter;
import career.lld.DesignPatterns.AdapterDesignPattern.Adapter.WeightMachineAdapterImpl;

public class Main {

    public static void main(String args[]){

        WeightMachineAdapter weightMachineAdapter = new WeightMachineAdapterImpl(new WeightMachineForBabies());
        System.out.println(weightMachineAdapter.getWeightInKg());
    }
}
