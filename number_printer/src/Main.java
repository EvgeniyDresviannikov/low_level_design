import com.example.number_printer.Printer;
import com.example.number_printer.PrinterType;
import com.example.number_printer.State;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        State state = new State(PrinterType.ODD);

        Printer printer1 = new Printer(state, PrinterType.ODD, PrinterType.EVEN, 2, 1, 50);
        Printer printer2 = new Printer(state, PrinterType.EVEN, PrinterType.ODD, 2, 2, 50);

        new Thread(printer1).start();
        new Thread(printer2).start();
    }
}