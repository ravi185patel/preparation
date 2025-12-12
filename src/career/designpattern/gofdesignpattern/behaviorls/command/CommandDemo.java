package career.designpattern.gofdesignpattern.behaviorls.command;

/*
✔ Spring Boot usage

Runnable, Callable (thread pools)
Quartz scheduler jobs
Spring Batch steps

 */

interface Command{
    void execute();
}

class OpenFileCommand implements Command{
    @Override
    public void execute() {
        System.out.println("open file command");
    }
}

class CloseFileCommand implements Command{
    @Override
    public void execute() {
        System.out.println("closed file command");
    }
}

class WriteFileCommand implements Command{
    @Override
    public void execute() {
        System.out.println("write file command");
    }
}

class MyFile implements Command {

    Command closeFileCommand;
    Command openFileCommand;
    Command writeFileCommand;

    public MyFile() {
        closeFileCommand = new CloseFileCommand();
        openFileCommand = new OpenFileCommand();
        writeFileCommand = new WriteFileCommand();
    }

    @Override
    public void execute() {
        this.openFileCommand.execute();
        this.writeFileCommand.execute();
        this.closeFileCommand.execute();
    }
}

public class CommandDemo {

    public static void main(String[] args) {

        MyFile myFile = new MyFile();
        myFile.execute();

    }
}
