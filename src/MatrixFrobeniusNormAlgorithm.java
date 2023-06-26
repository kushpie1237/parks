import java.util.Random;
import java.util.Scanner;
import parcs.*


public class MatrixFrobeniusNormAlgorithm implements AM {

    @Override
    public void run(AMInfo info) {
        NodeInfo[] nodes = new NodeInfo[]{
                new NodeInfo("10.164.0.7", 8888),
                new NodeInfo("10.164.0.6", 8888)
        };

        // Запрос размерности матрицы
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите размерность матрицы (формат: 'строки столбцы'):");
        String[] sizeStr = scanner.nextLine().split(" ");
        int[] size = new int[]{ Integer.parseInt(sizeStr[0]), Integer.parseInt(sizeStr[1]) };

        int[][] a = new int[size[0]][size[1]];
        double norm = 0.0;

        Random rnd = new Random();

        // Заполнение матрицы случайными числами
        for (int i = 0; i < size[0]; i++) {
            for (int j = 0; j < size[1]; j++) {
                a[i][j] = rnd.nextInt(10);
                norm += Math.pow(a[i][j], 2);
            }
        }

        norm = Math.sqrt(norm);

        // Создание связи с хост-сервером
        try {
            Communicator comm = new Communicator(nodes);
            Point point = comm.createPoint();

            // Распараллеливание вычислений на демонах
            int numNodes = nodes.length;

            long startTime = System.currentTimeMillis();
            for (int i = 0; i < numNodes; i++) {
                Point node = new Point();
                point.send(node);
                AMInfo nodeInfo = node.execute(new MatrixFrobeniusNormTask(serializeSize(size)));
                nodeInfo.waitFor();
                double nodeNorm = nodeInfo.getReturnValue(Double.class);
                norm += nodeNorm;
            }

            norm = Math.sqrt(norm);
            long endTime = System.currentTimeMillis();

            // Вывод результата на консоль
            System.out.println("Норма Фробениуса матрицы: " + norm);
            System.out.println("Время выполнения: " + (endTime - startTime) + " мс");

            point.delete();
            comm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        scanner.close();
    }

    private String serializeSize(int[] size) {
        return size[0] + "," + size[1];
    }
}
