package lab2;

import org.uncommons.watchmaker.framework.operators.AbstractCrossover;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyCrossover extends AbstractCrossover<double[]> {
    protected MyCrossover() {
        super(1);
    }

    private boolean contains(int [] arr, int v) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == v)
                return true;
        return false;
    }
    private int[] getCrossoverIndexes(Random random, int crossoverPointsNum, int dimension) {
        int[] indexes = new int[crossoverPointsNum];
        for (int i=0; i < crossoverPointsNum; i++) {
            int index_candidate = random.nextInt(dimension);
            if (! contains(indexes, index_candidate)) {
                indexes[i] = index_candidate;
            }
        }
        return indexes;
    }


    protected List<double[]> mate(double[] p1, double[] p2, int crossoverPointsNum, Random random, double alpha) {
        int dimension = p1.length;

        ArrayList children = new ArrayList();

        // ! Переписать с циклом без копипасты

        double[] child1  = new double[dimension];
        System.arraycopy(p1, 0, child1, 0, dimension);
        int[] crossoverIndexes1 = getCrossoverIndexes(random, crossoverPointsNum, dimension);
        // cn stands for crossover number
        for (int cn = 0; cn < crossoverIndexes1.length; cn++) {
            int crossoverIndex = crossoverIndexes1[cn];
            child1[crossoverIndex] = alpha * p2[crossoverIndex] + (1-alpha) * p1[crossoverIndex];
        }
        children.add(child1);


        double[] child2  = new double[dimension];
        System.arraycopy(p2, 0, child2, 0, dimension);
        int[] crossoverIndexes2 = getCrossoverIndexes(random, crossoverPointsNum, dimension);
        // cn stands for crossover number
        for (int cn = 0; cn < crossoverIndexes2.length; cn++) {
            int crossoverIndex = crossoverIndexes2[cn];
            child2[crossoverIndex] = alpha * p1[crossoverIndex] + (1-alpha) * p2[crossoverIndex];
        }
        children.add(child2);


        return children;
    }


    protected List<double[]> mate(double[] p1, double[] p2, int crossoverPointsNum, Random random) {
        return mate(p1, p2, crossoverPointsNum, random, 0.5D);
    }
}
