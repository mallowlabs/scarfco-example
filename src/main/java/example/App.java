package example;

import java.io.FileInputStream;
import java.io.IOException;

// Checkstyle: MissingJavadocType (no class-level Javadoc)
public class App {

    private String name;

    // Checkstyle: MissingJavadocMethod (no method-level Javadoc)
    public App(String name) {
        this.name = name;
    }

    // SpotBugs: HE_EQUALS_USE_HASHCODE (equals defined without hashCode)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof App)) return false;
        App other = (App) obj;
        return this.name.equals(other.name);
    }

    public void run() {
        // PMD: UnusedLocalVariable (unused is never read)
        int unused = 42;

        // Checkstyle: MagicNumber (literal 100)
        int limit = 100;

        // PMD: SystemPrintln (use of System.out.println)
        System.out.println("Hello, " + name);

        // PMD: EmptyCatchBlock (empty catch block)
        try (FileInputStream fis = new FileInputStream("dummy.txt")) {
            int b = fis.read();
        } catch (IOException e) {
            // do nothing
        }

        // Duplicated block for CPD detection (same logic exists in Greeter.java)
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < limit; i++) {
            sb.append(i);
            sb.append(", ");
            if (i % 10 == 0) {
                sb.append("\n");
            }
        }
        String result = sb.toString();
        System.out.println(result);
    }

    public static void main(String[] args) {
        App app = new App("World");
        app.run();
    }
}
