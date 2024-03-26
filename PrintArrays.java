import java.lang.reflect.Array;



public class PrintArrays {

    public static void printArray (Memory_Chaching.Options[] array_to_print){
        System.out.println("The array we are printing is: ");
        for(Memory_Chaching.Options print: array_to_print.values()){
            System.out.print(print);
        }
        System.out.println("\n__________________________________________________________________________");
    }//End PrintArray(Array[])



    public static void printArray (int[] array_to_print){
        System.out.println("The array we are printing is: ");
        for(int i = 0; i < array_to_print.length; i++){
            System.out.print(array_to_print[i] +", ");
        }
        System.out.println("\n__________________________________________________________________________");
    }//End PrintArray(Int[])
}// End Class
