package experiment_11_20;

import java.io.*;

public class StreamAndWriter {
    public static void main(String[] args) {

        String dataOutputStreamFile = "dataOutputStreamFile.txt";
        String outputStreamWriterFile = "outputStreamWriterFile.txt";

        int numDoubles = 5000000;

        try {
            // Write to dataOutputStreamFile
            long dataOutputStreamStartTime = System.currentTimeMillis();
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(dataOutputStreamFile));
            for (int i = 0; i < numDoubles; i++) {
                dos.writeDouble(i * 1.0);
            }
            dos.close();
            long dataOutputStreamEndTime = System.currentTimeMillis();
            long dataOutputStreamTime = dataOutputStreamEndTime - dataOutputStreamStartTime;
            System.out.println("Time taken to write to " + dataOutputStreamFile + " = " + dataOutputStreamTime + " milliseconds");

            // Write to outputStreamWriterFile
            long outputStreamWriterStartTime = System.currentTimeMillis();
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(outputStreamWriterFile));
            for (int i = 0; i < numDoubles; i++) {
                osw.write(Double.toString(i * 1.0));
                osw.write("\n");
            }
            osw.close();
            long outputStreamWriterEndTime = System.currentTimeMillis();
            long outputStreamWriterTime = outputStreamWriterEndTime - outputStreamWriterStartTime;
            System.out.println("Time taken to write to " + outputStreamWriterFile + " = " + outputStreamWriterTime + " milliseconds");

        } catch (IOException e) {
            e.printStackTrace();
        }


        String dataInputStreamFile = "dataOutputStreamFile.txt";
        String inputStreamReaderFile = "outputStreamWriterFile.txt";
        try {
            // Read from dataInputStreamFile
            long dataInputStreamStartTime = System.currentTimeMillis();
            DataInputStream dis = new DataInputStream(new FileInputStream(dataInputStreamFile));
            while (dis.available() > 0) {
                double num = dis.readDouble();
            }
            dis.close();
            long dataInputStreamEndTime = System.currentTimeMillis();
            long dataInputStreamTime = dataInputStreamEndTime - dataInputStreamStartTime;
            System.out.println("Time taken to read from " + dataInputStreamFile + " = " + dataInputStreamTime + " milliseconds");

            // Read from inputStreamReaderFile
            long inputStreamReaderStartTime = System.currentTimeMillis();
            InputStreamReader isr = new InputStreamReader(new FileInputStream(inputStreamReaderFile));
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                double num = Double.parseDouble(line);
            }
            br.close();
            long inputStreamReaderEndTime = System.currentTimeMillis();
            long inputStreamReaderTime = inputStreamReaderEndTime - inputStreamReaderStartTime;
            System.out.println("Time taken to read from " + inputStreamReaderFile + " = " + inputStreamReaderTime + " milliseconds");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}





