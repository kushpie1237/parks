import parcs.*;
import java.util.Random;

public class MatrixFrobeniusNormTask implements AM {

private String sizeStr;

public MatrixFrobeniusNormTask(String sizeStr) {
this.sizeStr = sizeStr;
}

public void run(AMInfo info) {
int[] size = deserializeSize(sizeStr);
int[][] a = new int[size[0]][size[1]];
double norm = 0.0;


java.util.Random rnd = new java.util.Random();

// Заполнение матрицы случайными числами
for (int i = 0; i < size[0]; i++) {
    for (int j = 0; j < size[1]; j++) {
        a[i][j] = rnd.nextInt(10);
        norm += Math.pow(a[i][j], 2);
    }
}

norm = Math.sqrt(norm);

// Обчислення норми Фробеніуса
for (int i = 0; i < size[0]; i++) {
    for (int j = 0; j < size[1]; j++) {
        norm += Math.pow(a[i][j], 2);
    }
}

norm = Math.sqrt(norm);

// Повернення результату
info.send(Double.toString(norm));
}

private int[] deserializeSize(String sizeStr) {
String[] sizeArr = sizeStr.split(",");
int[] size = new int[2];
size[0] = Integer.parseInt(sizeArr[0]);
size[1] = Integer.parseInt(sizeArr[1]);
return size;
}
}
