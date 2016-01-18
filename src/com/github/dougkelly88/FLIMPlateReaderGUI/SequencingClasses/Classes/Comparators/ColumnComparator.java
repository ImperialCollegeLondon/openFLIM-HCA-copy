/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.dougkelly88.FLIMPlateReaderGUI.SequencingClasses.Classes.Comparators;

import com.github.dougkelly88.FLIMPlateReaderGUI.SequencingClasses.Classes.SeqAcqSetup;
import java.util.Comparator;

/**
 *
 * @author dk1109
 */
public class ColumnComparator implements Comparator<SeqAcqSetup>{

    @Override
    public int compare(SeqAcqSetup o1, SeqAcqSetup o2) {
        String w1 = o1.getFOV().getWell();
        String w2 = o2.getFOV().getWell();

        String[] wellref1 = Utilitiesclass.splitWell(w1);
        String[] wellref2 = Utilitiesclass.splitWell(w2);

        // Something is very wrong if we don't juat have one letter block and one number block...
        if (2!=wellref1.length | 2!=wellref2.length){
            return 0; // Hopefully this just means nothing will change due to this comparator?
            // Although perhaps only true if all entries are messed up?
        }
        else{
            String col1=wellref1[1];
            String col2=wellref2[1];
            // Adding leading zeros for sort: http://stackoverflow.com/questions/4051887/how-to-format-a-java-string-with-leading-zero
            int padded_length=8; // Should be plenty for any sensibly-sized plate...          
            //String col1_pad = (col1,padded_length);
            String col1_pad = Utilitiesclass.padLeadingZeros(col1, padded_length);
            String col2_pad = Utilitiesclass.padLeadingZeros(col2, padded_length);
            if(0==o1.getSnaketype()){// Call 0 standard mode sorting...
                return col1_pad.compareTo(col2_pad);   
            }
            else if(1==o1.getSnaketype()){// Call 1 H-snake
                return col1_pad.compareTo(col2_pad);  // NO NEED TO ADD FLIP LOGIC FOR H-SNAKING IN COLS
            }
            else if(2==o1.getSnaketype()){// Call 2 V-snake
                //Determine if row is 'Even'
                int rownum = Integer.parseInt(wellref1[1]);
                
                if(0==rownum%2){
                    return col1_pad.compareTo(col2_pad);   
                }
                else{
                    //Reversing order of operands should invert sort direction
                    return col2_pad.compareTo(col1_pad);                       
                }
            }
            else{
                return 0;
            }
        }
    }
    
}