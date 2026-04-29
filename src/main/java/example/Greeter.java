package example;

// Checkstyle: MissingJavadocType (no class-level Javadoc)
public class Greeter {

    private String target;

    // Checkstyle: MissingJavadocMethod (no method-level Javadoc)
    public Greeter(String target) {
        this.target = target;
    }

    public void greet() {
        // PMD: SystemPrintln (use of System.out.println)
        System.out.println("Greetings, " + target);

        // Duplicated block for CPD detection (same logic exists in App.java)
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            sb.append(i);
            sb.append(", ");
            if (i % 10 == 0) {
                sb.append("\n");
            }
        }
        String result = sb.toString();
        System.out.println(result);
    }
}
