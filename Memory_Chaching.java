import java.util.Random;

public class Memory_Chaching {
    static int[] memory_cache = new int[10];
    static int[] array_of_50 = new int[50];
    static final int _MAX_VALUE = 20;
    static Options[] WhatWeDoneDid = new Options[50];
    static enum Options {NOT_ADDED ,ALREADY_PRESENT, ADDED_NEW, REPLACED }

    public static void main(String[] args) {
        populate_Cache();
        generate_random_numbers();
        PrintArrays.printArray(array_of_50);
        cache(array_of_50, memory_cache);

        for(int i = 0; i < WhatWeDoneDid.length; i++)
            System.out.print(WhatWeDoneDid[i] +", ");
        //PrintArrays.printArray(WhatWeDoneDid);
    }


    public static void populate_Cache(){
        for (int i = 0; i < memory_cache.length; i++){
            memory_cache[i] = -1;
        }
    }

    public static void generate_random_numbers(){
        //fill the array with random numbers
        Random randomNumber = new Random();
        for (int i = 0; i < array_of_50.length; i++){
            array_of_50[i] = randomNumber.nextInt(0, _MAX_VALUE);
        }
    }

    public static void cache(int[] array_of_commands, int[] memory_cache){ 

        for (int i=0; i < array_of_commands.length; i++){
            add(array_of_commands[i], memory_cache, i);
        }//End For
    }//End Cache()

    public static void add(int commandToAdd, int[] cacheArray, int location){
        
        for(int x =0; x < cacheArray.length; x++){
            if (commandToAdd == cacheArray[x]){
                WhatWeDoneDid[location] = Options.ALREADY_PRESENT;
                return;
            }else if (cacheArray[x] == -1){
                WhatWeDoneDid[location] = Options.ADDED_NEW;
                cacheArray[x] = commandToAdd;
                return;
            }
        }//end for loop
        try {
            replace(commandToAdd, findFurthestcommand(cacheArray, commandToAdd, location));
        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }

    public static void replace(int commandToAdd, int locationOfFurthestCommand){
        try {
            memory_cache[locationOfFurthestCommand] = commandToAdd;
        } catch (Exception e) {
           // e.printStackTrace();
            //System.err.println(commandToAdd);
            //System.err.println(locationOfFurthestCommand);
        }
        
    }

    public static int findFurthestcommand(int[] memory_cache, int commandToAdd, int location){
        int _commandToRemove =-1;
        int distanceToNextCommand = 50;
        for (int r =location+1; r<array_of_50.length;r++){
            if (array_of_50[r] == commandToAdd){
                distanceToNextCommand = r-location;
                break;
            }
        }

        int tempdistance = 0;

        for (int iterateThroughTheCache = 0; iterateThroughTheCache < memory_cache.length; iterateThroughTheCache++){
            int currentValue = memory_cache[iterateThroughTheCache];
            for(int checkingdistance = location+1; checkingdistance < array_of_50.length; checkingdistance++){
                if (currentValue == array_of_50[checkingdistance]){
                    tempdistance = checkingdistance-iterateThroughTheCache;
                }
            }
            if(distanceToNextCommand < tempdistance){
                _commandToRemove = iterateThroughTheCache;
                WhatWeDoneDid[location] = Options.REPLACED;
            }
        }

        if(_commandToRemove == -1)
            WhatWeDoneDid[location] = Options.NOT_ADDED;

        return _commandToRemove;
    }

}
