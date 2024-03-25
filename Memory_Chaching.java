import java.util.Random;

public class Memory_Chaching {
    static int[] memory_cahche = new int[10];
    static int[] array_of_50 = new int[50];
    static final int _MAX_VALUE = 100;

    public static void main(String[] args) {
        populate_Cache();
        generate_random_numbers();
        cache(array_of_50, memory_cahche);
    }


    public static void populate_Cache(){
        for (int i = 0; i < memory_cahche.length; i++){
            memory_cahche[i] = -1;
        }
    }

    public static void generate_random_numbers(){
        //fill the array with random numbers
        Random randomNumber = new Random();
        for (int i = 0; i < array_of_50.length; i++){
            array_of_50[i] = randomNumber.nextInt(0, _MAX_VALUE);
        }
    }

    public static void cache(int[] array_of_commands, int[] memory_cahche){ 

        for (int i=0; i < array_of_commands.length; i++){
            add(array_of_commands[i], memory_cahche);
        }//End For
    }//End Cache()

    public static void add(int commandToAdd, int[] cacheArray){
        
        for(int x =0; x < cacheArray.length; x++){
            if (commandToAdd == cacheArray[x]){
                return;
            }else if (cacheArray[x] == -1){
                cacheArray[x] = commandToAdd;
                return;
            }
        }//end for loop
        replace(commandToAdd, findFurthestcommand(cacheArray));
    }

    public static void replace(int commandToAdd, int locationOfFurthestCommand){
        memory_cahche[locationOfFurthestCommand] = commandToAdd;
    }

    public static int findFurthestcommand(int[] memory_cahche){
        int _commandToRemove =-1;
        int distanceToNextCommand = 0;
        int tempdistance = 0;

        for (int iterateThroughTheCache = 0; iterateThroughTheCache < memory_cahche.length; iterateThroughTheCache++){
            int currentValue = memory_cahche[iterateThroughTheCache];
            for(int checkingdistance = 0; checkingdistance < array_of_50.length; checkingdistance++){
                if (currentValue == array_of_50[checkingdistance]){
                    tempdistance = checkingdistance-iterateThroughTheCache;
                }
            }
            if(distanceToNextCommand < tempdistance){
                _commandToRemove = memory_cahche[iterateThroughTheCache];
            }
        }



        return _commandToRemove;
    }

}
