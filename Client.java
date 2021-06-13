package processor;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class Client {
    public static void choiceDialogue() {
        Scanner scanner = new Scanner(in);
        boolean exit = false;
        while (!exit) {
            out.println("1. Add matrices");
            out.println("2. Multiply matrix by a constant");
            out.println("3. Multiply matrices");
            out.println("4. Transpose matrix");
            out.println("5. Calculate a determinant");
            out.println("6. Inverse Matrix");
            out.println("0. Exit");

            out.print("Your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    matAddDialogue();
                    break;
                case 2:
                    matScaleDialogue();
                    break;
                case 3:
                    matMultDialogue();
                    break;
                case 4:
                    transDialogue();
                    break;
                case 5:
                    determinantDialogue();
                    break;
                case 6:
                    matInvertDialogue();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + choice);
            }
        }
    }

    public static void matAddDialogue() {

        out.println("Enter size of first matrix: > ");
        Matrix mat1 = makeMatrix();

        out.println("Enter first matrix: > ");
        mat1.fill();

        out.println("Enter size of second matrix: > ");
        Matrix mat2 = makeMatrix();

        out.println("Enter second matrix: > ");
        mat2.fill();

        out.println("The result is:  ");
        Matrix.add(mat1,mat2).print();
        }

    public static void matScaleDialogue() {
        Scanner scanner = new Scanner(in);

        out.println("Enter size of matrix: ");
        Matrix mat = makeMatrix();

        out.println("Enter matrix: ");
        mat.fill();

        out.println("Enter constant: ");
        double scalar = scanner.nextInt();

        out.println("The result is:  ");
        Matrix.scale(mat,scalar).print();
    }

    public static void matMultDialogue() {

        out.println("Enter size of first matrix: > ");
        Matrix mat1 = makeMatrix();

        out.println("Enter first matrix: > ");
        mat1.fill();

        out.println("Enter size of second matrix: > ");
        Matrix mat2 = makeMatrix();
        if (mat1.colDim != mat2.rowDim) {
            out.println("Tried to multiply matrices of different dimensions; Return to start.");
        } else {
            out.println("Enter second matrix: > ");
            mat2.fill();

            out.println("The result is:  ");
            Matrix.multiply(mat1,mat2).print();
        }
    }

    public static void transDialogue() {
        Scanner scanner = new Scanner(in);

        out.println("1. Main diagonal");
        out.println("2. Side diagonal");
        out.println("3. Vertical line");
        out.println("4. Horizontal line");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                transMainDialogue();
                break;
            case 2:
                transSideDialogue();
                break;
            case 3:
                transVertDialogue();
                break;
            case 4:
                transHorDialogue();
                break;
            default:
                out.println("Unexpected value: " + choice + "; Return to start.");
        }
    }

    public static void transMainDialogue() {

        out.println("Enter size of matrix: ");
        Matrix mat = makeMatrix();

        out.println("Enter matrix: ");
        mat.fill();

        Matrix transposed = mat.transpose();

        out.println("The result is: ");
        transposed.print();

    }

    public static void transSideDialogue() {
        out.println("Enter size of matrix: ");
        Matrix mat = makeMatrix();

        out.println("Enter matrix: ");
        mat.fill();

        out.println("The result is: ");
        for (int j = 0; j < mat.colDim; j++) {
            for (int i = 0; i < mat.rowDim; i++) {
                out.print(mat.values[mat.rowDim - 1 - i][mat.colDim - 1 - j]);
                out.print(i == mat.rowDim - 1 ? "\n" : " ");
            }
        }
    }

    public static void transVertDialogue() {
        out.println("Enter size of matrix: ");
        Matrix mat = makeMatrix();

        out.println("Enter matrix: ");
        mat.fill();

        out.println("The result is: ");
        for (int i = 0; i < mat.rowDim; i++) {
            for (int j = 0; j < mat.colDim; j++) {
                out.print(mat.values[i][mat.colDim - 1 - j]);
                out.print(j == mat.colDim - 1 ? "\n" : " ");
            }
        }
    }

    public static void transHorDialogue() {
        out.println("Enter size of matrix: ");
        Matrix mat = makeMatrix();

        out.println("Enter matrix: ");
        mat.fill();

        out.println("The result is: ");
        for (int i = 0; i < mat.rowDim; i++) {
            for (int j = 0; j < mat.colDim; j++) {
                out.print(mat.values[mat.rowDim - 1 - i][j]);
                out.print(j == mat.colDim - 1 ? "\n" : " ");
            }
        }
    }

    public static void determinantDialogue() {
        out.println("Enter size of matrix: ");
        Matrix mat = makeMatrix();

        if (mat.colDim != mat.rowDim) {
            out.println("This matrix is not quadratic; No determinant can be calculated.");
        } else {
            out.println("Enter matrix: ");
            mat.fill();

            out.println("The result is: ");
            out.println(mat.determinant());
        }
    }

    public static void matInvertDialogue() {
        Scanner scanner = new Scanner(in);

        out.println("Enter size of matrix: ");
        Matrix mat = makeMatrix();

        if(mat.isQuadratic()) {
            out.println("Enter matrix: ");
            mat.fill();

            if (mat.determinant() == 0) {
                System.out.println("This matrix is not invertible.");
            } else {
                out.println("The result is: ");
                mat.inverse().print();
            }
        } else {
            out.print("This matrix is not quadratic; no inverse can be calculated.");
        }
    }


    public static Matrix makeMatrix() {
        Scanner scanner = new Scanner(in);
        int rowDim = scanner.nextInt();
        int colDim = scanner.nextInt();

        Matrix matrix = new Matrix(rowDim, colDim);
        return matrix;
    }
}
