/*
 —---------------------------------------------------------------------------------
 Laboratory  : 2
 File        : RandomMailingListMaker.java
 Author(s)   : Michaël Rohrer, Lucie Steiner
 Date        : 13.04.2016

 Goal        : This class is used to generate a random list of e-mail addresses,
               it's also used to pick a random e-mail in an e-mail list

 Remark(s)   :

 Compiler    : jdk1.8.0_60
 —---------------------------------------------------------------------------------
*/

package labo02.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomMailingListMaker {
    /**
     * This static method is used to generate a random list of e-mail addresses
     * @param list the full mailing list
     * @param sizeGroup the size of the group which will be the size of the new random list of e-mail addresses
     * @return the list that contains the random e-mail addresses of the group
     */
    public static List<String> makeList(List<String> list, int sizeGroup){

        int index;
        Random random = new Random();
        List<String> randomList = new ArrayList<>();

        for(int i = 0; i < sizeGroup; ++i){
            //Select a random index between 0 and the size of the list not include
            index = random.nextInt(list.size());

            //Generate a new random index while the list already contain the address at this index
            while(randomList.contains(list.get(index))){
                index = random.nextInt(list.size());
            }
            //Add the selected e-mail address to the random list
            randomList.add(list.get(index));
        }
        return randomList;
    }

    /**
     * This static method is used to pick a random e-mail in the list of e-mails
     * @param list the list of e-mails
     * @return the selected e-mail
     */
    public static String selectItem(List<String> list){

        int index;
        String item;
        Random random = new Random();

        index = random.nextInt(list.size());
        item = list.get(index);
        list.remove(index);

        return item;
    }
}
