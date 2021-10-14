/*
**************************************************************************************************
Author: Sriram Yadavalli
Date: 9/15/2021
Course: CMSC 350 6380
Assignment: Project 2
**************************************************************************************************
Description:
This program examines a file of polynomials and determines whether the polynomials in that file 
are in stricly ascending order using two different methods of comparison.

This is the main class that allows the user to select the input file from the default directory.
**************************************************************************************************
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import javax.swing.JFileChooser;

public class Main{
    public static void main(String[] args){
        JFileChooser chooser = new JFileChooser(".");
        String file = "";
        
        //comparator interface implemented in a lambda expression
        Comparator<Polynomial> comparator = (Polynomial lhs, Polynomial rhs) -> {
            //Iterator objects used to iterate through the polynomials
            Iterator<Polynomial.Node> lhsIter = lhs.iterator();
            Iterator<Polynomial.Node> rhsIter = rhs.iterator();

            boolean lhsBool = lhsIter.hasNext();
            boolean rhsBool = rhsIter.hasNext();

            int result = 0;

            //until the end of the polynomials is reached
            while(lhsBool && rhsBool){
                Polynomial.Node lhsNode = lhsIter.next();
                Polynomial.Node rhsNode = rhsIter.next();

                result = lhsNode.getExp() - rhsNode.getExp();

                if(result < 0){
                    return result;
                }

                lhsBool = lhsIter.hasNext();
                rhsBool = rhsIter.hasNext();

                if(lhsBool && !rhsBool){
                    return 1;
                }
                if(!lhsBool && rhsBool){
                    return -1;
                }
            }

            return result;
        };

        //open file chooser
        int returnVal = chooser.showOpenDialog(null);

        if(returnVal == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile().getAbsolutePath();
            try(BufferedReader br = new BufferedReader(new FileReader(file))){
                ArrayList<Polynomial> polynomials = new ArrayList<Polynomial>();
                String line = null;

                //read each line of the file and add to the arraylist
                while((line = br.readLine()) != null){
                    Polynomial polynomial = new Polynomial(line);
                    polynomials.add(polynomial);
                    System.out.println(polynomial);
                }

                if(polynomials.size() == 0){
                    throw new InvalidPolynomialSyntax("Polynomials cannot be empty");
                }

                boolean strongFailed = false;
                boolean weakFailed = false;
                Polynomial prev = null;

                for (Polynomial curr : polynomials) {
                    if(prev == null){
                        prev = curr;
                    }
                    else{
                        if(!strongFailed && prev.compareTo(curr) > 0){
                            strongFailed = true;
                        }
                        if(!weakFailed && comparator.compare(prev, curr) > 0){
                            weakFailed = true;
                        }
                    }
                    if(strongFailed && weakFailed){
                        break;
                    }
                }

                //print results
                if(strongFailed){
                    System.out.println("Strong Order: Fail");
                }
                else{
                    System.out.println("Strong Order: Pass");
                }

                if(weakFailed){
                    System.out.println("Weak Order: Fail");
                }
                else{
                    System.out.println("Weak Order: Pass");
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
