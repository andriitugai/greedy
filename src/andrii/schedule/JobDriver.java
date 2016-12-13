package andrii.schedule;

import andrii.uniheap.Heap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;

/**
 * Created by andrii on 08.12.16.
 */
public class JobDriver {

    public static void main(String[] args) {

        try {
            File file = new File("jobs.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            line = bufferedReader.readLine();
            Heap<Job> jobHeap = new Heap<>(
                    Integer.parseInt(line),
                    new Comparator<Job>() {
                        /*
                        (o1 - o2) -> increasing order
                        (o2 - o1) -> decreasing order
                         */
                        @Override
                        public int compare(Job o1, Job o2) {
                            int p1 = o1.getWeight() - o1.getLenght();
                            int p2 = o2.getWeight() - o2.getLenght();
                            if(p1 == p2) return o2.getWeight() - o1.getWeight();
                            return p2 - p1;
                        }
                    }
            );

            Heap<Job> jobRatioHeap = new Heap<>(
                    Integer.parseInt(line),
                    new Comparator<Job>() {
                        /*
                        (o1 - o2) -> increasing order
                        (o2 - o1) -> decreasing order
                         */
                        @Override
                        public int compare(Job o1, Job o2) {
                            double p1 = 1.0*o1.getWeight() / o1.getLenght();
                            double p2 = 1.0*o2.getWeight() / o2.getLenght();
                            return p2 > p1 ? 1:-1;
                        }
                    }
            );

            String[] tokens;

            while ((line = bufferedReader.readLine()) != null) {

                tokens = line.split(" ");
                jobRatioHeap.insert(new Job(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1])));
                jobHeap.insert(new Job(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1])));

            }

            fileReader.close();
            Job cJob;

//            System.out.println("Size of W-L schedule: "+jobHeap.size());
//            System.out.println("Size of W/L schedule: "+jobRatioHeap.size());

            int c = 0;
            long summa = 0L;

            int counter = 0;

            while (!jobRatioHeap.isEmpty()){
                cJob = jobRatioHeap.pop();
                if(counter++<10 || jobRatioHeap.size() < 10 ) System.out.println(cJob);
//
                c+=cJob.getLenght();
                summa += c*cJob.getWeight();
            }

            System.out.println("sum for jobs(W/L)= " + summa);

            c = 0;
            summa = 0L;

            while (!jobHeap.isEmpty()){
                cJob = jobHeap.pop();

                c+=cJob.getLenght();
                summa += c*cJob.getWeight();
            }

            System.out.println("sum for jobs(W-L)= " + summa);

        } catch (IOException e) {
            e.printStackTrace();
        }

//        Heap<Job> jobRatioHeap = new Heap<>(
//                new Comparator<Job>() {
//                    /*
//                    (o1 - o2) -> increasing order
//                    (o2 - o1) -> decreasing order
//                     */
//                    @Override
//                    public int compare(Job o1, Job o2) {
//                        double p1 = o1.getWeight() / o1.getLenght();
//                        double p2 = o2.getWeight() / o2.getLenght();
//                        return p2 > p1 ? 1:-1;
//                    }
//                }
//        );
//
//        jobRatioHeap.insert(new Job(100,25));
//        jobRatioHeap.insert(new Job(150,25));
//        jobRatioHeap.insert(new Job(200,25));
//        jobRatioHeap.insert(new Job(50,25));
//        jobRatioHeap.insert(new Job(25,25));
//        jobRatioHeap.insert(new Job(200,50));
//
//        while (!jobRatioHeap.isEmpty()){
//            System.out.println(jobRatioHeap.pop());
//        }

    }
}
