# scarfco-example

A sample Maven project demonstrating how to use [scarfco](https://github.com/mallowlabs/scarfco).

Running `mvn site` generates analysis reports from SpotBugs, Checkstyle, PMD, and CPD.
Each tool's XML output can then be converted to Checkstyle or SARIF format using scarfco.

## Prerequisites

- Java 21+
- Maven 3.x
- [scarfco](https://github.com/mallowlabs/scarfco)

## Usage

### 1. Generate analysis reports

```bash
mvn site
```

The following XML files are generated in `target/`:

| File | Tool | Description |
|---|---|---|
| `target/spotbugs.xml` | SpotBugs | Bug pattern detection results |
| `target/pmd.xml` | PMD | Code quality issues |
| `target/cpd.xml` | CPD | Duplicated code detection |
| `target/checkstyle-result.xml` | Checkstyle | Coding convention violations |

### 2. Convert to Checkstyle format with scarfco

```bash
cat target/spotbugs.xml          | scarfco
cat target/pmd.xml               | scarfco
cat target/cpd.xml               | scarfco
cat target/checkstyle-result.xml | scarfco
```

### 3. Combine with reviewdog

```bash
cat target/spotbugs.xml          | scarfco | reviewdog -name=spotbugs   -f=checkstyle -reporter=github-pr-review
cat target/pmd.xml               | scarfco | reviewdog -name=pmd        -f=checkstyle -reporter=github-pr-review
cat target/cpd.xml               | scarfco | reviewdog -name=cpd        -f=checkstyle -reporter=github-pr-review
cat target/checkstyle-result.xml | scarfco | reviewdog -name=checkstyle -f=checkstyle -reporter=github-pr-review
```

### 4. Upload to GitHub Code Scanning (SARIF)

```bash
cat target/spotbugs.xml          | scarfco --format sarif > spotbugs.sarif
cat target/pmd.xml               | scarfco --format sarif > pmd.sarif
cat target/cpd.xml               | scarfco --format sarif > cpd.sarif
cat target/checkstyle-result.xml | scarfco --format sarif > checkstyle.sarif
```

Then upload with `github/codeql-action/upload-sarif@v4`. See [ci.yml](.github/workflows/ci.yml) for a working example.

## Intentional issues in this sample

### SpotBugs (`App.java`)
- `equals()` defined without `hashCode()` → `HE_EQUALS_USE_HASHCODE`

### PMD (`App.java`)
- Unused local variable → `UnusedLocalVariable`
- Empty catch block → `EmptyCatchBlock`

### CPD (`App.java` and `Greeter.java`)
- Same loop logic duplicated in both files → 10 lines of duplicated code detected

### Checkstyle (`App.java`, `Greeter.java`)
- Missing Javadoc → `MissingJavadocMethod`
- Magic numbers → `MagicNumber`
