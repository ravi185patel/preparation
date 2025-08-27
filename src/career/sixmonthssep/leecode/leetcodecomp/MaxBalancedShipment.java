package career.sixmonthssep.leecode.leetcodecomp;

public class MaxBalancedShipment {
    public static void main(String[] args) {
        System.out.println(maxBalancedShipments(new int[]{2,5,1,4,3}));
        System.out.println(maxBalancedShipments(new int[]{4,4}));
        System.out.println(maxBalancedShipments(new int[]{2,3,4,5,6}));
        System.out.println(maxBalancedShipments(new int[]{2,3,4,5,6,1}));
        System.out.println(maxBalancedShipments(new int[]{5,4,3,2,1}));
    }
    public static int maxBalancedShipments(int[] weight) {
        int shipment=0;
        int maxWeight=0;
        for(int i=0;i<weight.length;i++){
            maxWeight=Math.max(maxWeight,weight[i]);
            if(maxWeight > weight[i]){
                shipment++;
                maxWeight = 0;
            }
        }
        return shipment;
    }
}
