package sweep_line;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
https://leetcode.com/problems/meeting-rooms-ii/description/

Given an array of meeting time intervals where intervals[i] = [starti, endi], return the minimum number of
conference rooms required.
************************************************************************************************************************
clarification
1. all intervals are valid. input sorted?
2. edge overlapping. [1,3], [3,4] -> not considered as overlapping
************************************************************************************************************************
1. mark start as +1. mark end as -1. e.g [1,1],[2,-1],[3,1],[5,-1]
2. sort by time. key in the pair.
3. sweep the line. keep track of ongoing meetings
4. update rooms by comparing with ongoing meetings
 */
public class MeetingRoomsII {
    public int minRooms(int[][] intervals) {
        int ongoing = 0;
        int rooms = 0;

        List<Pair<Integer, Integer>> status = new ArrayList<>();
        for (int[] interval : intervals) {
            status.add(new Pair(interval[0], 1));
            status.add(new Pair(interval[1], -1));
        }

        status.sort((a, b) ->
                a.getKey().equals(b.getKey()) ? a.getValue() - b.getValue() : a.getKey() - b.getKey());

        for (Pair<Integer, Integer> cur : status) {
            ongoing += cur.getValue();
            rooms = Math.max(rooms, ongoing);
        }

        return rooms;
    }
}
