/*import java.util.Arrays;

public class hola {
    public static void main(String[] args) {
        Matrix m1 = new Matrix(new double[][]{{1, 2}, {4, 5}});
        Matrix m2 = new Matrix(new double[][]{{6, 7}, {8, 9}});
        Matrix suma = m1.add(m2);

        System.out.println(suma);

    }
}



class Matrix {
    private double[][] mat;

    Matrix(double[][] mat) {
        this.mat = new double[mat.length][];
        for (int i = 0; i < mat.length; i++) {
            this.mat[i] = new double[mat[i].length];
            for (int j = 0; j < mat[i].length; j++) {
                this.mat[i][j] = mat[i][j];
            }
        }
    }

    Matrix add(Matrix mat) {
        double[][] res = new double[mat.mat.length][];
        for (int i = 0; i < mat.mat.length; i++) {
            res[i] = new double[mat.mat[i].length];
            for (int j = 0; j < mat.mat[i].length; j++) {
                res[i][j] = this.mat[i][j] + mat.mat[i][j];
            }
        }
    }


*/
