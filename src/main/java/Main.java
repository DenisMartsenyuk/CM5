import ru.lab.lab5.utils.ReaderInputData;
import ru.lab.lab5.utils.Solver;

public class Main {
    public static void main(String[] args) {
        ReaderInputData readerInputData = new ReaderInputData();
        readerInputData.read();
        Solver solver = new Solver();
        solver.solve(readerInputData.getInputData());
    }
}
