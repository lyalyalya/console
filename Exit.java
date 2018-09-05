package concole;

import java.util.List;

public class Exit implements Command {
    @Override
    public void execute(List<String> args) {
        System.out.println("Thanks for being with us!");
        System.exit(0);
    }

    @Override
    public String getDescription() {
        return "For exit type \"exit\"";
    }
}
