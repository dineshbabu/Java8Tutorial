package pathdemo;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileTest {
    public static void main(String[] args) {

//        if (args.length < 1) {
//            System.out.println("usage: FileTest file");
//            System.exit(-1);
//        }

        // Converts the input string to a Path object.
        Path inputPath = Paths.get("C:\\installations\\eclipse\\workspace\\Java8Tutorial\\src\\pathdemo\\Password.java");

        // Converts the input Path
        // to an absolute path.
        // Generally, this means prepending
        // the current working
        // directory.  If this example
        // were called like this:
        //     java FileTest foo
        // the getRoot and getParent methods
        // would return null
        // on the original "inputPath"
        // instance.  Invoking getRoot and
        // getParent on the "fullPath"
        // instance returns expected values.
        Path fullPath = inputPath.toAbsolutePath();
        System.out.println(inputPath.getNameCount());
        System.out.println(inputPath.getFileName());
        System.out.println(inputPath.getParent());
        System.out.println(inputPath.getRoot());
        System.out.println(inputPath.getFileSystem());



    }
}