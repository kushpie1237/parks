import parcs.*;
import java.util.Random;

public class MatrixFrobeniusNormTask implements AM {

    public void run(AMInfo info) {
        int[] size = (int[]) info.parent.readObject();
        int[][] a = (int[][]) info.parent.readObject();
        double norm = 0.0;

        // Обчислення норми Фробеніуса
        for (int i = 0; i < size[0]; i++) {
            for (int j = 0; j < size[1]; j++) {
                norm += Math.pow(a[i][j], 2);
            }
        }

        norm = Math.sqrt(norm);

        // Повернення результату
        info.parent.write(Double.toString(norm));
    }

}
