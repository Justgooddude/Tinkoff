package edu.project4.generators;

import edu.project4.customTypes.Color;
import edu.project4.customTypes.ImageSize;
import edu.project4.customTypes.MapBorders;
import edu.project4.customTypes.Pixel;
import edu.project4.customTypes.PixelMap;
import edu.project4.customTypes.Point;
import edu.project4.preobr.ColoredPreob;
import edu.project4.preobr.PreobrPattern;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class MultiThread implements Gener{
    private int threadCount;
    public MultiThread(int threadCount) {
        this.threadCount = threadCount;
    }
    @Override
    public PixelMap gener(
        ImageSize imageSize,
        MapBorders borders,
        List<PreobrPattern> variations,
        List<ColoredPreob> affine,
        int samples,
        int iterations
    ) {
        List<Callable<PixelMap>> tasks = new ArrayList<>();
        int remains = samples % threadCount;

        for (int i = 0; i < threadCount; i++) {
            int finalI = i;
            tasks.add(() -> new SingleThread().gener(
                imageSize,
                borders,
                variations,
                affine,
                samples / threadCount + ((finalI < remains) ? 1 : 0),
                iterations
            ));
        }

        try (ExecutorService executorService = Executors.newFixedThreadPool(threadCount)) {
            List<Future<PixelMap>> futures = executorService.invokeAll(tasks);

            List<PixelMap> created = new ArrayList<>();
            for (var future : futures) {
                created.add(future.get());
            }
            return new PixelMap(merge(created), imageSize.width(), imageSize.height());
        } catch (InterruptedException | ExecutionException e) {
            return new PixelMap(Map.of(), imageSize.width(), imageSize.height());
        }
    }

    private Map<Point, Pixel> merge(List<PixelMap> forMerge) {
        Map<Point,Pixel>result= new HashMap<>();
        Map<Point,Pixel>merged=forMerge.stream()
            .flatMap(pixelMap -> pixelMap.map().entrySet().stream())
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                mergePixels()
            ));
        result= merged.entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey,  pointPixelEntry -> new Pixel(
                new Color(
                    pointPixelEntry.getValue().color().r() / threadCount,
                    pointPixelEntry.getValue().color().g() / threadCount,
                    pointPixelEntry.getValue().color().b() / threadCount
                ),
                pointPixelEntry.getValue().counter()
            )));
        return result;
    }
    private static BinaryOperator<Pixel> mergePixels() {
        return (pixel1, pixel2) -> new Pixel(
            new Color(
                pixel1.color().r() + pixel2.color().r(),
                pixel1.color().g() + pixel2.color().g(),
                pixel1.color().b() + pixel2.color().b()
            ),
            pixel1.counter() + pixel2.counter()
        );
    }
}
