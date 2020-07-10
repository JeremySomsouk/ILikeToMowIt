package com.x.mowit;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.x.mowit.exception.DescriptionInvalidException;
import com.x.mowit.model.Grid;
import com.x.mowit.model.Mower;
import com.x.mowit.util.DescriptionsParserUtil;
import com.x.mowit.util.FileReaderUtil;

public class Main {

    public static void main(String[] args) throws IOException, DescriptionInvalidException, InterruptedException {

        List<String> descriptions = (args.length == 1) ?
            FileReaderUtil.parseInputFile(args[0]) :
            FileReaderUtil.getLinesFromResourceInputFile();

        final Grid grid = DescriptionsParserUtil.parseGridDescription(descriptions);
        final List<Mower> mowers = DescriptionsParserUtil.parseMowerDescriptions(descriptions);
        grid.initGridOccupation(mowers);

        launchMowers(grid, mowers);
        printMowersLastPosition(mowers);
    }

    private static void launchMowers(Grid grid, List<Mower> mowers) throws InterruptedException {

        ExecutorService executorService = Executors.newWorkStealingPool();

        for (Mower mower : mowers) {
            executorService.submit(new Thread(() -> mower.launchMower(grid)));
        }

        executorService.shutdown();
        executorService.awaitTermination(300, TimeUnit.SECONDS);
    }

    private static void printMowersLastPosition(List<Mower> mowers) {

        mowers.stream()
            .sorted(Comparator.comparing(Mower::getMowerNumber))
            .forEach(mower -> System.out.println(mower.toString()));
    }
}
