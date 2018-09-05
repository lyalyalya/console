package concole;

import java.util.List;

public interface Command {
    String getDescription();

    void execute(List<String> args);
}
