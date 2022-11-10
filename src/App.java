import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.opencsv.CSVWriter;

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter path of the input file below:");
        String inPath = sc.nextLine();
        // String inPath = "C:/Users/Kejariwal/Desktop/BlueOptima_Project/logs.txt";

        Path path = Paths.get(inPath);

        if (Files.notExists(path)) {
            System.out.println("ERROR: Invalid Path or file does not exists");
            System.exit(0);
        }

        String outPath = "./analyzedLogs.csv";

        // System.out.println("Enter Output file name: (analyzedLogs)");
        // String op = sc.nextLine();
        // if (op != "")
        // outPath = op;
        // System.out.println("Path:" + op);
        // Path oPath = Paths.get(outPath);
        // if (Files.exists(oPath)) {
        // System.out.println("Output File already exists: Overriding file content...");
        // }

        // first create file object for file placed at location
        // specified by filepath
        File file = new File(outPath);
        CSVWriter csvWriter = null;
        FileWriter outputfile = null;
        try {
            // create FileWriter object with file as parameter
            outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            csvWriter = new CSVWriter(outputfile);

            // adding header to csv
            csvWriter.writeNext(LogData.header());

            parseLogsToCSV(inPath, csvWriter);
            System.out.println("File parsed and saved in as " + outPath);

            // closing writer connection
            if (csvWriter != null)
                csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sc.close();

        }

    }

    /**
     * It will iterate through all the lines in the file
     * allowing for processing of each line
     * without keeping references to them
     * and in conclusion, without keeping them in memory
     * 
     * @param path
     * @throws IOException
     * 
     */
    public static void parseLogsToCSV(String path, CSVWriter writer) throws IOException {
        FileInputStream inputStream = null;
        Scanner sc = null;
        ExecutorService service = Executors.newFixedThreadPool(10);
        long dataCnt = 0;
        try {

            // ArrayList<String> list = new ArrayList<>();
            /// reads file
            inputStream = new FileInputStream(path);

            /// Gets data from file line by line
            sc = new Scanner(inputStream, "UTF-8");

            /// left data will just take the left over data from logs last line
            /// wherever `!Request-Body=` ends it takes data after that and will add it
            /// infront of next log statment
            String leftData = "";

            System.out.println("\nLoading & Processing Data...\n");

            while (sc.hasNextLine()) {
                StringBuffer log = new StringBuffer(leftData);
                String s = "";
                do {
                    s = sc.nextLine();
                    log.append(s.trim());
                    if (!sc.hasNextLine())
                        break;

                } while (!log.toString().trim().contains("!Request-Body="));

                final String[] temp = log.toString().split("!Request-Body=");

                if (temp.length == 2)
                    leftData = temp[1].trim();

                // now submit our jobs
                service.submit(new Runnable() {
                    public void run() {
                        LogData l = LogGetter.getLogDetails(temp[0]);

                        // System.out.println(l);
                        // list.add(l.toString());

                        // adding data to csv file
                        writer.writeNext(l.getDataList());
                    }
                });

                dataCnt++;
            }
            // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }

            // when no more to submit, call shutdown, submitted jobs will continue to run
            service.shutdown();
            // now wait for the jobs to finish
            service.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

            System.out.println("--------------------------------------");
            System.out.println("Total no. of data: " + dataCnt);
            System.out.println("--------------------------------------");

        } catch (FileNotFoundException e) {
            System.err.println(e.toString());
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }
    }
}
