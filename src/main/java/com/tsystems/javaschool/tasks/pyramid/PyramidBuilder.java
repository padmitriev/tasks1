package com.tsystems.javaschool.tasks.pyramid;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class PyramidBuilder {

    /**
     * Builds a pyramid with sorted values (with minumum value at the top line and maximum at the bottom,
     * from left to right). All vacant positions in the array are zeros.
     *
     * @param inputNumbers to be used in the pyramid
     * @return 2d array with pyramid inside
     * @throws {@link CannotBuildPyramidException} if the pyramid cannot be build with given input
     */
    public int[][] buildPyramid(List<Integer> inputNumbers) {
        // TODO : Implement your solution here

        Stack<Integer> inputStack = new Stack<>();

        int augment = 1;
        int n = 1;
        int row = 1;
        int col = 1;

//подбор размеров пирамиды в зависимости от inputNumbers
        while (augment<inputNumbers.size()){
            augment = augment+n+1;
            n++;
            row = row +1;
            col = col +2;
        }

        if (augment != inputNumbers.size() | inputNumbers.contains(null)){
            throw new CannotBuildPyramidException();
        }

        for(int i=0;i<inputNumbers.size();i++){
            for(int j=i+1;j<inputNumbers.size();j++){
                if(inputNumbers.get(i).equals(inputNumbers.get(j))){
                    throw new CannotBuildPyramidException();
                }
            }
        }

//подготовка стека c числами
        Collections.sort(inputNumbers);

        for (Integer inputNumber : inputNumbers) {
            inputStack.push(inputNumber);
        }

        int[][] field = new int[row][col];

//заполнение пирамиды нулями
        for (int i=0; i<field.length; i++){
            for (int j=0; j<field[0].length; j++){
                field[i][j] = 0;
            }
        }

//Заполнение нижнего ряда числами
        for (int i=0; i<field[0].length; i++){
            field[field.length-1][field[0].length-1-i] = inputStack.peek();
            inputStack.pop();
            i++;
        }
//заполнение остальной пирамиды числами
        for (int k=0; k<(field.length-1); k++) {
            for (int i = (field[0].length - 1); i > 1 ; i--) {
                if (field[field.length-k-1][i] != 0 && field[field.length-1-k][i-2] != 0) {
                    field[field.length-k-2][i-1] = inputStack.peek();
                    inputStack.pop();
                }
            }
        }

        return field;
    }
}
