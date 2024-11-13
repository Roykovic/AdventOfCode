package nl.roykovic.aoc.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Year;
import java.util.Locale;

public class ClassGenerator {

    public static void main(String[] args) {
        args = new String[]{"2016","9", "formatCompression"};

        
        if (args.length < 2 || args.length > 3) {
            System.out.println("Usage: ClassGenerator [<year>] <day> <name>");
            return;
        }

        int year;
        if (args.length == 2) {
            year = Year.now().getValue();
        } else {
            year = Integer.parseInt(args[0]);
        }

        int day = Integer.parseInt(args[args.length - 2]);
        String name = StringUtils.capitalize(args[args.length - 1]);

        String classPackage = "nl.roykovic.aoc/_" + year + ".day" + day + "_" + name.toLowerCase(Locale.ROOT);

        // Generate Factory
        String class1Content = generateFactoryContent(year, day, name,name + "Factory");
        writeToFile("src/main/java/" + classPackage.replace(".", "/") + "/" + name + "Factory.java", class1Content);

        // Generate Test
        String class2Content = generateFactoryTestContent(year, day, name,name + "FactoryTest");
        writeToFile("src/test/java/" + classPackage.replace(".", "/") + "/" + name + "FactoryTest.java", class2Content);

        // Generate Test Input
        writeToFile("src/test/resources/" + year + "/" + name + "TestInput.txt", "");

        // Generate Actual Input
        writeToFile("src/main/resources/" + year + "/" + name + "Input.txt", "");

        System.out.println("Classes and text files created successfully!");
    }

    private static String generateFactoryContent(int year, int day, String name, String className) {
        return "package nl.roykovic.aoc._" + year + ".day" + day + "_" + name.toLowerCase()+";\n\n" +
                "import java.util.stream.Stream;\n"+
                "public class " + className + " {\n" +
                "    public int generate(Stream<String> input) {\n" +
                "        return 0;\n" +
                "    }\n" +
                "}\n";
    }

    private static String generateFactoryTestContent(int year, int day, String name, String className) {
        return "package nl.roykovic.aoc._" + year + ".day" + day + "_" + name.toLowerCase()+";\n\n" +
                "import nl.roykovic.aoc.utils.FileReaderService;\n" +
                "import org.junit.jupiter.params.ParameterizedTest;\n" +
                "import org.junit.jupiter.params.provider.CsvSource;\n" +
                "\n" +
                "import static org.junit.jupiter.api.Assertions.assertEquals;\n"+
                "public class " + className + " {\n" +
                    "    @ParameterizedTest\n" +
                    "    @CsvSource({\n" +
                    "            \""+name+"TestInput.txt,true,-1\",\n" +
                    "            \""+name+"Input.txt,false,-1\",\n" +
                    "    })\n" +
                    "    public void test(String filename, boolean test, int expected) {\n" +
                    "        var input = FileReaderService.streamLinesFromFile("+year+", filename, test);\n" +
                    "        var output = new "+name+"Factory().generate(input);\n" +
                    "\n" +
                    "        assertEquals(expected, output);\n" +
                    "    }\n"+
                "}\n";
    }





    private static void writeToFile(String fileName, String content) {
        try {
            File file = new File(fileName);
            file.getParentFile().mkdirs(); // Create parent directories if they don't exist

            try (FileWriter writer = new FileWriter(file)) {
                writer.write(content);
                System.out.println(fileName + " created successfully!");
            }

        } catch (IOException e) {
            System.out.println("Error creating " + fileName + ": " + e.getMessage());
        }
    }
}


