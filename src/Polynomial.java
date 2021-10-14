/*
**************************************************************************************************
Author: Sriram Yadavalli
Date: 9/15/2021
Course: CMSC 350 6380
Assignment: Project 2
**************************************************************************************************
Description:
This class defines an Individual Polynomial in the file. Objects of type Polynomial are 
represented by a singly linked list. Each node of that linked list contains one term of the 
polynomial consisting of its coefficient and exponent. The class also contains methods 4 methods, 
a constructor, compareTo() to compare two polynomials, iterator() that produces an iterator that 
iterates across the terms of the polynomial, and toString() that convert a polynomial to a string.
**************************************************************************************************
*/

import java.util.Iterator;

public class Polynomial implements Comparable<Polynomial>, Iterable<Polynomial.Node>{
    public static class Node {
        private double coef;
        private int exp;
        private Node next;
        
        public Node(double coef, int exp) {
            this.coef = coef;
            this.exp = exp;
        }

        public double getCoef() {
            return coef;
        }

        public int getExp() {
            return exp;
        }
    }
    
    private Node head;

    public Polynomial(String filePolynomial)throws InvalidPolynomialSyntax{
        if(filePolynomial == null || filePolynomial.isEmpty()){
            throw new InvalidPolynomialSyntax("filePolynomial cannot be null or empty");
        }

        //split string into terms
        String[] polynomialArray = filePolynomial.split(" ");

        if (polynomialArray == null || polynomialArray.length == 0){
            throw new InvalidPolynomialSyntax("Polynomial cannot be null or empty");
        }
        if(polynomialArray.length % 2 != 0){
            throw new InvalidPolynomialSyntax("Invalid format Coefficient or Exponent missing");
        }

        Node prev = null;
        Node curr = null;

        for(int i = polynomialArray.length - 1; i >= 0; i-=2) {
            String cStr = polynomialArray[i-1];
            String eStr = polynomialArray[i];
            double c = 0;
            int e = 0;

            //Assigning coefficient and exponents w/ exception handling for invalid inputs
            try{
                c = Double.parseDouble(cStr);
            }
            catch (NumberFormatException nfe){
                throw new InvalidPolynomialSyntax("Invalid format Coefficient");
            }
            try{
                e = Integer.parseInt(eStr);
            }
            catch (NumberFormatException nfe){
                throw new InvalidPolynomialSyntax("Invalid format Exponent");
            }

            if(cStr != null && !cStr.isEmpty()){
                c = Double.parseDouble(cStr);
            }
            else{
                throw new InvalidPolynomialSyntax("Coefficient cannot be null or empty");
            }

            if(eStr != null && !eStr.isEmpty()){
                e = Integer.parseInt(eStr);
            }
            else{
                throw new InvalidPolynomialSyntax("Exponent cannot be null or empty");
            }

            curr = new Node(c, e);
            curr.next = prev;
            prev = curr;
        }
        head = curr;

        if(head == null){
            throw new InvalidPolynomialSyntax("Node cannot be null or empty");
        }

        Node n = head;
        int exp = n.exp;
        
        while((n = n.next) != null){
            if(exp <= n.exp){
                throw new InvalidPolynomialSyntax("Exponents are not in descending order");
            }
        }
    }

    public int compareTo(Polynomial other){
        //compare the two polynomials
        int x = 0;
        Node lhs = head;
        Node rhs = other.head;
        
        while((lhs = lhs.next) != null && (rhs = rhs.next) != null){
            x = Integer.compare(lhs.exp, rhs.exp);
            if(x == 0){
                x = Double.compare(lhs.coef, rhs.coef);
                if(x != 0){
                    break;
                }
            }
            else if(x < 0){
                break;
            }
        }
        return x;
    }

    private class PolynomialIterator implements Iterator<Node>{
        //iterator for the polynomial
        private Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Polynomial.Node next() {
            Node result = current;
            current = current.next;
            return result;
        }
    }
    
    @Override
    public Iterator<Node> iterator() {
        return new PolynomialIterator();
    }

    @Override
    public String toString() {
        //returns String representation of the polynomial
        Iterator<Node> iter = iterator();
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        int sbi = 0;
        int sbi1 = 0;

        while(iter.hasNext()){
            Node n = iter.next();
            if(!first && n.coef > 0){
                sb.append("+");
            }
            if(n.coef != 0){
                sb.append(n.coef).append("x^").append(n.exp);
                first = false;
            }
        }
        
        sbi = sb.indexOf("x^0");
        sbi1 = sb.indexOf("^1");

        if(sbi != -1){
            sb.delete(sbi, sbi + sb.length());
        }
        if(sbi1 != -1){
            sb.delete(sbi1, sbi1 + 2);
        }

        return sb.toString();
    }
}
