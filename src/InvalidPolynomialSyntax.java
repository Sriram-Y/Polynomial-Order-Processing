/*
**************************************************************************************************
Author: Sriram Yadavalli
Date: 9/15/2021
Course: CMSC 350 6380
Assignment: Project 2
**************************************************************************************************
Description:
This class contains two overloaded implementations of a method named checkSorted, which 
determines whether a List object is in strictly ascending order. Displays error in a JOptionPane.
**************************************************************************************************
*/

import javax.swing.JOptionPane;

public class InvalidPolynomialSyntax extends RuntimeException{

    public InvalidPolynomialSyntax() {
    }

    public InvalidPolynomialSyntax(String message) {
        super(message);
        JOptionPane.showMessageDialog(null, message);
    }

    public InvalidPolynomialSyntax(Throwable cause) {
        super(cause);
        JOptionPane.showMessageDialog(null, cause);
    }

    public InvalidPolynomialSyntax(String message, Throwable cause) {
        super(message, cause);
        JOptionPane.showMessageDialog(null, message);
    }

    public InvalidPolynomialSyntax(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        JOptionPane.showMessageDialog(null, message);
    }
}
