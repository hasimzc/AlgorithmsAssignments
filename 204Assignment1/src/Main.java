import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader csvFile = new BufferedReader(new FileReader("C:\\javademos\\202Assignment1\\src\\TrafficFlowDataset.csv"));
        ArrayList<Integer> flowDurationsList = new ArrayList<Integer>();
        String line = "";
        csvFile.readLine(); // The first row includes captions so ignore it.
        while ((line=csvFile.readLine()) != null){
            String[] columns = line.split(",");
            flowDurationsList.add(Integer.parseInt(columns[7]));
        }
        int[] flowDurations = new int[flowDurationsList.size()];
        for(int i=0;i<flowDurationsList.size();i++){
            flowDurations[i] = flowDurationsList.get(i);
        }
        InsertionSort insertionSort = new InsertionSort();
        MergeSort mergeSort = new MergeSort();
        PigeonholeSort pigeonholeSort = new PigeonholeSort();
        CountingSort countingSort = new CountingSort();
        // X axis data
        int[] inputAxis = {512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 251281};
        double[][] yAxis = new double[12][10];
        yAxis[0] = new double[10]; // InsertionSort random list
        yAxis[1] = new double[10]; // MergeSort random list
        yAxis[2] = new double[10]; // PigeonholeSort random list
        yAxis[3] = new double[10]; // CountingSort random list
        yAxis[4] = new double[10]; // InsertionSort sorted list
        yAxis[5] = new double[10]; // MergeSort sorted list
        yAxis[6] = new double[10]; // PigeonholeSort sorted list
        yAxis[7] = new double[10]; // CountingSort sorted list
        yAxis[8] = new double[10]; // InsertionSort reversely sorted list
        yAxis[9] = new double[10]; // MergeSort reversely sorted list
        yAxis[10] = new double[10]; // PigeonholeSort reversely sorted list
        yAxis[11] = new double[10]; // CountingSort reversely sorted list

        /// in the below there are codes for the measuring run times and creating plots. ///
        /*for(int k = 0;k<10;k++){
            int[] flowDurationsA = new int[inputAxis[k]];
            for(int i=0;i<inputAxis[k];i++){
                flowDurationsA[i] = flowDurations[i];
            }
            int[] cloneArray = flowDurationsA.clone();
            long time = 0;
            for (int i = 0;i<10;i++){
                flowDurationsA = cloneArray.clone();
                long start2 = System.currentTimeMillis();
                insertionSort.insertionSort(flowDurationsA);
                long end2 = System.currentTimeMillis();
                time += -(start2-end2);
            }
            yAxis[0][k] = time/10;
        }
        System.out.println(Arrays.toString(yAxis[0]));*/

        /*for(int k = 0;k<10;k++){
            int[] flowDurationsA = new int[inputAxis[k]];
            for(int i=0;i<inputAxis[k];i++){
                flowDurationsA[i] = flowDurations[i];
            }
            long time = 0;
            for (int i = 0;i<10;i++){
                long start2 = System.currentTimeMillis();
                mergeSort.mergeSort(flowDurationsA,0,flowDurationsA.length-1);
                long end2 = System.currentTimeMillis();
                time += -(start2-end2);
            }
            yAxis[1][k] = time/10;

        }

        System.out.println(Arrays.toString(yAxis[1]));/*
        for(int k = 0;k<10;k++){
            int[] flowDurationsA = new int[inputAxis[k]];
            for(int i=0;i<inputAxis[k];i++){
                flowDurationsA[i] = flowDurations[i];
            }
            long time = 0;
            int[] cloneArray = flowDurationsA.clone();

            for (int i = 0;i<10;i++){
                long start2 = System.currentTimeMillis();
                flowDurationsA = cloneArray.clone();
                pigeonholeSort.pigeonholeSort(flowDurationsA);
                long end2 = System.currentTimeMillis();
                time += -(start2-end2);
            }
            yAxis[2][k] = time/10;
        }
        System.out.println(Arrays.toString(yAxis[2]));
        for(int k = 0;k<10;k++){
            int[] flowDurationsA = new int[inputAxis[k]];
            for(int i=0;i<inputAxis[k];i++){
                flowDurationsA[i] = flowDurations[i];
            }
            long time = 0;
            int[] cloneArray = flowDurationsA.clone();

            for (int i = 0;i<10;i++){
                flowDurationsA = cloneArray.clone();
                long start2 = System.currentTimeMillis();
                countingSort.countingSort(flowDurationsA);
                long end2 = System.currentTimeMillis();
                time += -(start2-end2);
            }
            yAxis[3][k] = time/10;
        }
        System.out.println(Arrays.toString(yAxis[3]));

        */
        /*for(int k = 0;k<10;k++){
            int[] flowDurationsA = new int[inputAxis[k]];
            for(int i=0;i<inputAxis[k];i++){
                flowDurationsA[i] = flowDurations[i];
            }
            mergeSort.mergeSort(flowDurationsA,0,flowDurationsA.length-1);
            long time = 0;
            int min = flowDurationsA[0];
            int max = flowDurationsA[0];
            for (int j : flowDurationsA) {
                if (j < min) {
                    min = j;
                }
                if (j > max) {
                    max = j;
                }
            }
            System.out.println(max-min+1);
            for (int i = 0;i<10;i++){
                long start2 = System.currentTimeMillis();
                insertionSort.insertionSort(flowDurationsA);
                long end2 = System.currentTimeMillis();
                time += -(start2-end2);
            }

            yAxis[4][k] = time/10;
        }
        System.out.println(Arrays.toString(yAxis[4]));*/
        /*for(int k = 0;k<10;k++){
            int[] flowDurationsA = new int[inputAxis[k]];
            for(int i=0;i<inputAxis[k];i++){
                flowDurationsA[i] = flowDurations[i];
            }
            long time = 0;

            for (int i = 0;i<10;i++){
                long start2 = System.currentTimeMillis();
                mergeSort.mergeSort(flowDurationsA,0,flowDurationsA.length-1);
                long end2 = System.currentTimeMillis();
                time += -(start2-end2);
            }

            yAxis[5][k] = time/10;
        }
        System.out.println(Arrays.toString(yAxis[5]));*/
        /*for(int k = 0;k<10;k++){
            int[] flowDurationsA = new int[inputAxis[k]];
            for(int i=0;i<inputAxis[k];i++){
                flowDurationsA[i] = flowDurations[i];
            }
            long time = 0;

            for (int i = 0;i<10;i++){
                long start2 = System.currentTimeMillis();
                pigeonholeSort.pigeonholeSort(flowDurationsA);
                long end2 = System.currentTimeMillis();
                time += -(start2-end2);
            }

            yAxis[6][k] = time/10;
        }
        System.out.println(Arrays.toString(yAxis[6]));
        for(int k = 0;k<10;k++){
            int[] flowDurationsA = new int[inputAxis[k]];
            for(int i=0;i<inputAxis[k];i++){
                flowDurationsA[i] = flowDurations[i];
            }
            long time = 0;

            for (int i = 0;i<10;i++){
                long start2 = System.currentTimeMillis();
                countingSort.countingSort(flowDurationsA);
                long end2 = System.currentTimeMillis();
                time += -(start2-end2);
            }

            yAxis[7][k] = time/10;
        }
        System.out.println(Arrays.toString(yAxis[7]));*/
        /*for(int k = 0;k<10;k++){
            int[] flowDurationsA = new int[inputAxis[k]];
            for(int i=0;i<inputAxis[k];i++){
                flowDurationsA[i] = flowDurations[i];
            }
            mergeSort.mergeSort(flowDurationsA,0,flowDurationsA.length-1);
            int[] cloneArray = new int[inputAxis[k]];
            long time = 0;
            for (int i = 0;i<10;i++){
                for (int j =0;j<inputAxis[k];j++){
                    cloneArray[j] = flowDurationsA[flowDurationsA.length-1-j];
                }
                flowDurationsA = cloneArray.clone();

                long start2 = System.currentTimeMillis();
                insertionSort.insertionSort(flowDurationsA);
                long end2 = System.currentTimeMillis();
                time += -(start2-end2);
            }

            yAxis[8][k] = time/10;
        }

        System.out.println(Arrays.toString(yAxis[8]));
        for(int k = 0;k<10;k++){
            int[] flowDurationsA = new int[inputAxis[k]];
            for(int i=0;i<inputAxis[k];i++){
                flowDurationsA[i] = flowDurations[i];
            }
            mergeSort.mergeSort(flowDurationsA,0,flowDurationsA.length-1);
            int[] cloneArray = new int[inputAxis[k]];
            long time = 0;
            for (int i = 0;i<10;i++){
                for (int j =0;j<inputAxis[k];j++){
                    cloneArray[j] = flowDurationsA[flowDurationsA.length-1-j];
                }
                flowDurationsA = cloneArray.clone();
                long start2 = System.currentTimeMillis();
                mergeSort.mergeSort(flowDurationsA,0,flowDurationsA.length-1);
                long end2 = System.currentTimeMillis();
                time += -(start2-end2);
            }

            yAxis[9][k] = time/10;
        }
        System.out.println(Arrays.toString(yAxis[9]));*//*
        for(int k = 0;k<10;k++){
            int[] flowDurationsA = new int[inputAxis[k]];
            for(int i=0;i<inputAxis[k];i++){
                flowDurationsA[i] = flowDurations[i];
            }
            mergeSort.mergeSort(flowDurationsA,0,flowDurationsA.length-1);
            int[] cloneArray = new int[inputAxis[k]];
            long time = 0;
            for (int i = 0;i<10;i++){
                for (int j =0;j<inputAxis[k];j++){
                    cloneArray[j] = flowDurationsA[flowDurationsA.length-1-j];
                }
                flowDurationsA = cloneArray.clone();
                long start2 = System.currentTimeMillis();
                pigeonholeSort.pigeonholeSort(flowDurationsA);
                long end2 = System.currentTimeMillis();
                time += -(start2-end2);
            }

            yAxis[10][k] = time/10;
        }
        System.out.println(Arrays.toString(yAxis[10]));
        for(int k = 0;k<10;k++){
            int[] flowDurationsA = new int[inputAxis[k]];
            for(int i=0;i<inputAxis[k];i++){
                flowDurationsA[i] = flowDurations[i];
            }
            mergeSort.mergeSort(flowDurationsA,0,flowDurationsA.length-1);
            int[] cloneArray = new int[inputAxis[k]];
            long time = 0;
            for (int i = 0;i<10;i++){
                for (int j =0;j<inputAxis[k];j++){
                    cloneArray[j] = flowDurationsA[flowDurationsA.length-1-j];
                }
                flowDurationsA = cloneArray.clone();
                long start2 = System.currentTimeMillis();
                countingSort.countingSort(flowDurationsA);
                long end2 = System.currentTimeMillis();
                time += -(start2-end2);
            }

            yAxis[11][k] = time/10;

        }
        System.out.println(Arrays.toString(yAxis[11]));*/

        // Save the char as .png and show it
        showAndSaveChart("Sorting with random data", inputAxis, yAxis);
        csvFile.close();
    }

    public static void showAndSaveChart(String title, int[] xAxis, double[][] yAxis) throws IOException {
        // Create Chart
        XYChart chart = new XYChartBuilder().width(800).height(600).title(title)
                .yAxisTitle("Time in Milliseconds").xAxisTitle("Input Size").build();
        XYChart chart1 = new XYChartBuilder().width(800).height(600).title(title)
                .yAxisTitle("Time in Milliseconds").xAxisTitle("Input Size").build();
        XYChart chart2 = new XYChartBuilder().width(800).height(600).title(title)
                .yAxisTitle("Time in Milliseconds").xAxisTitle("Input Size").build();

        // Convert x axis to double[]
        double[] doubleX = Arrays.stream(xAxis).asDoubleStream().toArray();

        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
        chart1.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart1.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
        chart2.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart2.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

        // Add a plot for a sorting algorithm
        chart.addSeries("Insertion Sort", doubleX, yAxis[0]);
        chart.addSeries("Merge Sort", doubleX, yAxis[1]);
        chart.addSeries("Pigeonhole Sort", doubleX, yAxis[2]);
        chart.addSeries("countingSort", doubleX, yAxis[3]);
        /*chart.addSeries("Insertion Sort", doubleX, yAxis[4]);
        chart.addSeries("Merge Sort", doubleX, yAxis[5]);
        chart.addSeries("Pigeonhole Sort", doubleX, yAxis[6]);
        chart.addSeries("countingSort", doubleX, yAxis[7]);
        chart.addSeries("Insertion Sort", doubleX, yAxis[8]);
        chart.addSeries("Merge Sort", doubleX, yAxis[9]);
        chart.addSeries("Pigeonhole Sort", doubleX, yAxis[10]);
        chart.addSeries("countingSort", doubleX, yAxis[11]);*/


        // Save the chart as PNG
        BitmapEncoder.saveBitmap(chart, title + ".png", BitmapEncoder.BitmapFormat.PNG);

        // Show the chart
        new SwingWrapper(chart).displayChart();
    }

}
