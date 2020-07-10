package com.x.mowit.util;

import java.util.ArrayList;
import java.util.List;

import com.x.mowit.exception.DescriptionInvalidException;
import com.x.mowit.model.Grid;
import com.x.mowit.model.Instruction;
import com.x.mowit.model.Mower;

public class DescriptionsParserUtil {

    public static Grid parseGridDescription(List<String> descriptions) throws DescriptionInvalidException {

        String firstLine = descriptions.get(0);
        String[] sizes = splitBySpaces(firstLine);

        if (sizes.length != 2) {
            throw new DescriptionInvalidException("The first line is invalid to create the grid.");
        }

        return new Grid(Integer.parseInt(sizes[0]), Integer.parseInt(sizes[1]));
    }

    public static List<Mower> parseMowerDescriptions(List<String> descriptions) throws DescriptionInvalidException {

        List<Mower> mowers = new ArrayList<>();

        int mowerCount = 0;

        for (int i = 1; i < descriptions.size() - 1; i += 2) {

            String mowerState = descriptions.get(i);
            String mowerInstructions = descriptions.get(i + 1);

            String[] initialState = splitBySpaces(mowerState);
            if (initialState.length != 3) {
                throw new DescriptionInvalidException("The state description of a mower is invalid.");
            }

            mowers.add(
                new Mower(
                    ++mowerCount,
                    Integer.parseInt(initialState[0]),
                    Integer.parseInt(initialState[1]),
                    initialState[2].charAt(0),
                    Instruction.toInstructionQueue(mowerInstructions)
                )
            );
        }

        return mowers;
    }

    private static String[] splitBySpaces(String mowerState) {
        return mowerState.split(" ");
    }
}
