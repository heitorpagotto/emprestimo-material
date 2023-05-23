import br.edu.unifaj.emprestimo.menus.FirstMenu;

import java.util.Scanner;

public class Main {
    private static int _isRunning = 1;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FirstMenu menu = new FirstMenu(sc);

        while (_isRunning == 1) {
            _isRunning = menu.generateMainMenu();
        }

        System.out.println("\n\nFinalizando Sistema...");
    }
}