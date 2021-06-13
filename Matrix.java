package processor;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

class Matrix {

    int rowDim;
    int colDim;
    double[][] values = new double[rowDim][colDim];

    public Matrix(int rowDim, int colDim) {
        this.rowDim = rowDim;
        this.colDim = colDim;
        this.values = new double[rowDim][colDim];
    }

    public static Matrix zeroMatrix(int rowDim, int colDim) {
        Matrix zeroMatrix = new Matrix(rowDim,colDim);
        for(int i = 0; i < rowDim; i++) {
            for(int j = 0; j < colDim; j++) {
                zeroMatrix.values[i][j] = 0;
            }
        }
        return zeroMatrix;
    }

    public static Matrix add(Matrix mat1, Matrix mat2) {
        Matrix added = new Matrix(mat1.rowDim, mat1.colDim);
        if (mat1.rowDim != mat2.rowDim  || mat1.colDim  != mat2.colDim ) {
            out.println("Tried to add matrices of different dimensions; Return to start.");
        } else {
            for (int i = 0; i < mat1.rowDim; i++) {
                for (int j = 0; j < mat1.colDim; j++)
                    added.values[i][j] = mat1.values[i][j] + mat2.values[i][j];
            }
        }
        return added;
    }

    public static Matrix scale(Matrix mat, double scalar) {
        Matrix scaled = new Matrix(mat.rowDim, mat.colDim);

        for (int i = 0; i < mat.rowDim; i++) {
            for (int j = 0; j < mat.colDim; j++)
                scaled.values[i][j] = scalar * mat.values[i][j];
        }

        return scaled;

    }

    public static Matrix multiply(Matrix mat1, Matrix mat2) {
        Matrix multiplied = new Matrix(mat1.rowDim, mat2.colDim);
        if (mat1.colDim != mat2.rowDim) {
            out.println("Tried to multiply incompatible matrices; Returned zero matrix.");
            return zeroMatrix(mat1.rowDim, mat2.colDim);
        } else {
            for (int i = 0; i < mat1.rowDim; i++) {
                for (int j = 0; j < mat2.colDim; j++){
                    multiplied.values[i][j] = 0;
                    for(int k = 0; k < mat1.colDim; k++ ){
                        multiplied.values[i][j] += mat1.values[i][k] * mat2.values[k][j];
                    }
                }
            }
        return multiplied;
        }
    }

    public double determinant() {
        if(!this.isQuadratic()) {
            out.println("Tried to calculate determinant of nonquadratic matrix; Returned 0.");
            return 0;
        }
        if (this.rowDim == 1 && this.colDim == 1) {
            return this.values[0][0];
        } else {
            double summedValue = 0;
            for (int i = 0; i < this.rowDim; i++) {
                summedValue += Math.pow(-1, i) * this.values[i][0] * this.minor(i,0).determinant();
            }
            return summedValue;
        }
    }

    public boolean isQuadratic() {
        return this.colDim == this.rowDim ? true : false;
    }
    public void fill() {
        Scanner scanner = new Scanner(in);

        for (int i = 0; i < this.rowDim; i++) {
            for (int j = 0; j < this.colDim; j++) {
                this.values[i][j] = scanner.nextDouble();
            }
        }
    }

    public void print() {
        for (int i = 0; i < this.rowDim; i++) {
            for (int j = 0; j < this.colDim; j++) {
                out.print(this.values[i][j]);
                out.print(j == this.colDim - 1 ? "\n" : " ");
            }
        }
    }

    public double cofactor(int i, int j) {
        if (0 > i || i > this.rowDim - 1 || 0 > j || j > this.colDim - 1) {
            out.println("Called cofactor at invalid position; Returned 0.");
            return 0;
        } else {
            return Math.pow(-1, i + j) * this.minor(i, j).determinant();
        }
    }

    public Matrix transpose() {

        Matrix mat = new Matrix(this.colDim, this.rowDim);

        for (int i = 0; i < mat.colDim; i++) {
            for (int j = 0; j < mat.rowDim; j++) {
                mat.values[j][i] = this.values[i][j];
            }
        }
        return mat;
    }

    public Matrix minor(int i, int j) {
        Matrix min = new Matrix(this.rowDim - 1, this.colDim - 1);
        for (int x = 0; x < min.rowDim; x++)
            for (int y = 0; y < min.colDim; y++) {
                if (x < i && y < j) min.values[x][y] = this.values[x][y];
                if (x >= i && y < j) min.values[x][y] = this.values[x + 1][y];
                if (x < i && y >= j) min.values[x][y] = this.values[x][y + 1];
                if (x >= i && y >= j) min.values[x][y] = this.values[x + 1][y + 1];
            }
        return min;
    }


    public Matrix cofactorMatrix() {
        Matrix cofactorMatrix = new Matrix(this.rowDim, this.colDim);

        for (int i = 0; i < this.rowDim; i++) {
            for (int j = 0; j < this.colDim; j++) {
                cofactorMatrix.values[i][j] = cofactor(i,j);
            }
        }
        return cofactorMatrix;
    }

    public Matrix adjugate() {
        return this.cofactorMatrix().transpose();
    }

    public Matrix inverse() {
        return Matrix.scale(this.adjugate(), 1.0/this.determinant());
    }


}